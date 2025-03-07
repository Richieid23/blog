package id.web.fitrarizki.blog.service;

import id.web.fitrarizki.blog.config.SecretPropertiesConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final SecretPropertiesConfig secretPropertiesConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || !username.equals(secretPropertiesConfig.getUser().getUsername())) {
            throw new UsernameNotFoundException("Username or Password is incorrect");
        }

        return User.builder()
                .username(secretPropertiesConfig.getUser().getUsername())
                .password(secretPropertiesConfig.getUser().getPassword())
                .build();
    }
}
