package br.com.iesb.backendbotequimbox.application.service.auth;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserEntity;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserRoleEntity;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request.AuthenticationRequest;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.AuthenticationResponse;
import br.com.iesb.backendbotequimbox.application.service.jwt.JwtService;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper.UserJpaEntityMapper.USER_JPA_ENTITY_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserEntity userEntity = USER_JPA_ENTITY_MAPPER.toEntity(
                (UserModel) userDetailsService.loadUserByUsername(authRequest.getUsername()));

        final String accessToken = jwtService.generateAccessToken(userEntity);
        final String refreshToken = jwtService.generateRefreshToken(userEntity);

        return AuthenticationResponse.builder()
                .withId(userEntity.getId())
                .withCnpj(userEntity.getCnpj())
                .withEmail(userEntity.getEmail())
                .withRoles(getRolesToString(userEntity.getRoles()))
                .withAccessToken(accessToken)
                .withRefreshToken(refreshToken)
                .build();
    }

    private List<String> getRolesToString(List<UserRoleEntity> userRoles) {
        List<String> roles = new ArrayList<>(userRoles.size());
        userRoles.forEach(role -> roles.add(role.getName().getName()));

        return roles;
    }
}
