package usecases.mybatis;

import jakarta.annotation.PostConstruct;

import jakarta.enterprise.inject.Model;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import mybatis.dao.TicketMapper;
import mybatis.model.Ticket;

import java.io.Serializable;
import java.util.List;

//@ViewScoped
//@Named
//@Getter @Setter
@Model
public class TicketsMybatis{

    //@Inject
    // Commented out for now because does not want to work. Error when trying to inject:
    // WELD-001408: Unsatisfied dependencies for type TicketMapper with qualifiers @Default
    // at injection point [BackedAnnotatedField] @Inject private usecases.mybatis.TicketsMybatis.ticketMapper
    private TicketMapper ticketMapper;

    @Getter @Setter
    private List<Ticket> allTickets;

    @Getter @Setter
    private Ticket ticketToUpdate = new Ticket();

    public void loadTickets() {
        this.allTickets = ticketMapper.selectAll();
    }

    @PostConstruct
    public void init(){
        this.loadTickets();
    }

    @Transactional
    public String deleteTicket(Integer ticket_id){
        ticketMapper.deleteByPrimaryKey(ticket_id);
        return "/mybatis/ticket?faces-redirect=true";
    }

    @Transactional
    public String updateTicket(){
        ticketMapper.updateByPrimaryKey(ticketToUpdate);
        return "/mybatis/ticket?faces-redirect=true";
    }
}
