package usecases;

import entities.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeesDAO;

import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateEmployee implements Serializable {
    @Inject
    private EmployeesDAO employeesDAO;

    @Getter @Setter
    private Employee employee;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer employee_id = Integer.parseInt(requestParameters.get("employee_id"));
        this.employee = employeesDAO.findEmployee(employee_id);
    }

    @Transactional
    public String updateEmployee(){
        this.employeesDAO.update(employee);
        return "employee?faces-redirect=true";
    }
}
