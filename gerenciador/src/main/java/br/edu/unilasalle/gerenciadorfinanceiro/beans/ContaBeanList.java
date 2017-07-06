package br.edu.unilasalle.gerenciadorfinanceiro.beans;

import java.util.ArrayList;
import java.util.List;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;

public class ContaBeanList {

	private List<ContaBean> contasBeanList = new ArrayList<ContaBean>();

	public ContaBeanList() {
		// TODO Auto-generated constructor stub
	}

	public ContaBeanList(List<Conta> contas) {
		// TODO Auto-generated constructor stub
		for (Conta conta : contas) {
			contasBeanList.add(new ContaBean(conta.getId(), conta.getNumero(), conta.getBanco()));
		}
	}

	public List<ContaBean> getContasBeanList() {
		return contasBeanList;
	}

	public void setContasBeanList(List<ContaBean> contasBeanList) {
		this.contasBeanList = contasBeanList;
	}


}
