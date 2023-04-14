package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "logs", schema = "public")
public class Log {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
}
