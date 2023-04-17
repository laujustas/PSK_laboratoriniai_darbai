package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Ticket.findAll", query = "select a from Ticket as a")
})
@Table(name = "Ticket", schema = "public")
public class Ticket {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer ticket_id;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @Getter @Setter
    private Flight flight;

    @Column(name = "seat")
    @Getter @Setter
    private Integer seat;

    @Column(name = "ticket_class")
    @Getter @Setter
    private String ticket_class;

    @Column(name = "price")
    @Getter @Setter
    private Double price;

    @Column(name = "is_bought")
    @Getter @Setter
    private boolean is_bought;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticket_id, ticket.ticket_id) && Objects.equals(seat, ticket.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket_id, seat);
    }
}
