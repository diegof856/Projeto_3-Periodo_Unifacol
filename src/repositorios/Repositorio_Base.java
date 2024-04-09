package repositorios;

import java.util.ArrayList;
import java.util.List;

import interfacea.IBaseRepositorio;

public class Repositorio_Base<Qualquer> implements IBaseRepositorio<Qualquer> {
	public List<Qualquer> entidades;
	
	public Repositorio_Base() {
        this.entidades = new ArrayList<>();
    }
	@Override
	public void adicionarLista(Qualquer entidade) {
		entidades.add(entidade);
		
	}

	@Override
	public List<Qualquer> verLista() {
		return entidades;

	} 
	
	

}
