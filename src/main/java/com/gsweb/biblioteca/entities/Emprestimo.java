package com.gsweb.biblioteca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codEmprestimo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataSaida;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDevolucaoPrevista;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataDevolucao;
	private Double multaAtraso;
	
	@ManyToOne
	@JoinColumn(name = "cpf_cliente")
	private User cliente;
	
	@ManyToMany
	@JoinTable(name = "tb_emprestimo_livro", 
	joinColumns = @JoinColumn(name = "emprestimo_cod"),
	inverseJoinColumns = @JoinColumn(name = "livro_isbn"))
	private Set<Livro> livros;
	
	public Emprestimo() {	
	}

	public Emprestimo(Long codEmprestimo, Date dataSaida, Date dataDevolucaoPrevista, Double multaAtraso) {
		this.codEmprestimo = codEmprestimo;
		this.dataSaida = dataSaida;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.multaAtraso = multaAtraso;
	}

	public Long getCodEmprestimo() {
		return codEmprestimo;
	}

	public void setCodEmprestimo(Long codEmprestimo) {
		this.codEmprestimo = codEmprestimo;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Double getMultaAtraso() {
		return multaAtraso;
	}

	public void setMultaAtraso(Double multaAtraso) {
		this.multaAtraso = multaAtraso;
	}

	public Date getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = new HashSet<>(livros);
	}
	
	@JsonIgnore
	public User getCliente() {
		return cliente;
	}

	public void setCliente(User cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmprestimo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		return Objects.equals(codEmprestimo, other.codEmprestimo);
	}
	
	public void calcularMulta() {
		if (dataDevolucao != null) {
			long diferencaEmMilissegundos = dataDevolucao.getTime() - dataDevolucaoPrevista.getTime();
			long dias = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);
			setMultaAtraso(1.5 * dias);
		} 
	}

}
