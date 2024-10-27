package com.gsweb.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsweb.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, String>{

}
