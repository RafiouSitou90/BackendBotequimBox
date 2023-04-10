package br.com.iesb.backendbotequimbox.domain.port.api.user;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;

public interface UserRoleServicePort {

    UserRoleModel saveUserRoleModel(UserRoleModel userRoleModel);

    UserRoleModel getOrSaveUserRoleModelByName(UserModelRoleEnum name);
}
