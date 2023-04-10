package br.com.iesb.backendbotequimbox.domain.port.spi.user;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;

import java.util.Optional;

public interface UserRoleDaoPort {

    UserRoleModel save(UserRoleModel userRoleModel);

    Optional<UserRoleModel> findByName(UserModelRoleEnum name);
}
