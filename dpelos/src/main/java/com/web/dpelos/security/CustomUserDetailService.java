package com.web.dpelos.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.dpelos.entity.Administrador;
import com.web.dpelos.entity.Dueno;
import com.web.dpelos.entity.Role;
import com.web.dpelos.entity.UserEntity;
import com.web.dpelos.entity.Veterinario;
import com.web.dpelos.repository.RoleRepository;
import com.web.dpelos.repository.UserRepository;



@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
           () -> new UsernameNotFoundException("User not found")
       );
       UserDetails userDetails = new User(userDB.getUsername(),
        userDB.getPassword(),
         mapRolesToAuthorities(userDB.getRoles()));
       return userDetails;
    }

        //PAsar de roles a GrantedAuthoritys
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserEntity duenoToUser(Dueno dueno){
        UserEntity user = new UserEntity();
        user.setUsername(dueno.getCedulaDueno());
        user.setPassword(passwordEncoder.encode("123"));

        Role roles = roleRepository.findByName("DUENO").get();
        user.setRoles(List.of(roles));
        return user;
    }

    public UserEntity veterinarioToUser(Veterinario veterinario){
        UserEntity user = new UserEntity();
        user.setUsername(veterinario.getCedulaVeterinario());
        user.setPassword(passwordEncoder.encode(veterinario.getPasswordVeterinario()));

        Role roles = roleRepository.findByName("VETERINARIO").get();
        user.setRoles(List.of(roles));
        return user;
    }
    
    public UserEntity adminToUser(Administrador administrador){
        UserEntity user = new UserEntity();
        user.setUsername(administrador.getAdminCedula());
        user.setPassword(passwordEncoder.encode(administrador.getPassword()));

        Role roles = roleRepository.findByName("ADMINISTRADOR").get();
        user.setRoles(List.of(roles));
        return user;
    }
    
}
