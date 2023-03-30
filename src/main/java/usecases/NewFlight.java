package usecases;

import entities.Flight;
import entities.Plane;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.FlightsDAO;

import java.io.Serializable;

@ViewScoped
@Named
@Getter @Setter
public class NewFlight implements Serializable {
    @Inject
    private FlightsDAO flightsDAO;

    @Getter @Setter
    private Flight flight;

    @PostConstruct
    public void init(){
        flight = new Flight();
    }

    public void assignPlane(Plane plane){
        flight.setPlane(plane);
    }

    @Transactional
    public String createFlight(){
        this.flightsDAO.persist(flight);
        return "flight?faces-redirect=true";
    }
}
