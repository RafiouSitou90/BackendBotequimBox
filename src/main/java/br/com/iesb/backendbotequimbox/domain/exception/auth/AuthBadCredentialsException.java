package br.com.iesb.backendbotequimbox.domain.exception.auth;

public class AuthBadCredentialsException extends RuntimeException {

    public AuthBadCredentialsException() {
        super("Username or password incorrect.");
    }

    public AuthBadCredentialsException(String message) {
        super(message);
    }
}
