package br.com.iesb.backendbotequimbox.application.service.user;

import br.com.iesb.backendbotequimbox.domain.exception.auth.AuthBadCredentialsException;
import br.com.iesb.backendbotequimbox.domain.model.user.UserModel;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static br.com.iesb.backendbotequimbox.adapter.jpa.user.mapper.UserJpaEntityMapper.USER_JPA_ENTITY_MAPPER;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoPort userDaoPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return USER_JPA_ENTITY_MAPPER
                .toEntity(userDaoPort.findByCnpjOrEmail(username).orElseThrow(AuthBadCredentialsException::new));
    }
}
