package br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String username;
    private String password;
}
