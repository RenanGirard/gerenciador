package br.edu.unilasalle.gerenciadorfinanceiro.beans;

public class CategoriaBean {

	private Long id;
	private String nome;
	private String complemento;

	public CategoriaBean() {
		super();
	}

	public CategoriaBean(Long id, String nome, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
		this.complemento = complemento;
	}

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
