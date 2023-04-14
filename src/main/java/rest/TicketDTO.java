package rest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketDTO {
    private Integer seat;

    private String ticket_class;

    private Double price;

    private boolean is_bought;

    private String flight_name;
}
