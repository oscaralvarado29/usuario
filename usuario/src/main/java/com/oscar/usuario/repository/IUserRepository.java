package com.oscar.usuario.repository;

import com.oscar.usuario.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUserEmail(String userEmail);
    void deleteByUserEmail(String userEmail);
}

