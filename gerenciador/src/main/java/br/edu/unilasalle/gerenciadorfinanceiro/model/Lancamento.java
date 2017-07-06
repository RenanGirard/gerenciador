package br.edu.unilasalle.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LANCAMENTOS")
@SequenceGenerator(name = "LANCAMENTOS_SEQUENCE", sequenceName = "LANCAMENTOS_SEQ", initialValue = 1, allocationSize = 1)
public class Lancamento {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANCAMENTOS_SEQUENCE")
	private Long id;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "TIPO")
	private String tipo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CRIACAO")
	private Date dataDeCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_LANCAMENTO")
	private Date dataDeLancamento;

	@Column(name = "VALOR_LANCAMENTO")
	private BigDecimal valorLancamento;

	@Column(name = "FIXO")
	private Boolean fixo;

	@ManyToOne
	@JoinColumn(name = "USUARIO_ID", foreignKey = @ForeignKey(name = "USUARIO_LANCAMENTO_FK"))
	private Usuario usuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CONTA_ID", foreignKey = @ForeignKey(name = "CONTA_LANCAMENTO_FK"))
	private Conta conta;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CATEGORIA_ID", foreignKey = @ForeignKey(name = "CATEGORIA_LANCAMENTO_FK"))
	private Categoria categoria;

	@OneToMany
	@JoinColumn(name = "USUARIO_ID", foreignKey = @ForeignKey(name = "USUARIO_LANCAMENTO_FK"))
	private Collection<ItemLancamento> itens;

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

	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public BigDecimal getValorLancamento() {
		return valorLancamento;
	}

	public void setValorLancamento(BigDecimal valorLancamento) {
		this.valorLancamento = valorLancamento;
	}

	public Boolean getFixo() {
		return fixo;
	}

	public void setFixo(Boolean fixo) {
		this.fixo = fixo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Collection<ItemLancamento> getItens() {
		return itens;
	}

	public void setItens(Collection<ItemLancamento> itens) {
		this.itens = itens;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(Date dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

}
