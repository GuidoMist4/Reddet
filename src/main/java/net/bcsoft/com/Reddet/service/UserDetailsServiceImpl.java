package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.model.User;
import net.bcsoft.com.Reddet.controller.exception.ExceptionHandler;
import net.bcsoft.com.Reddet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
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
