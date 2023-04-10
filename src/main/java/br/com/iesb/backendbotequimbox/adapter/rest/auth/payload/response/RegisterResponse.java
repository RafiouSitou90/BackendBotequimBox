package br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RegisterResponse {

    private Long id;
    private String cnpj;
    private String email;
    private List<Object> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
