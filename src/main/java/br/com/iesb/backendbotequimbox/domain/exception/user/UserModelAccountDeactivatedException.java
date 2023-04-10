package br.com.iesb.backendbotequimbox.domain.exception.user;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserModelAccountDeactivatedException extends RuntimeException {

    public UserModelAccountDeactivatedException(String fieldName, String fieldValue) {
        super("User Model Account deactivated.");
    }

    public UserModelAccountDeactivatedException(String message) {
        super(message);
    }
}
