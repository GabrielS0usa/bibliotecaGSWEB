package com.gsweb.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsweb.biblioteca.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
}
