package persistence;

import entities.Flight;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FlightsDAO {
    @Inject
    private EntityManager em;

    public List<Flight> loadAll(){
        return em.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }

    public void persist(Flight flight){
        this.em.persist(flight);
    }

    public void delete(Flight flight) { this.em.remove(flight); }
}
