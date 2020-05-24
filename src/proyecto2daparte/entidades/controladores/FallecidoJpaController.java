/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.entidades.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import proyecto2daparte.entidades.Fallecido;
import proyecto2daparte.entidades.FallecidoPK;
import proyecto2daparte.entidades.Region;
import proyecto2daparte.entidades.controladores.exceptions.NonexistentEntityException;
import proyecto2daparte.entidades.controladores.exceptions.PreexistingEntityException;

/**
 *
 * @author USER
 */
public class FallecidoJpaController implements Serializable {

    public FallecidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fallecido fallecido) throws PreexistingEntityException, Exception {
        if (fallecido.getFallecidoPK() == null) {
            fallecido.setFallecidoPK(new FallecidoPK());
        }
        fallecido.getFallecidoPK().setRegion(fallecido.getRegion1().getNombre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Region region1 = fallecido.getRegion1();
            if (region1 != null) {
                region1 = em.getReference(region1.getClass(), region1.getNombre());
                fallecido.setRegion1(region1);
            }
            em.persist(fallecido);
            if (region1 != null) {
                region1.getFallecidoList().add(fallecido);
                region1 = em.merge(region1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFallecido(fallecido.getFallecidoPK()) != null) {
                throw new PreexistingEntityException("Fallecido " + fallecido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fallecido fallecido) throws NonexistentEntityException, Exception {
        fallecido.getFallecidoPK().setRegion(fallecido.getRegion1().getNombre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fallecido persistentFallecido = em.find(Fallecido.class, fallecido.getFallecidoPK());
            Region region1Old = persistentFallecido.getRegion1();
            Region region1New = fallecido.getRegion1();
            if (region1New != null) {
                region1New = em.getReference(region1New.getClass(), region1New.getNombre());
                fallecido.setRegion1(region1New);
            }
            fallecido = em.merge(fallecido);
            if (region1Old != null && !region1Old.equals(region1New)) {
                region1Old.getFallecidoList().remove(fallecido);
                region1Old = em.merge(region1Old);
            }
            if (region1New != null && !region1New.equals(region1Old)) {
                region1New.getFallecidoList().add(fallecido);
                region1New = em.merge(region1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FallecidoPK id = fallecido.getFallecidoPK();
                if (findFallecido(id) == null) {
                    throw new NonexistentEntityException("The fallecido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FallecidoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fallecido fallecido;
            try {
                fallecido = em.getReference(Fallecido.class, id);
                fallecido.getFallecidoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fallecido with id " + id + " no longer exists.", enfe);
            }
            Region region1 = fallecido.getRegion1();
            if (region1 != null) {
                region1.getFallecidoList().remove(fallecido);
                region1 = em.merge(region1);
            }
            em.remove(fallecido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fallecido> findFallecidoEntities() {
        return findFallecidoEntities(true, -1, -1);
    }

    public List<Fallecido> findFallecidoEntities(int maxResults, int firstResult) {
        return findFallecidoEntities(false, maxResults, firstResult);
    }

    private List<Fallecido> findFallecidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fallecido.class));
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

    public Fallecido findFallecido(FallecidoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fallecido.class, id);
        } finally {
            em.close();
        }
    }

    public int getFallecidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fallecido> rt = cq.from(Fallecido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
