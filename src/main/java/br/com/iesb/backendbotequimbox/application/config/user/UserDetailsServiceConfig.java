package br.com.iesb.backendbotequimbox.application.config.user;

import br.com.iesb.backendbotequimbox.application.service.user.UserDetailsServiceImpl;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsServiceConfig {

    public UserDetailsService userDetailsService(UserDaoPort userDaoPort) {
        return new UserDetailsServiceImpl(userDaoPort);
    }
}
