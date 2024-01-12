package com.gautami.shipit.controller;


import com.gautami.shipit.model.Role;
import com.gautami.shipit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PutMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public  void createRoles(@RequestBody List<Role> role){
        roleService.createRoles(role);
    }
}
