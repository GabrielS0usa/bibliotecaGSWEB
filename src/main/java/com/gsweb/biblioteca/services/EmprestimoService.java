package com.gsweb.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsweb.biblioteca.entities.Emprestimo;
import com.gsweb.biblioteca.repositories.EmprestimoRepository;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository repository;
	
	public Emprestimo findbyId(Long id) {
		Optional<Emprestimo> obj = repository.findById(id);
		return obj.get();
	}
	
}
