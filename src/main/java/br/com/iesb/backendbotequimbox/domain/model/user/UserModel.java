package br.com.iesb.backendbotequimbox.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String cnpj;
    private String email;
    private String password;
    private Boolean isEnabled;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private List<UserRoleModel> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
