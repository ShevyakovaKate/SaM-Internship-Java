package com.samsolutions.controller;

import com.samsolutions.dto.RoleDTO;
import com.samsolutions.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RoleDTO getRole(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @PostMapping
    public RoleDTO addRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO result = roleService.add(roleDTO);
        return result;
    }

    @PutMapping(value = "/{id}")
    public RoleDTO updateUser(@RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        return roleService.update(roleDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
    }


    @DeleteMapping
    public void deleteRole(@RequestBody RoleDTO roleDTO) {
        roleService.delete(roleDTO);
    }

}
