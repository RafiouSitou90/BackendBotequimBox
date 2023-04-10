package br.com.iesb.backendbotequimbox.adapter.jpa.user;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserRoleEntity;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.repository.UserRoleEntityRepository;
import br.com.iesb.backendbotequimbox.domain.enums.user.UserModelRoleEnum;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserRoleDaoPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper.UserRoleJpaEntityMapper.USER_ROLE_JPA_ENTITY_MAPPER;

@RequiredArgsConstructor
public class UserRoleJpaAdapter implements UserRoleDaoPort {

    private final UserRoleEntityRepository userRoleEntityRepository;

    @Override
    public UserRoleModel save(UserRoleModel userRoleModel) {
        UserRoleEntity userRoleEntity = USER_ROLE_JPA_ENTITY_MAPPER.toEntity(userRoleModel);
        return USER_ROLE_JPA_ENTITY_MAPPER
                .toDomainModel(userRoleEntityRepository.save(USER_ROLE_JPA_ENTITY_MAPPER.toEntity(userRoleModel)));
    }

    @Override
    public Optional<UserRoleModel> findByName(UserModelRoleEnum name) {
        final Optional<UserRoleEntity> userRoleEntity = userRoleEntityRepository.findByName(name);

        if (userRoleEntity.isEmpty()) return Optional.empty();

        return userRoleEntity.map(USER_ROLE_JPA_ENTITY_MAPPER::toDomainModel);
    }
}
