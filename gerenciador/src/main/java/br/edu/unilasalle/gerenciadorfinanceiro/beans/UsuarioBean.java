package br.edu.unilasalle.gerenciadorfinanceiro.beans;

public class UsuarioBean {
	private Long id;
	private String nomeUsuario;
	private String nomeCompleto;
	private String senha;
		
	public UsuarioBean() {
		super();
	}
	
	public UsuarioBean(Long id, String nomeUsuario, String nomeCompleto, String senha) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
	}

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
	
}
