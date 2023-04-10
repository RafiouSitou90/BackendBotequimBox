package br.com.iesb.backendbotequimbox.application.config.user;

import br.com.iesb.backendbotequimbox.application.service.user.UserService;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserRoleServicePort;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserServicePort;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;

@Configuration
@RequiredArgsConstructor
public class UserServiceConfig {

    @Bean
    public UserServicePort userServicePort(UserDaoPort userDaoPort, PasswordEncoder passwordEncoder,
                                           UserRoleServicePort userRoleServicePort) {
        return new UserService(userDaoPort, passwordEncoder, userRoleServicePort, clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
