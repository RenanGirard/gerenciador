package br.edu.unilasalle;

import java.util.List;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.CategoriaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;

public class TesteCategorias {

	public static void main(String[] args) {
		String nomes[] = {"Transporte", "Transporte Particular", "Saude", "Lazer", "refeicao"};
		String complementos[] = {"Despesa com transporte publico", "Despesa com transporte pr�prio", null, "Atividades com a Familia", null};
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		for (int i = 0; i < nomes.length; i++){
			Categoria categoria = new Categoria();
			categoria.setNome(nomes[i]);
			categoria.setComplemento(complementos[i]);
			categoriaDAO.insert(categoria);			
		}
		
		List<Categoria> categorias = categoriaDAO.selectAll();
		for (Categoria categoria : categorias){
			System.out.println("Id: " + categoria.getId() + " Nome: " + categoria.getNome() + " Complemento: " + categoria.getComplemento());
		}
	}

}
