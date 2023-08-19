package com.github.jarmas97.jewelryshopspringbootrestapi.security;

import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.User;
import com.github.jarmas97.jewelryshopspringbootrestapi.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            System.out.println("Username not found");
            throw new UsernameNotFoundException("");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList(user.getRole())
        );
    }
}
