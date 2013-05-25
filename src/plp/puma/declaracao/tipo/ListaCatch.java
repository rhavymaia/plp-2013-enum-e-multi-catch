package plp.puma.declaracao.tipo;

import java.util.ArrayList;
import java.util.List;

import plp.puma.comando.Catch;
import plp.puma.util.Tipo;

public class ListaCatch {
	public List<Catch> catchs;

	public ListaCatch(Catch c) {
		this.catchs = new ArrayList<Catch>();
		this.catchs.add(c);
	}
	
	public ListaCatch(Catch c, ListaCatch catchs) {
		this.catchs = catchs.getCatchs();
		this.catchs.add(c);
	}
	
	public List<Catch> getCatchs() {
		return catchs;
	}
}