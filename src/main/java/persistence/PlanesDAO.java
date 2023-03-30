package persistence;

import entities.Plane;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PlanesDAO {
    @Inject
    private EntityManager em;

    public List<Plane> loadAll() {
        return em.createNamedQuery("Plane.findAll", Plane.class).getResultList();
    }

    public void persist(Plane plane){
        this.em.persist(plane);
    }

    public void delete(Plane plane){
        this.em.remove(plane);
    }
}
