package br.com.iesb.backendbotequimbox.application.service.user;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserRoleServicePort;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserRoleDaoPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRoleService implements UserRoleServicePort {

    private final UserRoleDaoPort userRoleDaoPort;

    @Override
    public UserRoleModel saveUserRoleModel(UserRoleModel userRoleModel) {
        return getOrSaveUserRoleModelByName(userRoleModel.getName());
    }

    @Override
    public UserRoleModel getOrSaveUserRoleModelByName(UserModelRoleEnum name) {
        Optional<UserRoleModel> userRoleModel = userRoleDaoPort.findByName(name);

        return userRoleModel.orElseGet(() -> userRoleDaoPort.save(new UserRoleModel(name)));
    }
}
