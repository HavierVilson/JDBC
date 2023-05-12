package sky.project.dao.employeeDAO;

import sky.project.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    Employee findEmployeeById(Integer id) throws SQLException;

    void hireNewEmployee(String firstName, String lastName,
                         Integer age, Integer city_id) throws SQLException;

    List<Employee> getEmployees() throws SQLException;

    void refactorEmployeeById(Integer id, String firstName, String lastName,
                              Integer age, Integer city_id) throws SQLException;

    void fireEmployeeById(Integer id) throws SQLException;
}
