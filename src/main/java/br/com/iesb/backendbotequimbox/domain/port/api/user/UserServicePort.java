package br.com.iesb.backendbotequimbox.domain.port.api.user;

import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelAlreadyExistsException;
import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelNotFoundException;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;

import java.util.List;
import java.util.Optional;

public interface UserServicePort {

    UserModel saveUserModel(UserModel userModel, List<UserRoleModel> roles) throws UserModelAlreadyExistsException;

    void enableUser(Long id) throws UserModelNotFoundException;

    Boolean existsUserByEmail(String email);

    Boolean existsUserById(Long id);

    Boolean existsUserByCnpj(String cnpj);

    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByCnpjOrEmail(String value);
}
