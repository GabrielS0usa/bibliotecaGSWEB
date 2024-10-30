package com.gsweb.biblioteca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gsweb.biblioteca.entities.Livro;
import com.gsweb.biblioteca.repositories.LivroRepository;
import com.gsweb.biblioteca.services.exceptions.DatabaseException;
import com.gsweb.biblioteca.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	public List<Livro> findAll() {
		List<Livro> livros = repository.findAll();
		return livros;
	}
	
	public Livro findById(String id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.get();
	}
	
	public List<Livro> findByTitulo(String titulo) {
		List<Livro> livros = repository.findByTitulo(titulo);
		return livros;
	}
	
	public Livro insert(Livro obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
	    try {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	        } else {
	            throw new ResourceNotFoundException(id);
	        }
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}

	
	public Livro update(String id, Livro obj) {
		try {
			Livro entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(Livro entity, Livro obj) {
		entity.setIsbn(obj.getIsbn());
		entity.setTitulo(obj.getTitulo());
		entity.setAutor(obj.getAutor());
		entity.setEditora(obj.getEditora());
		entity.setExemplares(obj.getExemplares());
	}
	
	public List<Livro> findAllEmprestimos() {
		List<Livro> livros = repository.findAll();
		livros = livros.stream().filter(livro -> livro.getExemplares() > 0).collect(Collectors.toList());
		return livros;
	}
}
