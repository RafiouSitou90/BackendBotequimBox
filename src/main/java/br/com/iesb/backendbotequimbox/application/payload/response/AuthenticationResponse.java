package br.com.iesb.backendbotequimbox.application.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthenticationResponse {

    private Long id;
    private String cnpj;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
}
