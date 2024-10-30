package com.gsweb.biblioteca.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gsweb.biblioteca.entities.Emprestimo;
import com.gsweb.biblioteca.entities.Livro;
import com.gsweb.biblioteca.repositories.EmprestimoRepository;
import com.gsweb.biblioteca.services.exceptions.DatabaseException;
import com.gsweb.biblioteca.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmprestimoService {
	
	@Autowired
	private EmprestimoRepository repository;
	
	@Autowired
	private LivroService livroService;
	
	public List<Emprestimo> findAll() {
		List<Emprestimo> emprestimos = repository.findAll();
		return emprestimos;
	}
	
	public Emprestimo findbyId(Long id) {
		Optional<Emprestimo> obj = repository.findById(id);
		return obj.get();
	}
	
	public void insert(Emprestimo obj,  List<String> livrosIsbn) {   
		List<Livro> livrosSelecionados = livrosIsbn.stream().map(isbn -> livroService.findById(isbn)).filter(Objects::nonNull).collect(Collectors.toList());
	    obj.setLivros(livrosSelecionados);
	    livrosSelecionados.forEach(livro -> livro.emprestar());
	    repository.save(obj);
	}
	
	public Emprestimo update(Long id, Emprestimo obj) {
		try {
			Emprestimo entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Emprestimo entity, Emprestimo obj) {
		entity.setDataDevolucao(obj.getDataDevolucao());
		entity.getLivros().forEach(livro -> livro.devolver());
		entity.calcularMulta();
	}
	
}
