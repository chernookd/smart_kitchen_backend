package org.example.smartkitchen.security.services;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.smartkitchen.domain.entity.user.RoleEntity;
import org.example.smartkitchen.domain.entity.user.UserEntity;
import org.example.smartkitchen.domain.repository.user.UserRepository;
import org.example.smartkitchen.security.userPrincipal.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);

        return UserPrincipal.builder()
                .userId(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(extractSimpleGrantedAuthorities(userEntity))
                .build();
    }

    private List<SimpleGrantedAuthority> extractSimpleGrantedAuthorities(UserEntity userEntity) {
        RoleEntity role = userEntity.getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        return Collections.singletonList(authority);

    }
}
