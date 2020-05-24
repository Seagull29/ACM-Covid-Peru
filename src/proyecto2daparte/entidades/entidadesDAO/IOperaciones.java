package proyecto2daparte.entidades.entidadesDAO;

import java.util.List;

public interface IOperaciones<Entidad> {
	public void Agregar(Entidad objeto) throws Exception;
	public void Editar(Entidad objeto) throws Exception;
	public void Eliminar(Object idObjeto) throws Exception;
	public List<Entidad> Listar() throws Exception;
	public Entidad Encontrar(Object idObjeto) throws Exception;
}
