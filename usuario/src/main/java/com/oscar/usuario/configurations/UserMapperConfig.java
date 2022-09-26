package com.oscar.usuario.configurations;

import com.oscar.usuario.dto.UserDto;
import com.oscar.usuario.mapper.UserMapper;
import com.oscar.usuario.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {
    @Bean
    public UserMapper userMapper() {
        return new UserMapper() {
            public User toUser(UserDto userDto) {
                User user = new User();
                user.setUserName(userDto.getUserName());
                user.setUserSurname(userDto.getUserSurname());
                user.setUserPhone(userDto.getUserPhone());
                user.setUserAddress(userDto.getUserAddress());
                user.setUserEmail(userDto.getUserEmail());
                user.setUserPassword(userDto.getUserPassword());
                return user;
            }

            public UserDto toUserDto(User user) {
                UserDto userDto = new UserDto();
                userDto.setUserName(user.getUserName());
                userDto.setUserSurname(user.getUserSurname());
                userDto.setUserPhone(user.getUserPhone());
                userDto.setUserAddress(user.getUserAddress());
                userDto.setUserEmail(user.getUserEmail());
                userDto.setUserPassword(user.getUserPassword());
                return userDto;
            }
        };
    }

}
