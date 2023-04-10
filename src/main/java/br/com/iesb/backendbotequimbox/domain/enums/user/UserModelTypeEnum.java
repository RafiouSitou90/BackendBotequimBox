package br.com.iesb.backendbotequimbox.domain.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserModelTypeEnum {

    TYPE_MANUFACTURER("TYPE_MANUFACTURER", "TYPE_MANUFACTURER"),
    TYPE_RETAILER("TYPE_RETAILER", "TYPE_RETAILER");

    private final String name;
    private final String description;
}
