package usecases.mybatis;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import mybatis.dao.EmployeeFlightMapper;
import mybatis.dao.EmployeeMapper;
import mybatis.dao.TicketMapper;
import mybatis.model.Employee;
import mybatis.model.Ticket;

import java.util.List;

@Model
public class EmployeesMybatis {

//    @Inject
    private EmployeeMapper employeeMapper;

//    @Inject
    private EmployeeFlightMapper employeeFlightMapper;

    @Getter
    @Setter
    private List<Employee> allEmployees;

    public void loadEmployees() {
        this.allEmployees = employeeMapper.selectAll();
    }

    @PostConstruct
    public void init(){
        this.loadEmployees();
    }

    @Transactional
    public String deleteEmployee(Integer employee_id){
        employeeFlightMapper.deleteByEmployeeId(employee_id);
        employeeMapper.deleteByPrimaryKey(employee_id);
        return "/mybatis/ticket?faces-redirect=true";
    }
}
