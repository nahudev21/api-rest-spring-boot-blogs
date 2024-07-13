package com.Nahudev.application_blog_api_rest.security;

import com.Nahudev.application_blog_api_rest.model.Rol;
import com.Nahudev.application_blog_api_rest.model.UserEntity;
import com.Nahudev.application_blog_api_rest.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese email o nombre de usuario " + usernameOrEmail));

        return new User(userEntity.getEmail(), userEntity.getPassword(), mapRoles(userEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Set<Rol> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
    }

}
