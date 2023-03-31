package persistence;

import entities.Employee;
import entities.Flight;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FlightsDAO {
    @Inject
    private EntityManager em;

    public List<Flight> loadAll(){
        return em.createNamedQuery("Flight.findAll", Flight.class).getResultList();
    }

    public Flight findFlight(Integer flight_id){
        return em.find(Flight.class, flight_id);
    }

    public void persist(Flight flight){
        this.em.persist(flight);
    }

    public void delete(Flight flight) { this.em.remove(flight); }

    public List<Employee> findFlightEmployees(Integer flightId) {
        return em.createNamedQuery("Flight.findAllFlightEmployees")
                .setParameter("flight_id", flightId)
                .getResultList();
    }

    public Employee findFlightPilot(Integer flightId) {
        List<Employee> flightEmployees = em.createNamedQuery("Flight.findAllFlightEmployees")
                .setParameter("flight_id", flightId)
                .getResultList();

        for(Employee employee : flightEmployees){
            if(employee.getRole().equals("pilot")){
                return employee;
            }
        }

        return new Employee();
    }
}
