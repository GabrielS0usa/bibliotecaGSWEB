package com.gsweb.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsweb.biblioteca.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, String>{
	List<Livro> findByTitulo(String titulo);
}
