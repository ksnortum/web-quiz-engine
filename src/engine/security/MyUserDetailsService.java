package engine.security;

import engine.model.Account;
import engine.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> userOption = accountRepository.findById(username);

        if (userOption.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User name %s not found", username));
        }

        return new MyUserPrincipal(userOption.get());
    }
}