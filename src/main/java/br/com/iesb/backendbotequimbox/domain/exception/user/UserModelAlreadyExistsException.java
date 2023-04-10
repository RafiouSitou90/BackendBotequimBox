package br.com.iesb.backendbotequimbox.domain.exception.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserModelAlreadyExistsException extends RuntimeException {

    private String fieldName;
    private Object fieldValue;

    public UserModelAlreadyExistsException(String fieldName, Object fieldValue) {
        super(String.format("User Model Already Exists with this %s : %s", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public UserModelAlreadyExistsException(String message) {
        super(message);
    }
}
