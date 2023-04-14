package rest;

import entities.Ticket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;
import persistence.TicketsDAO;

@ApplicationScoped
@Path("/tickets")
public class TicketController {
    @Inject
    @Getter @Setter
    private TicketsDAO ticketsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicketById(@PathParam("id") final Integer id){
        Ticket ticket = ticketsDAO.findTicketById(id);
        if(ticket == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSeat(ticket.getSeat());
        ticketDTO.setTicket_class(ticket.getTicket_class());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.set_bought(ticket.is_bought());
        ticketDTO.setFlight_name(ticket.getFlight().getName());

        return Response.ok(ticketDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer id, @Valid TicketDTO ticketDTO){
        Ticket ticketToUpdate = ticketsDAO.findTicketById(id);
        if(ticketToUpdate == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ticketToUpdate.set_bought(ticketDTO.is_bought());
        ticketToUpdate.setSeat(ticketDTO.getSeat());
        ticketToUpdate.setPrice(ticketDTO.getPrice());
        ticketToUpdate.setTicket_class(ticketDTO.getTicket_class());

        ticketsDAO.update(ticketToUpdate);
        return Response.ok().build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response post(@Valid TicketDTO ticketDTO){
        Ticket ticketToPost = new Ticket();
        ticketToPost.set_bought(ticketDTO.is_bought());
        ticketToPost.setSeat(ticketDTO.getSeat());
        ticketToPost.setTicket_class(ticketDTO.getTicket_class());
        ticketToPost.setPrice(ticketDTO.getPrice());

        ticketsDAO.persist(ticketToPost);
        return Response.ok(ticketToPost).build();
    }

}
