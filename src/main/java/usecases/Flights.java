package usecases;

import entities.Flight;
import entities.Plane;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.FlightsDAO;

import java.util.ArrayList;
import java.util.List;

@Model
public class Flights {
    @Inject
    private FlightsDAO flightsDAO;

    @Getter
    @Setter
    private Flight flightToCreate = new Flight();

    @Getter @Setter
    private Plane plane;

    @Getter @Setter
    private List<Flight> allFlights;

    @Getter
    private List<Flight> planeFlights;

    @PostConstruct
    public void init(){
        loadFlights();
    }

    public void loadFlights(){
        this.allFlights = flightsDAO.loadAll();
    }

    public void loadFlightsByPlane(Integer plane_id) {
        planeFlights = new ArrayList<>();
        for(Flight flight : this.allFlights){
            if(flight.PlaneIdGetter() == plane_id){
                this.planeFlights.add(flight);
            }
        }
    }

    @Transactional
    public String createFlight(){
        this.flightsDAO.persist(flightToCreate);
        return "flight?faces-redirect=true";
    }

    @Transactional
    public String deleteFlight(Flight flightToDelete){
        this.flightsDAO.delete(flightToDelete);
        return "flight?faces-redirect=true";
    }
}
