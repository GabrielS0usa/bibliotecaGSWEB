package com.gsweb.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsweb.biblioteca.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
