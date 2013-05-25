package plp.puma.declaracao.tipo;

import java.util.ArrayList;
import java.util.List;

import plp.puma.util.Tipo;

public class ListaTipoExcecao {
	public List<Tipo> tipos;

	public ListaTipoExcecao(Tipo tipo) {
		this.tipos = new ArrayList<Tipo>();
		this.tipos.add(tipo);
	}
	
	public ListaTipoExcecao(Tipo tipo, ListaTipoExcecao tipos) {
		this.tipos = tipos.getTipos();
		this.tipos.add(tipo);
	}

	public boolean existeTipo(Tipo tipo) {
		for(Tipo t: this.tipos){	
			if (t.getTipo().equals(tipo)) {
				return true;
			}
		}
		return false;
	}
	
	public List<Tipo> getTipos() {
		return tipos;
	}
}