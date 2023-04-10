package br.com.iesb.backendbotequimbox.adapter.rest.auth.mapper;

import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request.RegisterRequest;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.AuthenticationResponse;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.RegisterResponse;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {

    AuthRestMapper AUTH_REST_MAPPER = Mappers.getMapper(AuthRestMapper.class);

    RegisterResponse toRegisterResponse(UserModel userModel);

    UserModel toUserModel(RegisterRequest registerRequest);
}
