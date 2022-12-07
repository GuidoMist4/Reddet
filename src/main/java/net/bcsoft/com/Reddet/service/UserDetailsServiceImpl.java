package net.bcsoft.com.Reddet.service;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUserDetails = userRepo.findUserByUsername(username);
                User user = optionalUserDetails.orElseThrow(() ->
                        new UsernameNotFoundException("ERROR: User not found." + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities("USER"));
    }

    private Collection<?extends GrantedAuthority> getAuthorities (String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
