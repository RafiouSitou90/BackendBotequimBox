package br.com.iesb.backendbotequimbox.application.service.user;

import br.com.iesb.backendbotequimbox.adapter.jpa.user.repository.UserEntityRepository;
import br.com.iesb.backendbotequimbox.domain.exception.auth.AuthBadCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userEntityRepository.findByCnpjOrEmail(username).orElseThrow(AuthBadCredentialsException::new);
    }
}
