package br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String cnpj;
    private String email;
    private String password;
}
