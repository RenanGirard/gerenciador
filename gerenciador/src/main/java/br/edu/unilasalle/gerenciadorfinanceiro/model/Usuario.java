package br.edu.unilasalle.gerenciadorfinanceiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS")
@SequenceGenerator(name= "USUARIOS_SEQUENCE", sequenceName = "USUARIOS_SEQ", initialValue=2, allocationSize = 1)
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOS_SEQUENCE")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME_COMPLETO")
	private String nomeCompleto;
	
	@Column(name="NOME_USUARIO")
	private String nomeUsuario;
	
	@Column(name="SENHA")
	private String senha;

/*	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONTA_ID", foreignKey=@ForeignKey(name="CONTA_USUARIO_FK"))
	private Collection<Conta> contas;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="LANCAMENTO_ID", foreignKey=@ForeignKey(name="LANCAMENTO_USUARIO_FK"))
	private Collection<Lancamento>  lancamento;*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

/*	public Collection<Conta> getContas() {
		return contas;
	}

	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}

	public Collection<Lancamento> getLancamento() {
		return lancamento;
	}

	public void setLancamento(Collection<Lancamento> lancamento) {
		this.lancamento = lancamento;
	}*/

}
