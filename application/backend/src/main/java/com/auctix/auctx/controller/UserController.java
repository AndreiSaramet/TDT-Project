package com.auctix.auctx.controller;

import com.auctix.auctx.converter.UserConverter;
import com.auctix.auctx.dto.UsersDto;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UsersService userService;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/findUserByUsername/{username}")
    public UsersDto findUserByUsername(@PathVariable String username) {
        Users users = userService.findByUsername(username);
        return userConverter.convertModelToDto(users);
    }

    @GetMapping("/findUserByEmail/{email}")
    public UsersDto findUserByEmail(@PathVariable String email) {
        Users users = userService.findByEmail(email);
        return userConverter.convertModelToDto(users);
    }

    @PostMapping("/addUser")
    public UsersDto addUser(@RequestBody UsersDto usersDto) {
        Users users = userConverter.convertDtoToModel(usersDto);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users addedUser = userService.save(users);
        return userConverter.convertModelToDto(addedUser);
    }

    @PutMapping("/updateUser")
    public UsersDto updateUser(@RequestBody UsersDto usersDto) {
        Users users = userConverter.convertDtoToModel(usersDto);
        Users updatedUser = userService.update(users);
        return userConverter.convertModelToDto(updatedUser);
    }

    @DeleteMapping("/deleteUser/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

    @GetMapping("/findUserById/{id}")
    public UsersDto findUserById(@PathVariable Long id) {
        Users users = userService.findById(id);
        return userConverter.convertModelToDto(users);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
