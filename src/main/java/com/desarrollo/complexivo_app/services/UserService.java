package com.desarrollo.complexivo_app.services;

import com.desarrollo.complexivo_app.models.Role;
import com.desarrollo.complexivo_app.models.User;
import com.desarrollo.complexivo_app.repository.RoleRepository;
import com.desarrollo.complexivo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    public User save(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //Buscar Rol, sino crearlo
        Role userRole=roleRepository.findByName("ROLE_ADMIN").
                orElseGet(()->{
                    Role newRole= new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });
        user.getRoles().add(userRole);
        return this.repository.save(user);
    }
}
