package com.auctix.auctx.jwtutils;


import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        String password = user.getPassword();
//        password=BCrypt.hashpw(password, BCrypt.gensalt());
        if (user.getEmail().equals(username)) {
            return new User(user.getEmail(), password, new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(user.getRole()))));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
