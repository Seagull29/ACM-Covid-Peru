/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.entidades.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import proyecto2daparte.entidades.Fallecido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import proyecto2daparte.entidades.Region;
import proyecto2daparte.entidades.controladores.exceptions.IllegalOrphanException;
import proyecto2daparte.entidades.controladores.exceptions.NonexistentEntityException;
import proyecto2daparte.entidades.controladores.exceptions.PreexistingEntityException;

/**
 *
 * @author USER
 */
public class RegionJpaController implements Serializable {

    public RegionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Region region) throws PreexistingEntityException, Exception {
        if (region.getFallecidoList() == null) {
            region.setFallecidoList(new ArrayList<Fallecido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fallecido> attachedFallecidoList = new ArrayList<Fallecido>();
            for (Fallecido fallecidoListFallecidoToAttach : region.getFallecidoList()) {
                fallecidoListFallecidoToAttach = em.getReference(fallecidoListFallecidoToAttach.getClass(), fallecidoListFallecidoToAttach.getFallecidoPK());
                attachedFallecidoList.add(fallecidoListFallecidoToAttach);
            }
            region.setFallecidoList(attachedFallecidoList);
            em.persist(region);
            for (Fallecido fallecidoListFallecido : region.getFallecidoList()) {
                Region oldRegion1OfFallecidoListFallecido = fallecidoListFallecido.getRegion1();
                fallecidoListFallecido.setRegion1(region);
                fallecidoListFallecido = em.merge(fallecidoListFallecido);
                if (oldRegion1OfFallecidoListFallecido != null) {
                    oldRegion1OfFallecidoListFallecido.getFallecidoList().remove(fallecidoListFallecido);
                    oldRegion1OfFallecidoListFallecido = em.merge(oldRegion1OfFallecidoListFallecido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegion(region.getNombre()) != null) {
                throw new PreexistingEntityException("Region " + region + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Region region) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            region = em.merge(region);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = region.getNombre();
                if (findRegion(id) == null) {
                    throw new NonexistentEntityException("The region with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Region region;
            try {
                region = em.getReference(Region.class, id);
                region.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The region with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Fallecido> fallecidoListOrphanCheck = region.getFallecidoList();
            for (Fallecido fallecidoListOrphanCheckFallecido : fallecidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Region (" + region + ") cannot be destroyed since the Fallecido " + fallecidoListOrphanCheckFallecido + " in its fallecidoList field has a non-nullable region1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(region);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Region> findRegionEntities() {
        return findRegionEntities(true, -1, -1);
    }

    public List<Region> findRegionEntities(int maxResults, int firstResult) {
        return findRegionEntities(false, maxResults, firstResult);
    }

    private List<Region> findRegionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Region.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Region findRegion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Region.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Region> rt = cq.from(Region.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
