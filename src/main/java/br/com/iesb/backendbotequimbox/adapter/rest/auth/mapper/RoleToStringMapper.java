package br.com.iesb.backendbotequimbox.adapter.rest.auth.mapper;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface RoleToStringMapper {

    @Named(value = "roleToString")
    static String roleToString(UserModelRoleEnum roleEnum) {
        return roleEnum.getName();
    }

    @Named(value = "listRoleToListString")
    static List<String> listRoleToListString(List<UserModelRoleEnum> roleEnumList) {
        List<String> roles = new ArrayList<>();
        roleEnumList.forEach(roleEnum -> roles.add(roleEnum.getName()));

        return roles;
    }
}
