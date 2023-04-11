package br.com.iesb.backendbotequimbox.application.service.auth;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserEntity;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.entity.UserRoleEntity;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request.AuthenticationRequest;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.AuthenticationResponse;
import br.com.iesb.backendbotequimbox.application.service.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        final UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(authRequest.getUsername());

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

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username == null) {
            return;
        }

        UserEntity userEntity = (UserEntity) userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, userEntity)) {
            final String accessToken = jwtService.generateAccessToken(userEntity);
            final AuthenticationResponse authResponse = AuthenticationResponse.builder()
                    .withId(userEntity.getId())
                    .withCnpj(userEntity.getCnpj())
                    .withEmail(userEntity.getEmail())
                    .withRoles(getRolesToString(userEntity.getRoles()))
                    .withAccessToken(accessToken)
                    .withRefreshToken(refreshToken)
                    .build();

            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        }
    }
}
