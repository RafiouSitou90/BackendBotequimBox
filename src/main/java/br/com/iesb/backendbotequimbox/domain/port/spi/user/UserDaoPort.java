package br.com.iesb.backendbotequimbox.domain.port.spi.user;

import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;

import java.util.Optional;

public interface UserDaoPort {

    UserModel save(UserModel userModel);

    Optional<UserModel> findById(Long id);

    Optional<UserModel> findByCnpj(String cnpj);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByCnpjOrEmail(String value);

    Boolean existsById(Long id);

    Boolean existsByCnpj(String cnpj);

    Boolean existsByEmail(String email);
}
