package proyecto2daparte.entidades.entidadesDAO;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import proyecto2daparte.entidades.Region;
import proyecto2daparte.entidades.controladores.RegionJpaController;

public class RegionDAO {
	
	private final RegionJpaController controladorRegion;
    private final EntityManagerFactory emf;
    
    public RegionDAO() {
    	emf = Persistence.createEntityManagerFactory("Proyecto2daPartePU");
        controladorRegion = new RegionJpaController(emf);
    }

	public void Agregar(Region region) throws Exception {
		controladorRegion.create(region);
		
	}

	public void Editar(Region region) throws Exception {
		controladorRegion.edit(region);
		
	}

	public void Eliminar(String idRegion) throws Exception {
		controladorRegion.destroy(idRegion);
		
	}

	public List<Region> Listar() throws Exception {
		return controladorRegion.findRegionEntities();
	}

	public Region Encontrar(String idRegion) throws Exception {
		return controladorRegion.findRegion(idRegion);
	}
}
