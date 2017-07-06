package br.edu.unilasalle.gerenciadorfinanceiro.beans;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Banco;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

public class ContaBean {
	private Long id;
	private int numero;
	private Banco banco;

	public ContaBean() {
		// TODO Auto-generated constructor stub
		super();
	}

	public ContaBean(Long id, int numero, Banco banco) {
		super();
		this.id = id;
		this.numero = numero;
		this.banco = banco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
