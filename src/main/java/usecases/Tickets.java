package usecases;

import entities.Ticket;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.TicketsDAO;

import java.util.List;

@Model
public class Tickets {
    @Inject
    private TicketsDAO ticketsDAO;

    @Getter
    @Setter
    private Ticket ticketToCreate = new Ticket();

    @Getter @Setter
    private List<Ticket> allTickets;

    @PostConstruct
    public void init(){
        loadTickets();
    }

    public void loadTickets(){
        this.allTickets = ticketsDAO.loadAll();
    }

    @Transactional
    public String createTicket(){
        this.ticketsDAO.persist(ticketToCreate);
        return "ticket?faces-redirect=true";
    }

    @Transactional
    public String deleteTicket(Ticket ticketToDelete){
        this.ticketsDAO.delete(ticketToDelete);
        return "ticket?faces-redirect=true";
    }
}
