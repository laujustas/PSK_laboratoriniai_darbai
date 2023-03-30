package usecases;

import entities.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeesDAO;

import java.util.List;

@Model
public class Employees {
    @Inject
    private EmployeesDAO employeesDAO;

    @Getter @Setter
    private Employee employeeToCreate = new Employee();

    @Getter @Setter
    private List<Employee> allEmployees;

    @PostConstruct
    public void init(){
        loadEmployees();
    }

    public void loadEmployees(){
        this.allEmployees = employeesDAO.loadAll();
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
