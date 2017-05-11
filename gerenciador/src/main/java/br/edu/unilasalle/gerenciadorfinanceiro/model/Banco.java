package br.edu.unilasalle.gerenciadorfinanceiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BANCOS")
@SequenceGenerator(name= "BANCOS_SEQUENCE", sequenceName = "BANCOS_SEQ", initialValue=1, allocationSize = 1)
public class Banco {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANCOS_SEQUENCE")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME", length=100)
	private String nome;

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
}
