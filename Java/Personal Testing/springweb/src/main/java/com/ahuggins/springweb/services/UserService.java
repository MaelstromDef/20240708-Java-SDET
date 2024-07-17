package com.ahuggins.springweb.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahuggins.springweb.dtos.UserDto;
import com.ahuggins.springweb.mappers.UserMapper;
import com.ahuggins.springweb.models.User;

@Service
public class UserService {
    @Autowired private UserMapper mapper;

    private List<User> users;

    public UserService(){
        users = new LinkedList<User>();
        
        users.add(new User(0, "Caroline", "Ahumada", "cahumada@skillstorm.com", "password123"));
        users.add(new User(1, "Branden", "Arms", "barms@skillstorm.com", "password123!"));
        users.add(new User(2, "Kendall", "Budd", "kbudd@skillstorm.com", "verystrongpassword123"));
        users.add(new User(3, "Andy", "Zheng", "azheng@skillstorm.com", "password123"));
        users.add(new User(4, "Aaron", "Huggins", "ahuggins@skillstorm.com", "password123"));
    }

    public List<UserDto> findAllUsers(){
        return users.stream().map(mapper::toDto).toList();
    }

    // public List<User> findAllUsers(){
    //     return users;
    // }

    public List<User> findUserByFirstName(@RequestParam String firstName){
        List<User> matchingUsers = new LinkedList<>();
        for (User user : users) {
            if(user.getFirstName().equals(firstName)) matchingUsers.add(user);
        }

        return matchingUsers;
    }

    public User findUserById(@RequestParam long id){
        for (User user : users) {
            if(user.getId() == id) return user;
        }

        return null;
    }

    public User createUser(User user){
        user.setId(0);
        users.add(user);
        return user;
    }

    public User updateUser(long id, User user){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id){
                users.remove(i);
                users.add(user);
                break;
            }
        }
        return user;
    }

    public User deleteUser(long id){
        int i = 0;
        for(;i < users.size(); i++){
            if(users.get(i).getId() == id) break;
        }

        if(i >= users.size()) return null;
        User user = users.remove(i);
        return user;
    }
}
