package com.oscar.usuario.factory;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.model.User;

public class FactoryUserDataTest {
    public static User getUser() {
        User user = new User();
        user.setUserName("Oscar");
        user.setUserSurname("Garcia");
        user.setUserPhone("666666666");
        user.setUserAddress("Calle falsa 123");
        user.setUserEmail("oscar@gmail.com");
        user.setUserPassword("Ab*12345678");
        return user;
    }

    public static UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUserName("Oscar");
        userDto.setUserSurname("Garcia");
        userDto.setUserPhone("666666666");
        userDto.setUserAddress("Calle falsa 123");
        userDto.setUserEmail("oscar@gmail.com");
        userDto.setUserPassword("Ab*12345678");
        return userDto;
    }
}
