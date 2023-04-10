package br.com.iesb.backendbotequimbox.adapter.jpa.user.entity;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_cnpj_uk", columnNames = {"cnpj"}),
                @UniqueConstraint(name = "user_email_uk", columnNames = {"email"}),
        },
        indexes = {
                @Index(name = "user_cnpj_idx", columnList = "cnpj", unique = true),
                @Index(name = "user_email_idx", columnList = "email", unique = true),
        }
)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(unique = true, length = 20, nullable = false)
    private String cnpj;

    @Column(unique = true, length = 200, nullable = false)
    private String email;

    private String password;

    private Boolean isEnabled;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(targetEntity = UserRoleEntity.class)
    @JoinTable(name = "tab_user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @OrderBy(value = "name DESC")
    private List<UserRoleEntity> roles;

    @Enumerated(EnumType.STRING)
    private UserModelTypeEnum type = UserModelTypeEnum.TYPE_RETAILER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return cnpj;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
