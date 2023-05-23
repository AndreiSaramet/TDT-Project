package com.auctix.auctx.converter;

import com.auctix.auctx.dto.UsersDto;
import com.auctix.auctx.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements IConverter<Users, UsersDto> {
    @Override
    public Users convertDtoToModel(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setUsername(usersDto.getUsername());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setRole(usersDto.getRole());
        return users;
    }

    @Override
    public UsersDto convertModelToDto(Users users) {
        UsersDto usersDto = new UsersDto();
        if(users == null)
            return usersDto;
        usersDto.setId(users.getId());
        usersDto.setUsername(users.getUsername());
        usersDto.setEmail(users.getEmail());
        usersDto.setPassword(users.getPassword());
        usersDto.setRole(users.getRole());
        return usersDto;
    }

    @Override
    public List<UsersDto> convertModelListToDtoList(List<Users> users) {
        return users
                .stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }
}
