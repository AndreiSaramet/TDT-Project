package com.auctix.auctx.tests.service;


import com.auctix.auctx.exception.UserAlreadyExistingException;
import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.helpers.UserConstants;

import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UsersRepository;
import com.auctix.auctx.service.UsersService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.PostConstruct;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {


    @MockBean
    private static UsersRepository usersRepository;

    @Autowired
    private UsersService userService;

    private static Users user, userUpdate;


    private static Users invalidUserUpdate, addUser, invalidAddUser;

    @PostConstruct
    public static void setUp() {

        user = new Users();
        user.setUsername(UserConstants.VALID_USERNAME);
        user.setId(UserConstants.VALID_ID);
        user.setEmail(UserConstants.VALID_EMAIL);
        user.setPassword(UserConstants.VALID_PASSWORD);
        user.setRole(UserConstants.VALID_ROLE);
        userUpdate = new Users();
        userUpdate.setUsername(UserConstants.VALID_UPDATE_USERNAME);
        userUpdate.setId(UserConstants.VALID_UPDATE_ID);
        userUpdate.setEmail(UserConstants.VALID_UPDATE_EMAIL);
        userUpdate.setPassword(UserConstants.VALID_PASSWORD);
        userUpdate.setRole(UserConstants.VALID_ROLE);

        addUser = new Users();
        addUser.setUsername(UserConstants.VALID_ADD_USERNAME);
        addUser.setId(UserConstants.VALID_ADD_ID);
        addUser.setEmail(UserConstants.VALID_ADD_EMAIL);
        addUser.setPassword(UserConstants.VALID_PASSWORD);
        addUser.setRole(UserConstants.VALID_ROLE);

        invalidAddUser = new Users();
        invalidAddUser.setUsername(UserConstants.INVALID_ADD_USERNAME);
        invalidAddUser.setId(UserConstants.INVALID_ADD_ID);
        invalidAddUser.setEmail(UserConstants.INVALID_ADD_EMAIL);
        invalidAddUser.setPassword(UserConstants.VALID_PASSWORD);
        invalidAddUser.setRole(UserConstants.VALID_ROLE);

        Users invalidUser = new Users();
        invalidUser.setUsername(UserConstants.INVALID_USERNAME);
        invalidUser.setId(UserConstants.INVALID_ID);
        invalidUser.setEmail(UserConstants.INVALID_EMAIL);
        invalidUser.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUser.setRole(UserConstants.INVALID_ROLE);
        invalidUserUpdate = new Users();
        invalidUserUpdate.setUsername(UserConstants.INVALID_UPDATE_USERNAME);
        invalidUserUpdate.setId(UserConstants.INVALID_UPDATE_ID);
        invalidUserUpdate.setEmail(UserConstants.INVALID_UPDATE_EMAIL);
        invalidUserUpdate.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUserUpdate.setRole(UserConstants.INVALID_ROLE);


        when(usersRepository.findByUsername(UserConstants.VALID_USERNAME)).thenReturn(Optional.of(user));
        when(usersRepository.findByUsername(UserConstants.INVALID_USERNAME)).thenThrow(UserNotFoundException.class);
        when(usersRepository.findByEmail(UserConstants.VALID_EMAIL)).thenReturn(Optional.of(user));
        when(usersRepository.findByEmail(UserConstants.INVALID_EMAIL)).thenThrow(UserNotFoundException.class);
        when(usersRepository.findById(UserConstants.INVALID_ID)).thenThrow(UserNotFoundException.class);
        when(usersRepository.findById(UserConstants.VALID_ID)).thenReturn(Optional.of(user));

        when(usersRepository.findByEmail(UserConstants.VALID_ADD_EMAIL)).thenReturn(Optional.empty());
        when(usersRepository.findByEmail(UserConstants.INVALID_ADD_EMAIL)).thenThrow(UserAlreadyExistingException.class);
        when(usersRepository.findByUsername(UserConstants.VALID_ADD_USERNAME)).thenReturn(Optional.empty());
        when(usersRepository.findByUsername(UserConstants.INVALID_ADD_USERNAME)).thenThrow(UserAlreadyExistingException.class);
        when(usersRepository.findById(UserConstants.VALID_ADD_ID)).thenReturn(Optional.empty());
        when(usersRepository.findById(UserConstants.INVALID_ADD_ID)).thenThrow(UserAlreadyExistingException.class);

        when(usersRepository.findByEmail(UserConstants.VALID_UPDATE_EMAIL)).thenReturn(Optional.ofNullable(userUpdate));
        when(usersRepository.findByEmail(UserConstants.INVALID_UPDATE_EMAIL)).thenThrow(UserNotFoundException.class);
        when(usersRepository.findByUsername(UserConstants.VALID_UPDATE_USERNAME)).thenReturn(Optional.ofNullable(userUpdate));
        when(usersRepository.findByUsername(UserConstants.INVALID_UPDATE_USERNAME)).thenThrow(UserNotFoundException.class);
        when(usersRepository.findById(UserConstants.VALID_UPDATE_ID)).thenReturn(Optional.ofNullable(userUpdate));
        when(usersRepository.findById(UserConstants.INVALID_UPDATE_ID)).thenThrow(UserNotFoundException.class);


        when(usersRepository.save(addUser)).thenReturn(addUser);
        when(usersRepository.save(invalidUser)).thenReturn(invalidUser);
        when(usersRepository.save(userUpdate)).thenReturn(userUpdate);
        when(usersRepository.save(invalidUserUpdate)).thenThrow(UserNotFoundException.class);

        doNothing().when(usersRepository).deleteByEmail(UserConstants.VALID_EMAIL);
        doThrow(UserNotFoundException.class).when(usersRepository).deleteByEmail(UserConstants.INVALID_EMAIL);
        doNothing().when(usersRepository).deleteById(UserConstants.VALID_ID);
        doThrow(UserNotFoundException.class).when(usersRepository).deleteById(UserConstants.INVALID_ID);


    }

    @Test
    public void testFindUserByValidUsername() {
        Users user = userService.findByUsername(UserConstants.VALID_USERNAME);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, UserServiceTests.user);
    }

    @Test
    public void testFindUserByInvalidUsername() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> usersRepository.findByUsername(UserConstants.INVALID_USERNAME));
    }

    @Test
    public void testFindUserByValidEmail() throws Exception {
        Users user = userService.findByEmail(UserConstants.VALID_EMAIL);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, UserServiceTests.user);
    }

    @Test
    public void testFindUserByInvalidEmail() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> usersRepository.findByEmail(UserConstants.INVALID_EMAIL));

    }

    @Test
    public void testFindUserByValidId() throws Exception {
        Users user = userService.findById(UserConstants.VALID_ID);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, UserServiceTests.user);
    }

    @Test
    public void testFindUserByInvalidId() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findById(UserConstants.INVALID_ID));

    }


    @Test
    public void testAddValidUser() throws Exception {
        Users user = userService.save(UserServiceTests.addUser);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, UserServiceTests.addUser);
    }

    @Test
    public void testAddInvalidUser() throws Exception {
        Assertions.assertThrows(UserAlreadyExistingException.class, () -> userService.save(UserServiceTests.invalidAddUser));

    }

    @Test
    public void testUpdateValidUser() throws Exception {
        Users user = userService.update(UserServiceTests.userUpdate);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, UserServiceTests.userUpdate);
    }

    @Test
    public void testUpdateInvalidUser() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.update(UserServiceTests.invalidUserUpdate));


    }

    @Test
    public void testDeleteValidUser() throws Exception {
        userService.deleteByEmail(UserConstants.VALID_EMAIL);
        verify(usersRepository, times(1)).findByEmail(UserConstants.VALID_EMAIL);
        verify(usersRepository, times(1)).deleteByEmail(UserConstants.VALID_EMAIL);

    }

    @Test
    public void testDeleteInvalidUser() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteByEmail(UserConstants.INVALID_EMAIL));


    }

    @Test
    public void testDeleteValidUseById() throws Exception {
        userService.deleteById(UserConstants.VALID_ID);
        verify(usersRepository, times(1)).findById(UserConstants.VALID_ID);
        verify(usersRepository, times(1)).deleteById(UserConstants.VALID_ID);


    }

    @Test
    public void testDeleteInvalidUserById() throws Exception {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteById(UserConstants.INVALID_ID));


    }

}
