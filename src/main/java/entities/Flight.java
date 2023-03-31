package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Flight.findAll", query = "select a from Flight as a"),
        @NamedQuery(name = "Flight.findAllFlightEmployees", query = "select a.employees from Flight as a where a.flight_id = :flight_id")
})
@Table(name = "Flight", schema = "public")
public class Flight {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer flight_id;

    @OneToMany(mappedBy = "flight")
    @Getter @Setter
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    @Getter @Setter
    private Plane plane;

    @Size(max = 50)
    @Column(name = "name")
    @NotNull
    @Getter @Setter
    private String name;

    @Size(max = 50)
    @Column(name = "departure_airport")
    @NotNull
    @Getter @Setter
    private String departure_airport;

    @Size(max = 50)
    @Column(name = "arrival_airport")
    @NotNull
    @Getter @Setter
    private String arrival_airport;

    @Column(name = "departure_time")
    @NotNull
    @Getter @Setter
    private Integer departure_time;

    @Column(name = "arrival_time")
    @NotNull
    @Getter @Setter
    private Integer arrival_time;

    @ManyToMany
    @JoinTable(name = "employee_flight", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @Getter @Setter
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public Integer PlaneIdGetter(){
        return plane.getPlane_id();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flight_id, flight.flight_id) && Objects.equals(name, flight.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight_id, name);
    }
}
