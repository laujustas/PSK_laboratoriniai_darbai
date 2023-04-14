package persistence;

import entities.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class LoggerDAO {
    @Inject
    private EntityManager em;

    public void persist(Log log){
        this.em.persist(log);
    }
}
