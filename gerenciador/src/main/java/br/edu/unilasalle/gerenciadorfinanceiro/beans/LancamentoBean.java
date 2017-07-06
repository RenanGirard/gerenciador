package br.edu.unilasalle.gerenciadorfinanceiro.beans;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

public class LancamentoBean {

	private Long id;
	private String descricao;
	private String tipo;
	private BigDecimal valorLancamento;
	private Date dataDeCriacao;
	private Categoria categoria;
	private Conta conta;
	private Boolean fixo;
	private Date dataDeLancamento;
	private Usuario usuario;

	public LancamentoBean() {
		// TODO Auto-generated constructor stub
		super();
	}

	public LancamentoBean(Long id, String descricao, String tipo, BigDecimal valorLancamento, Date dataDeCriacao,
			Categoria categoria, Conta conta, Boolean fixo, Date dataDeLancamento, Usuario usuario) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.valorLancamento = valorLancamento;
		this.dataDeCriacao = dataDeCriacao;
		this.categoria = categoria;
		this.conta = conta;
		this.fixo = fixo;
		this.dataDeLancamento = dataDeLancamento;
		this.usuario = usuario;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Boolean getFixo() {
		return fixo;
	}

	public void setFixo(Boolean fixo) {
		this.fixo = fixo;
	}

	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
