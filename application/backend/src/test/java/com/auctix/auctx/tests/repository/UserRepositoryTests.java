package com.auctix.auctx.tests.repository;

import com.auctix.auctx.helpers.UserConstants;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.annotation.PostConstruct;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsersRepository usersRepository;
    private static Users user, userUpdate;


    @PostConstruct
    public static void setUp() {

        user = new Users();
        user.setUsername(UserConstants.VALID_USERNAME);
        user.setEmail(UserConstants.VALID_EMAIL);
        user.setPassword(UserConstants.VALID_PASSWORD);
        user.setRole(UserConstants.VALID_ROLE);
        userUpdate = new Users();
        userUpdate.setUsername(UserConstants.VALID_UPDATE_USERNAME);
        userUpdate.setId(UserConstants.VALID_UPDATE_ID);
        userUpdate.setEmail(UserConstants.VALID_UPDATE_EMAIL);
        userUpdate.setPassword(UserConstants.VALID_PASSWORD);
        userUpdate.setRole(UserConstants.VALID_ROLE);

        Users addUser = new Users();
        addUser.setUsername(UserConstants.VALID_ADD_USERNAME);
        addUser.setId(UserConstants.VALID_ADD_ID);
        addUser.setEmail(UserConstants.VALID_ADD_EMAIL);
        addUser.setPassword(UserConstants.VALID_PASSWORD);
        addUser.setRole(UserConstants.VALID_ROLE);

        Users invalidAddUser = new Users();
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
        Users invalidUserUpdate = new Users();
        invalidUserUpdate.setUsername(UserConstants.INVALID_UPDATE_USERNAME);
        invalidUserUpdate.setId(UserConstants.INVALID_UPDATE_ID);
        invalidUserUpdate.setEmail(UserConstants.INVALID_UPDATE_EMAIL);
        invalidUserUpdate.setPassword(UserConstants.INVALID_PASSWORD);
        invalidUserUpdate.setRole(UserConstants.INVALID_ROLE);


    }

    @Test
    public void testFindUserByValidUsername() {
        testEntityManager.persist(user);
        Optional<Users> user1 = usersRepository.findByUsername(UserConstants.VALID_USERNAME);
        Assertions.assertEquals(user, user1.get());

    }

    @Test
    public void testFindUserByValidEmail() {
        testEntityManager.persist(user);
        Optional<Users> user1 = usersRepository.findByEmail(UserConstants.VALID_EMAIL);
        Assertions.assertEquals(user, user1.get());

    }

    @Test
    public void testAddValidUser() {
        Users users = usersRepository.save(user);
        Users users1 = UserRepositoryTests.user;
        users1.setId(users.getId());
        Assertions.assertEquals(users1, users);

    }

    @Test
    public void testUpdateValidUser() {
        testEntityManager.persist(user);
        userUpdate.setId(user.getId());
        userUpdate.setUsername(user.getUsername());
        usersRepository.save(userUpdate);
        Optional<Users> users = usersRepository.findByUsername(user.getUsername());
        Assertions.assertEquals(users.get(), userUpdate);

    }

    @Test
    public void testDeleteValidUserByEmail() {
        testEntityManager.persist(user);
        usersRepository.deleteByEmail(usersRepository.findByUsername(user.getUsername()).get().getEmail());
        Assertions.assertEquals(usersRepository.findAll().size(), 0);
    }

    @Test
    public void testDeleteValidUserById() {
        testEntityManager.persist(user);
        usersRepository.deleteById(usersRepository.findByUsername(user.getUsername()).get().getId());
        Assertions.assertEquals(usersRepository.findAll().size(), 0);
    }

}
