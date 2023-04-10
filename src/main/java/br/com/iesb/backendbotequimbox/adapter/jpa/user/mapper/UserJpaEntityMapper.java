package br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserEntity;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserJpaEntityMapper {

    UserJpaEntityMapper USER_JPA_ENTITY_MAPPER = Mappers.getMapper(UserJpaEntityMapper.class);

    UserModel toDomainModel(UserEntity userEntity);

    List<UserModel> toDomainModel(List<UserEntity> userEntityList);

    UserEntity toEntity(UserModel userModel);

    List<UserEntity> toEntity(List<UserModel> userModelList);
}
