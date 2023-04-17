package usecases;

import entities.Ticket;
import interceptors.MyInterceptor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.TicketsDAO;

import java.io.*;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateTicket implements Serializable {
    @Inject
    private TicketsDAO ticketsDAO;

    private Ticket ticket;

    @PostConstruct
    private void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer ticket_id = Integer.parseInt(requestParameters.get("ticketId"));
        this.ticket = ticketsDAO.findTicketById(ticket_id);
        String ticket_class = requestParameters.get("ticketClass");
        if(ticket_class != null){
            this.ticket.setTicket_class(ticket_class);
        }
    }

    @Transactional
    @MyInterceptor
    public String updateTicket(){
        try{
            ticketsDAO.update(this.ticket);
        }catch (OptimisticLockException e){
            return "/ticketDetails.xhtml?faces-redirect=true&ticketId=" + this.ticket.getTicket_id() + "&error=optimistic-lock-exception&ticketClass=" + this.ticket.getTicket_class();
        }
        return "ticket.xhtml?faces-redirect=true";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @MyInterceptor
    public String updateTicketAfterOptLock(){
        Ticket tck = ticketsDAO.findTicketById(this.ticket.getTicket_id());
        tck.setTicket_class(this.ticket.getTicket_class());
        ticketsDAO.update(tck);

        return "ticket.xhtml?faces-redirect=true";
    }
}
