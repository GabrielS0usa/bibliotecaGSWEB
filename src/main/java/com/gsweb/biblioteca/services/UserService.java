package com.gsweb.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gsweb.biblioteca.entities.User;
import com.gsweb.biblioteca.repositories.UserRepository;
import com.gsweb.biblioteca.services.exceptions.DatabaseException;
import com.gsweb.biblioteca.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		List<User> users = repository.findAll();
		return users;
	}
	
	public User findbyId(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
		try {
			if (repository.existsById(id)) {
				repository.deleteById(id);
			} 
			throw new EmptyResultDataAccessException(0);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(String id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, User obj) {
		entity.setNome(obj.getNome());
		entity.getContato().setEmail(obj.getContato().getEmail());
		entity.getContato().setTelefone(obj.getContato().getTelefone());
		entity.getEndereco().setCidade(obj.getEndereco().getCidade());
		entity.getEndereco().setUf(obj.getEndereco().getUf());
		entity.getEndereco().setCep(obj.getEndereco().getCep());
		entity.getEndereco().setNumeroResidencia(obj.getEndereco().getNumeroResidencia());
		
	}
	
}
