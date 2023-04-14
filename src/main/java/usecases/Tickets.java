package usecases;

import entities.Flight;
import entities.Ticket;
import interceptors.MyInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.TicketsDAO;
import services.TicketTaxGenerator;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
@Getter @Setter
public class Tickets implements Serializable {
    @Inject
    private TicketsDAO ticketsDAO;

    @Inject
    private TicketTaxGenerator ticketTaxGenerator;

    @Getter @Setter
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

    public void assignFlight(Flight flight){
        this.ticketToCreate.setFlight(flight);
    }

    @Transactional
    @MyInterceptor
    public String createTicket(){
        ticketToCreate.setPrice(ticketTaxGenerator.calculateTax(ticketToCreate.getPrice()));
        this.ticketsDAO.persist(ticketToCreate);
        return "ticket?faces-redirect=true";
    }

    @Transactional
    public String deleteTicket(Ticket ticketToDelete){
        this.ticketsDAO.delete(ticketToDelete);
        return "ticket?faces-redirect=true";
    }
}
