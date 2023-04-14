package persistence;

import entities.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class TicketsDAO {
    @Inject
    private EntityManager em;

    public List<Ticket> loadAll(){
        return em.createNamedQuery("Ticket.findAll", Ticket.class).getResultList();
    }

    public Ticket findTicketById(Integer ticketId){ return this.em.find(Ticket.class, ticketId); }

    public void persist(Ticket ticket){
        this.em.persist(ticket);
    }

    public void delete(Ticket ticket){
        this.em.remove(ticket);
    }

    public void update(Ticket ticket) { this.em.merge(ticket); }
}
