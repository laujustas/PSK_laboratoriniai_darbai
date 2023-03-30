package persistence;

import entities.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class EmployeesDAO {

    @Inject
    private EntityManager em;

    public List<Employee> loadAll(){
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public Employee findEmployee(Integer employee_id){
        return em.find(Employee.class, employee_id);
    }

    public void persist(Employee employee){
        this.em.persist(employee);
    }

    public void delete(Employee employee){
        this.em.remove(employee);
    }

    public void update(Employee employee) { this.em.merge(employee); }
}
