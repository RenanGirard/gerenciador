package br.edu.unilasalle.gerenciadorfinanceiro.beans;

import java.util.ArrayList;
import java.util.List;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Lancamento;

public class LancamentoBeanList {
	List<LancamentoBean> lancamentosBeanList = new ArrayList<LancamentoBean>();
	
	public LancamentoBeanList() {
		// TODO Auto-generated constructor stub
	}
	
	public LancamentoBeanList(List<Lancamento> lancamentos){
			for (Lancamento lancamento : lancamentos) {
				lancamentosBeanList.add(new LancamentoBean(lancamento.getId(), lancamento.getDescricao(), 
						lancamento.getTipo(), lancamento.getValorLancamento(),lancamento.getDataDeCriacao(), 
						lancamento.getCategoria(), lancamento.getConta(), lancamento.getFixo(), 
						lancamento.getDataDeLancamento(), lancamento.getUsuario()));
			}
	}

	public List<LancamentoBean> getLancamentosBeanList() {
		return lancamentosBeanList;
	}

	public void setLancamentosBeanList(List<LancamentoBean> lancamentosBeanList) {
		this.lancamentosBeanList = lancamentosBeanList;
	}
	
}
