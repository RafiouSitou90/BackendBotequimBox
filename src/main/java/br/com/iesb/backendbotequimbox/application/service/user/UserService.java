package br.com.iesb.backendbotequimbox.application.service.user;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelAlreadyExistsException;
import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelNotFoundException;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserRoleServicePort;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserServicePort;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserDaoPort userDaoPort;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleServicePort userRoleServicePort;
    private final Clock clock;

    @Override
    public UserModel saveUserModel(UserModel userModel, List<UserRoleModel> roles)
            throws UserModelAlreadyExistsException {

        checkIfUserExistsByCnpjAndThrowException(userModel.getCnpj());
        checkIfUserExistsByEmailAndThrowException(userModel.getEmail());

        UserModel uModel = new UserModel();
        uModel.setCnpj(userModel.getCnpj());
        uModel.setEmail(userModel.getEmail().trim().toLowerCase());
        uModel.setPassword(getPasswordHashed(userModel.getPassword()));
        uModel.setRoles(getUserDefaultRoles(roles));
        uModel.setIsEnabled(false);
        uModel.setIsAccountNonExpired(false);
        uModel.setIsAccountNonLocked(false);
        uModel.setIsCredentialsNonExpired(false);
        uModel.setCreatedAt(LocalDateTime.now(clock));
        uModel.setUpdatedAt(null);

        return userDaoPort.save(uModel);
    }

    @Override
    public void enableUser(Long id) throws UserModelNotFoundException {
        UserModel userModel = findById(id).orElseThrow(() -> new UserModelNotFoundException("Id", id));

        userModel.setIsEnabled(true);
        userModel.setUpdatedAt(LocalDateTime.now(clock));

        userDaoPort.save(userModel);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userDaoPort.existsByEmail(email);
    }

    @Override
    public Boolean existsUserById(Long id) {
        return userDaoPort.existsById(id);
    }

    @Override
    public Boolean existsUserByCnpj(String cnpj) {
        return userDaoPort.existsByCnpj(cnpj);
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userDaoPort.findById(id);
    }

    @Override
    public Optional<UserModel> findByCnpjOrEmail(String value) {
        return userDaoPort.findByCnpjOrEmail(value);
    }

    private void checkIfUserExistsByCnpjAndThrowException(final String cnpj) {
        if (existsUserByCnpj(cnpj)) {
            throw new UserModelAlreadyExistsException("Cnpj", cnpj);
        }
    }

    private void checkIfUserExistsByEmailAndThrowException(final String email) {
        if (existsUserByCnpj(email)) {
            throw new UserModelAlreadyExistsException("Email", email);
        }
    }

    private List<UserRoleModel> getUserDefaultRoles(List<UserRoleModel> userRoleModels) {
        List<UserRoleModel> roles = new ArrayList<>();
        UserRoleModel userRole = userRoleServicePort.getOrSaveUserRoleModelByName(UserModelRoleEnum.ROLE_USER);
        roles.add(userRole);

        if (userRoleModels == null || userRoleModels.isEmpty()) {
            return roles;
        }

        userRoleModels.forEach(role -> roles.add(userRoleServicePort.getOrSaveUserRoleModelByName(role.getName())));

        return roles;
    }

    private String getPasswordHashed(final String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
