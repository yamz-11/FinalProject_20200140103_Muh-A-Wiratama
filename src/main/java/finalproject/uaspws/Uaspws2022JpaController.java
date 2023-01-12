/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.uaspws;

import finalproject.uaspws.exceptions.NonexistentEntityException;
import finalproject.uaspws.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Wiratama
 */
public class Uaspws2022JpaController implements Serializable {

    public Uaspws2022JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalproject_uaspws_jar_0.0.1-SNAPSHOTPU");

    public Uaspws2022JpaController() {
        
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    public void create(Uaspws2022 uaspws2022) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uaspws2022);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUaspws2022(uaspws2022.getId()) != null) {
                throw new PreexistingEntityException("Uaspws2022 " + uaspws2022 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uaspws2022 uaspws2022) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uaspws2022 = em.merge(uaspws2022);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = uaspws2022.getId();
                if (findUaspws2022(id) == null) {
                    throw new NonexistentEntityException("The uaspws2022 with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uaspws2022 uaspws2022;
            try {
                uaspws2022 = em.getReference(Uaspws2022.class, id);
                uaspws2022.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uaspws2022 with id " + id + " no longer exists.", enfe);
            }
            em.remove(uaspws2022);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uaspws2022> findUaspws2022Entities() {
        return findUaspws2022Entities(true, -1, -1);
    }

    public List<Uaspws2022> findUaspws2022Entities(int maxResults, int firstResult) {
        return findUaspws2022Entities(false, maxResults, firstResult);
    }

    private List<Uaspws2022> findUaspws2022Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uaspws2022.class));
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

    public Uaspws2022 findUaspws2022(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uaspws2022.class, id);
        } finally {
            em.close();
        }
    }

    public int getUaspws2022Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uaspws2022> rt = cq.from(Uaspws2022.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
