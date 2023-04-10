package br.com.iesb.backendbotequimbox.adapter.jpa.user.repository;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserRoleEntity;
import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByName(UserModelRoleEnum name);
}