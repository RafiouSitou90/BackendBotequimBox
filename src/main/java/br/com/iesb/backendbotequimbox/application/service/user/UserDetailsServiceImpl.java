package br.com.iesb.backendbotequimbox.application.service.user;

import br.com.iesb.backendbotequimbox.domain.exception.auth.AuthBadCredentialsException;
import br.com.iesb.backendbotequimbox.domain.port.spi.user.UserDaoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDaoPort userDaoPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userDaoPort.findByCnpjOrEmail(username).orElseThrow(AuthBadCredentialsException::new);
    }
}
