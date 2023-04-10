package br.com.iesb.backendbotequimbox.adapter.jpa.user.entity;

import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_roles",
        uniqueConstraints = {@UniqueConstraint(name = "role_name_uk", columnNames = {"name"})},
        indexes = {@Index(name = "role_name_idx", columnList = "name", unique = true)}
)
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserModelRoleEnum name;
}
