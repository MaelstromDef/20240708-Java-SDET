package com.ahuggins.springweb.mappers;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.ahuggins.springweb.dtos.UserDto;
import com.ahuggins.springweb.models.User;

@Component
public class UserMapper {
    public User toUser(UserDto dto){
        String[] name = dto.getName().split("\\s*");
        return new User(new Random().nextLong(1000), name[0], name[1], dto.getEmail());
    }

    public UserDto toDto(User user){
        return new UserDto(user.getFirstName() + " " + user.getLastName(), user.getEmail());
    }
}
