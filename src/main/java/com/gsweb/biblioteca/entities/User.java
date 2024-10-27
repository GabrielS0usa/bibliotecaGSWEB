package com.gsweb.biblioteca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cpf;
	private String nome;
	private Date dataDeNascimento;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Contato contato;
	
	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Endereco endereco;
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Emprestimo> emprestimos;
	
	public User() {
	}

	public User(String cpf, String nome, Date dataDeNascimento, Contato contato, Endereco endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.contato = contato;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Emprestimo> getEmprestimo() {
		return emprestimos;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimos.add(emprestimo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
