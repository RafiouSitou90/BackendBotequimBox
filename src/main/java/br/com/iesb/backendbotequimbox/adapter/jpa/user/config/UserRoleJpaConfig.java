package br.com.iesb.backendbotequimbox.adapter.jpa.user.config;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.UserRoleJpaAdapter;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.repository.UserRoleEntityRepository;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserRoleDaoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRoleJpaConfig {

    @Bean
    public UserRoleDaoPort userRoleDaoPort(UserRoleEntityRepository userRoleEntityRepository) {
        return new UserRoleJpaAdapter(userRoleEntityRepository);
    }
}
