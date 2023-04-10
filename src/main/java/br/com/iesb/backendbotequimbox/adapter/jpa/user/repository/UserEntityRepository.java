package br.com.iesb.backendbotequimbox.adapter.jpa.user.repository;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCnpj(String cnpj);

    Optional<UserEntity> findByEmailIgnoreCase(String email);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.cnpj = ?1 OR u.email = ?1")
    Optional<UserEntity> findByCnpjOrEmail(String value);

    Boolean existsByCnpj(String cnpj);

    Boolean existsByEmailIgnoreCase(String email);
}