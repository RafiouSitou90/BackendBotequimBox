package br.com.iesb.backendbotequimbox.adapter.jpa.user.config;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.UserJpaAdapter;
import br.com.iesb.backendbotequimbox.adapter.jpa.user.repository.UserEntityRepository;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserJpaConfig {

    @Bean
    public UserDaoPort userDaoPort(UserEntityRepository userEntityRepository) {
        return new UserJpaAdapter(userEntityRepository);
    }
}
