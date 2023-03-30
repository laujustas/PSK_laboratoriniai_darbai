package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Plane.findAll", query = "select a from Plane as a")
})
@Table(name = "Plane", schema = "public")
public class Plane {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer plane_id;

    @Size(max = 50)
    @Column(name = "model")
    @Getter @Setter
    private String model;

    @Size(max = 7)
    @Column(name = "registration")
    @Getter @Setter
    private String registration;

    @Column(name = "flight_hours")
    @Getter @Setter
    private Integer flightHours;

    @OneToMany(mappedBy = "plane")
    @Getter @Setter
    private List<Flight> flights;

    public Plane() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(plane_id, plane.plane_id) && Objects.equals(registration, plane.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plane_id, registration);
    }
}
