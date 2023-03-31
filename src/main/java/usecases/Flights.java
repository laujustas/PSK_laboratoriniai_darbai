package usecases;

import entities.Employee;
import entities.Flight;
import entities.Plane;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeesDAO;
import persistence.FlightsDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class Flights implements Serializable {
    @Inject
    private FlightsDAO flightsDAO;

    @Inject
    private EmployeesDAO employeesDAO;

    @Getter
    @Setter
    private Flight flightToCreate = new Flight();

    @Getter @Setter
    private Plane plane;

    @Getter @Setter
    private List<Flight> allFlights;

    @Getter
    private List<Flight> planeFlights;

    @Getter @Setter
    private List<Employee> flightEmployees;

    @Getter @Setter
    private Employee flightPilot;

    @PostConstruct
    public void init(){
        loadFlights();
    }

    public void loadFlights(){
        this.allFlights = flightsDAO.loadAll();
    }

    public void loadEmployeesByFlightId(Integer flightId){
        this.flightEmployees = flightsDAO.findFlightEmployees(flightId);
    }

    public void loadFlightsByPlane(Integer plane_id) {
        planeFlights = new ArrayList<>();
        for(Flight flight : this.allFlights){
            if(flight.PlaneIdGetter() == plane_id){
                this.planeFlights.add(flight);
            }
        }
    }

    public void loadFlightPilot(Integer flight_id){
        Employee pilot = flightsDAO.findFlightPilot(flight_id);
        if(pilot != null) {
            flightPilot = pilot;
        }
    }

    @Transactional
    public String createFlight(){
        this.flightsDAO.persist(flightToCreate);
        return "flight?faces-redirect=true";
    }

    @Transactional
    public String assignEmployee(Integer employee_id, Integer flight_id){
        Employee employee = employeesDAO.findEmployee(employee_id);
        if(employee == null){
            return "not found";
        }
        Flight flight = flightsDAO.findFlight(flight_id);
        flight.addEmployee(employee);
        flightsDAO.persist(flight);
        return "flight?faces-redirect=true";
    }

    @Transactional
    public String deleteFlight(Flight flightToDelete){
        this.flightsDAO.delete(flightToDelete);
        return "flight?faces-redirect=true";
    }
}
