package com.gautami.shipit.service;


import com.gautami.shipit.model.Role;
import com.gautami.shipit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository repository;
    public void createRoles(List<Role> role) {
        for(int i=0;i<role.size();i++){
           if(!role.get(i).getRoleName().equalsIgnoreCase("ADMIN")){
               repository.save(role.get(i));
           }
        }
    }
}
