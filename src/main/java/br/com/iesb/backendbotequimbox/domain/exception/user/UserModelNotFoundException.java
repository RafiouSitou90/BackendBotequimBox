package br.com.iesb.backendbotequimbox.domain.exception.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserModelNotFoundException extends RuntimeException {

    private String fieldName;
    private Object fieldValue;

    public UserModelNotFoundException(String fieldName, Object fieldValue) {
        super(String.format("User Model Not Found with this %s : %s", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public UserModelNotFoundException(String message) {
        super(message);
    }
}
