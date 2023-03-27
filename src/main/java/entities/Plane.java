package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
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

    public Plane() {
    }

    public Plane(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Objects.equals(plane_id, plane.plane_id) &&
                Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plane_id, model);
    }
}
