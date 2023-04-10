package br.com.iesb.backendbotequimbox.domain.model.user;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleModel {

    private Long id;
    private UserModelRoleEnum name;

    public UserRoleModel(UserModelRoleEnum name) {
        this.name = name;
    }
}
