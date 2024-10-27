package com.gsweb.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsweb.biblioteca.entities.User;


public interface UserRepository extends JpaRepository<User, String>{

}
