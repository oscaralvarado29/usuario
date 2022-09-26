package com.oscar.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String userName;

    private String userSurname;

    private String userPhone;

    private String userAddress;

    private String userEmail;

    private String userPassword;


}
