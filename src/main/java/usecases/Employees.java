package usecases;

import entities.Employee;
import entities.Flight;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeesDAO;
import persistence.FlightsDAO;

import java.util.List;

@Model
public class Employees {
    @Inject
    private EmployeesDAO employeesDAO;

    @Inject
    private FlightsDAO flightsDAO;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @Getter @Setter
    private List<Employee> allEmployees;

    @Getter @Setter
    private List<Flight> employeeFlights;

    @PostConstruct
    public void init(){
        loadEmployees();
    }

    public void loadEmployees(){
        this.allEmployees = employeesDAO.loadAll();
    }

    public void loadFlightsByEmployeeId(Integer employee_id){
        this.employeeFlights = employeesDAO.findEmployeeFlights(employee_id);
    }

    @Transactional
    public String createEmployee(){
        this.employeesDAO.persist(employeeToCreate);
        return "employee?faces-redirect=true";
    }

    @Transactional
    public String deleteEmployee(Employee employeeToDelete){
        this.employeesDAO.delete(employeeToDelete);
        return "employee?faces-redirect=true";
    }
}
