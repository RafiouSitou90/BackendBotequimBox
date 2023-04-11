package br.com.iesb.backendbotequimbox.adapter.rest.auth.controller.v1;

import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request.AuthenticationRequest;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.request.RegisterRequest;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.AuthenticationResponse;
import br.com.iesb.backendbotequimbox.adapter.rest.auth.payload.response.RegisterResponse;
import br.com.iesb.backendbotequimbox.application.service.auth.AuthService;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserServicePort;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static br.com.iesb.backendbotequimbox.adapter.rest.auth.mapper.AuthRestMapper.AUTH_REST_MAPPER;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationV1Controller {

    private final AuthService authService;
    private final UserServicePort userServicePort;

    @PostMapping(path = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(
                AUTH_REST_MAPPER.toRegisterResponse(userServicePort.saveUserModel(
                        AUTH_REST_MAPPER.toUserModel(registerRequest), null)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authRequest) {
        return new ResponseEntity<>(authService.authenticate(authRequest), HttpStatus.OK);
    }

    @PostMapping(path = "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
