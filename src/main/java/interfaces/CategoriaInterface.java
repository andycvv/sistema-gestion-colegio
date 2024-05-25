package interfaces;


import java.util.ArrayList;

import entidad.Categoria;

public interface CategoriaInterface {
	public int registrarCategoria(Categoria a);
	public int editarCategoria(Categoria a);
	public int eliminarCategoria(int idCategoria);
	public ArrayList<Categoria> listado();
}
