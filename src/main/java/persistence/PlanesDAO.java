package persistence;

import entities.Plane;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PlanesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Plane> loadAll() {
        return em.createNamedQuery("Plane.findAll", Plane.class).getResultList();
//        Query q = em.createQuery("SELECT p FROM Plane p ");
//        List results = q.getResultList();
//        return results;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Plane plane){
        this.em.persist(plane);
    }
}
