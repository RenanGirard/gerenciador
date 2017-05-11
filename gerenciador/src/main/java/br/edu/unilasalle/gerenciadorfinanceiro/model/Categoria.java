package br.edu.unilasalle.gerenciadorfinanceiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIAS")
@SequenceGenerator(name= "CATEGORIAS_SEQUENCE", sequenceName = "CATEGORIAS_SEQ", initialValue=1, allocationSize = 1)
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIAS_SEQUENCE")
	@Column(name = "ID")
	private Long id;
	
	@Column(name="NOME", length=100)
	private String nome;
	
	@Column(name="COMPLEMENTO", length=100, nullable=true)
	private String complemento;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
