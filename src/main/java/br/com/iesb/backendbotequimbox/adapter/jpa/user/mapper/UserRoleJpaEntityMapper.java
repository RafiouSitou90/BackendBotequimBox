package br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserRoleEntity;
import br.com.iesb.backendbotequimbox.domain.model.user.UserRoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleJpaEntityMapper {

    UserRoleJpaEntityMapper USER_ROLE_JPA_ENTITY_MAPPER = Mappers.getMapper(UserRoleJpaEntityMapper.class);

    UserRoleModel toDomainModel(UserRoleEntity userRoleEntity);
    List<UserRoleModel> toDomainModelList(List<UserRoleEntity> userRoleEntityList);

    UserRoleEntity toEntity(UserRoleModel userRoleModel);

    List<UserRoleEntity> toEntityList(List<UserRoleModel> userRoleModelList);
}
