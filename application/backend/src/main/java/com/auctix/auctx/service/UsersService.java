package com.auctix.auctx.service;

import com.auctix.auctx.exception.UserAlreadyExistingException;
import com.auctix.auctx.exception.UserNotFoundException;
import com.auctix.auctx.model.Users;
import com.auctix.auctx.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersService implements IUsersService{

    private final UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Override
    public Users findByUsername(String username) {
        logger.info("findByUsername: " + username);
        return usersRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Users findByEmail(String email) {
        logger.info("findByEmail: " + email);
        return usersRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Users save(Users users) {
        logger.info("save: " + users.getEmail());
        usersRepository.findByEmail(users.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistingException();
        });
        usersRepository.findByUsername(users.getUsername()).ifPresent(u -> {
            throw new UserAlreadyExistingException();
        });
        return usersRepository.save(users);
    }

    @Override
    public void deleteByEmail(String email) {
        logger.info("deleteByEmail: " + email);
        usersRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        usersRepository.deleteByEmail(email);
    }

    @Override
    public Users findById(Long id) {
        logger.info("findById: " + id);
        return usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Users update(Users users) {
        logger.info("update: " + users.getId());
        usersRepository.findById(users.getId()).orElseThrow(UserNotFoundException::new);
        return usersRepository.save(users);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("deleteById: " + id);
        usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        usersRepository.deleteById(id);
    }
}
