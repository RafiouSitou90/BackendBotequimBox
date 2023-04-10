package br.com.iesb.backendbotequimbox.application.config.user;

import br.com.iesb.backendbotequimbox.application.service.user.UserRoleService;
import br.com.iesb.backendbotequimbox.domain.port.api.user.UserRoleServicePort;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserRoleDaoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoleServiceConfig {

    @Bean
    public UserRoleServicePort userRoleServicePort(UserRoleDaoPort userRoleDaoPort) {
        return new UserRoleService(userRoleDaoPort);
    }
}
