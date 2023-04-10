package br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder(setterPrefix = "with")
public class AuthenticationResponse {

    private Long id;
    private String cnpj;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
}
