package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "select a from Employee as a")
})
@Table(name = "Employee", schema = "public")
public class Employee {

    @Id
    @GeneratedValue
    @Getter @Setter
    private Integer employee_id;

    @Size(max = 50)
    @Column(name = "name")
    @NotNull
    @Getter @Setter
    private String name;

    @Size(max = 50)
    @Column(name = "surname")
    @NotNull
    @Getter @Setter
    private String surname;

    @Size(max = 20)
    @Column(name = "cell_phone")
    @Getter @Setter
    private String cellPhone;

    @Size(max = 50)
    @Column(name = "role")
    @NotNull
    @Getter @Setter
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
