package br.edu.unilasalle.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ITENS_LANCAMNTO")
@SequenceGenerator(name="ITENS_LANCAMENTO_SEQUENCE", sequenceName="ITENS_LANCAMENTO_SEQ",initialValue=1,allocationSize=1)
public class ItemLancamento {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ITENS_LANCAMENTO_SEQUENCE")
	private Long id;
	
	@Column(name="DATA")
	private Date data;
	
	@Column(name="VALOR")
	private BigDecimal valor;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "LANCAMENTO_ID", foreignKey = @ForeignKey(name = "ITENS_LANCAMENTO_FK"))
	private Lancamento lancamento;
	
	
}
