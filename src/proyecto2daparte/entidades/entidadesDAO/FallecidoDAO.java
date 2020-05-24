package proyecto2daparte.entidades.entidadesDAO;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyecto2daparte.entidades.Fallecido;
import proyecto2daparte.entidades.FallecidoPK;
import proyecto2daparte.entidades.controladores.FallecidoJpaController;

public class FallecidoDAO implements IOperaciones<Fallecido> {
	private final FallecidoJpaController controladorFallecido;
    private final EntityManagerFactory emf;
    
    public FallecidoDAO() {
    	emf = Persistence.createEntityManagerFactory("Proyecto2daPartePU");
        controladorFallecido = new FallecidoJpaController(emf);
    }

	@Override
	public void Agregar(Fallecido objeto) throws Exception {
		controladorFallecido.create(objeto);
		
	}

	@Override
	public void Editar(Fallecido objeto) throws Exception {
		controladorFallecido.edit(objeto);
		
	}

	@Override
	public void Eliminar(Object idObjeto) throws Exception {
		controladorFallecido.destroy((FallecidoPK) idObjeto);
		
	}

	@Override
	public List<Fallecido> Listar() throws Exception {
		return controladorFallecido.findFallecidoEntities();
	}

	@Override
	public Fallecido Encontrar(Object idObjeto) throws Exception {
		return controladorFallecido.findFallecido((FallecidoPK) idObjeto);
	}
}
