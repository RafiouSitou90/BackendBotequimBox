package br.com.iesb.backendbotequimbox.adapter.jpa.user;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserEntity;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.repository.UserEntityRepository;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper.UserJpaEntityMapper.USER_JPA_ENTITY_MAPPER;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserDaoPort {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserModel save(UserModel userModel) {
        return USER_JPA_ENTITY_MAPPER
                .toDomainModel(userEntityRepository.save(USER_JPA_ENTITY_MAPPER.toEntity(userModel)));
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        final Optional<UserEntity> userEntity = userEntityRepository.findById(id);

        if (userEntity.isEmpty()) return Optional.empty();

        return userEntity.map(USER_JPA_ENTITY_MAPPER::toDomainModel);
    }

    @Override
    public Optional<UserModel> findByCnpj(String cnpj) {
        final Optional<UserEntity> userEntity = userEntityRepository.findByCnpj(cnpj);

        if (userEntity.isEmpty()) return Optional.empty();

        return userEntity.map(USER_JPA_ENTITY_MAPPER::toDomainModel);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        final Optional<UserEntity> userEntity = userEntityRepository.findByEmailIgnoreCase(email);

        if (userEntity.isEmpty()) return Optional.empty();

        return userEntity.map(USER_JPA_ENTITY_MAPPER::toDomainModel);
    }

    @Override
    public Optional<UserModel> findByCnpjOrEmail(String value) {
        final Optional<UserEntity> userEntity = userEntityRepository.findByCnpjOrEmail(value);

        if (userEntity.isEmpty()) return Optional.empty();

        return userEntity.map(USER_JPA_ENTITY_MAPPER::toDomainModel);
    }

    @Override
    public Boolean existsById(Long id) {
        return userEntityRepository.existsById(id);
    }

    @Override
    public Boolean existsByCnpj(String cnpj) {
        return userEntityRepository.existsByCnpj(cnpj);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userEntityRepository.existsByEmailIgnoreCase(email);
    }
}
