package br.edu.unilasalle.gerenciadorfinanceiro.beans;

public class BancoBean {

	private long id;
	private String nome;

	public BancoBean() {
		// TODO Auto-generated constructor stub
		super();
	}

	public BancoBean(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
