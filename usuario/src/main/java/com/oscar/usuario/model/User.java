package com.oscar.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class User implements Serializable {

    @Column()
    private String userName;

    @Column()
    private String userSurname;

    @Column()
    private String userPhone;

    @Column()
    private String userAddress;

    @Id
    @Column()
    private String userEmail;

    @Column()
    private String userPassword;
}
