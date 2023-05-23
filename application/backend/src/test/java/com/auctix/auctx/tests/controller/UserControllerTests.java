package com.auctix.auctx.tests.controller;


import com.auctix.auctx.controller.UserController;
import com.auctix.auctx.converter.UserConverter;
import com.auctix.auctx.dto.UsersDto;

import com.auctix.auctx.exception.UserAlreadyExistingException;
import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.helpers.UserConstants;
import com.auctix.auctx.jwtutils.JwtFilter;

import com.auctix.auctx.model.Users;

import com.auctix.auctx.service.UsersService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private static UsersService userService;

    @MockBean
    private static UserConverter userConverter;

    @MockBean
    private PasswordEncoder crypt;

    @MockBean
    private JwtFilter jwtFilter;

    private static UsersDto usersDto, invalidUserDto;

    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @PostConstruct
    public static void setUp() {

        Users user = new Users();
        user.setUsername(UserConstants.VALID_USERNAME);
        user.setId(UserConstants.VALID_ID);
        user.setEmail(UserConstants.VALID_EMAIL);
        user.setPassword(UserConstants.VALID_PASSWORD);
        user.setRole(UserConstants.VALID_ROLE);
        usersDto = new UsersDto();
        usersDto.setUsername(UserConstants.VALID_USERNAME);
        usersDto.setId(UserConstants.VALID_ID);
        usersDto.setEmail(UserConstants.VALID_EMAIL);
        usersDto.setPassword(UserConstants.VALID_PASSWORD);
        usersDto.setRole(UserConstants.VALID_ROLE);

        Users invalidUser = new Users();
        invalidUser.setUsername(UserConstants.INVALID_USERNAME);
        invalidUser.setId(UserConstants.INVALID_ID);
        invalidUser.setEmail(UserConstants.INVALID_EMAIL);
        invalidUser.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUser.setRole(UserConstants.INVALID_ROLE);

        invalidUserDto = new UsersDto();
        invalidUserDto.setUsername(UserConstants.INVALID_USERNAME);
        invalidUserDto.setId(UserConstants.INVALID_ID);
        invalidUserDto.setEmail(UserConstants.INVALID_EMAIL);
        invalidUserDto.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUserDto.setRole(UserConstants.INVALID_ROLE);

        when(userService.findByUsername(UserConstants.VALID_USERNAME)).thenReturn(user);
        when(userService.findByUsername(UserConstants.INVALID_USERNAME)).thenThrow(UserNotFoundException.class);
        when(userService.findByEmail(UserConstants.VALID_EMAIL)).thenReturn(user);
        when(userService.findByEmail(UserConstants.INVALID_EMAIL)).thenThrow(UserNotFoundException.class);
        when(userService.findById(UserConstants.INVALID_ID)).thenThrow(UserNotFoundException.class);
        when(userService.findById(UserConstants.VALID_ID)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
        when(userService.save(invalidUser)).thenThrow(UserAlreadyExistingException.class);
        when(userService.update(user)).thenReturn(user);
        when(userService.update(invalidUser)).thenThrow(UserNotFoundException.class);
        doNothing().when(userService).deleteByEmail(UserConstants.VALID_EMAIL);
        doThrow(UserNotFoundException.class).when(userService).deleteByEmail(UserConstants.INVALID_EMAIL);
        doNothing().when(userService).deleteById(UserConstants.VALID_ID);
        doThrow(UserNotFoundException.class).when(userService).deleteById(UserConstants.INVALID_ID);
        when(userConverter.convertModelToDto(user)).thenReturn(usersDto);
        when(userConverter.convertDtoToModel(usersDto)).thenReturn(user);
        when(userConverter.convertDtoToModel(invalidUserDto)).thenReturn(invalidUser);

    }

    @Test
    public void testFindUserByValidUsername() throws Exception {
        mockMvc.perform(get("/findUserByUsername/{username}", UserConstants.VALID_USERNAME).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(UserConstants.VALID_USERNAME));

    }

    @Test
    public void testFindUserByInvalidUsername() throws Exception {
        mockMvc.perform(get("/findUserByUsername/{username}", UserConstants.INVALID_USERNAME).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void testFindUserByValidEmail() throws Exception {
        mockMvc.perform(get("/findUserByEmail/{email}", UserConstants.VALID_EMAIL).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(UserConstants.VALID_EMAIL));

    }

    @Test
    public void testFindUserByInvalidEmail() throws Exception {
        mockMvc.perform(get("/findUserByEmail/{email}", UserConstants.INVALID_EMAIL).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void testFindUserByValidId() throws Exception {
        mockMvc.perform(get("/findUserById/{id}", UserConstants.VALID_ID).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(UserConstants.VALID_ID));

    }

    @Test
    public void testFindUserByInvalidId() throws Exception {
        mockMvc.perform(get("/findUserById/{id}", UserConstants.INVALID_ID).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }


    @Test
    public void testAddValidUser() throws Exception {
        mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(usersDto)).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(UserConstants.VALID_USERNAME)).andExpect(jsonPath("$.email").value(UserConstants.VALID_EMAIL)).andExpect(jsonPath("$.role").value(UserConstants.VALID_ROLE));

    }

    @Test
    public void testAddInvalidUser() throws Exception {
        mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(invalidUserDto)).accept("application/json")).andExpect(status().isConflict())
                .andExpect(content().string("User already exists"));

    }

    @Test
    public void testUpdateValidUser() throws Exception {
        mockMvc.perform(put("/updateUser").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(usersDto)).accept("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(UserConstants.VALID_USERNAME)).andExpect(jsonPath("$.email").value(UserConstants.VALID_EMAIL)).andExpect(jsonPath("$.role").value(UserConstants.VALID_ROLE));

    }

    @Test
    public void testUpdateInvalidUser() throws Exception {
        mockMvc.perform(put("/updateUser").contentType(MediaType.APPLICATION_JSON).content(this.ow.writeValueAsString(invalidUserDto)).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void testDeleteValidUser() throws Exception {
        mockMvc.perform(delete("/deleteUser/{email}", UserConstants.VALID_EMAIL).accept("application/json")).andExpect(status().isOk())
        ;

    }

    @Test
    public void testDeleteInvalidUser() throws Exception {
        mockMvc.perform(delete("/deleteUser/{email}", UserConstants.INVALID_EMAIL).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }

    @Test
    public void testDeleteValidUseById() throws Exception {
        mockMvc.perform(delete("/deleteUserById/{id}", UserConstants.VALID_ID).accept("application/json")).andExpect(status().isOk())
        ;

    }

    @Test
    public void testDeleteInvalidUserById() throws Exception {
        mockMvc.perform(delete("/deleteUserById/{id}", UserConstants.INVALID_ID).accept("application/json")).andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

    }


}
