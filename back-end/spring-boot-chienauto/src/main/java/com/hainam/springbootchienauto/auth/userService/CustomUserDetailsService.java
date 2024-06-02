package com.hainam.springbootchienauto.auth.userService;

import com.hainam.springbootchienauto.entity.User;
import com.hainam.springbootchienauto.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(()-> new NullPointerException("phone number not found"));
        return new CustomUserDetails(user);
    }
}
