package br.com.iesb.backendbotequimbox.domain.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserModelRoleEnum {

    ROLE_USER("ROLE_USER", "ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN", "ROLE_ADMIN"),
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN", "ROLE_SUPER_ADMIN");

    private final String name;
    private final String description;
}
