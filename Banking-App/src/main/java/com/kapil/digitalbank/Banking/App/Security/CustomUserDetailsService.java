package com.kapil.digitalbank.Banking.App.Security;
import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import org.springframework.security.core.userdetails.User;
import com.kapil.digitalbank.Banking.App.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        AppUser user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found Exception"));
        return User.builder()
                .username(user.getFirstName() + " " + user.getLastName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
