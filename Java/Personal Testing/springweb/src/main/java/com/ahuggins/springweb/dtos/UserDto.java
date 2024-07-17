package com.ahuggins.springweb.dtos;

import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;

public class UserDto {
    private String name;
    private String email;

    public UserDto() {
    }

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
