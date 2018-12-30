package com.samsolutions.controller;

import com.samsolutions.dto.SignUpInformationDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.error.exception.LoginExistsException;
import com.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/user/{name}")
    public UserDTO getUser(@PathVariable String name) {
        return userService.getByName(name);
    }

    @PostMapping
    public ModelAndView addUser(@ModelAttribute("informationDTO") @Valid SignUpInformationDTO informationDTO)
            throws LoginExistsException {
        //TODO проверить что он не пустой?
        userService.add(informationDTO);
        return new ModelAndView("login");

    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return userService.update(userDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserDTO userDTO) {
        userService.delete(userDTO);
    }



}
