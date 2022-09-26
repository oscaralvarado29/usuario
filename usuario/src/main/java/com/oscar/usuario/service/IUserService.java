package com.oscar.usuario.service;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.exceptionhandler.UserAlreadyExistsException;

import java.util.List;

public interface IUserService {
    void saveUser(UserDto userDto) ;
    void deleteUser(String userEmail);
    void updateUser(UserDto userDto);
    UserDto getUserByEmail(String userEmail);
}
