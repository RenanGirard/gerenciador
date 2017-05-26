package br.edu.unilasalle.gerenciadorfinanceiro.beans;

import java.util.ArrayList;
import java.util.List;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

public class UsuarioBeanList {
	private List<UsuarioBean> usuariosBeanList = new ArrayList<UsuarioBean>();
	
	public UsuarioBeanList() {
		
	}
	
	public UsuarioBeanList(List<Usuario> usuariosList) {
		for (Usuario usuario : usuariosList){
			usuariosBeanList.add(new UsuarioBean(usuario.getId(),usuario.getNomeCompleto()
					, usuario.getNomeUsuario(), usuario.getSenha()));
		}
	}

	public List<UsuarioBean> getUsuariosBeanList() {
		return usuariosBeanList;
	}

	public void setUsuariosBeanList(List<UsuarioBean> usuariosBeanList) {
		this.usuariosBeanList = usuariosBeanList;
	}
	
	
}
