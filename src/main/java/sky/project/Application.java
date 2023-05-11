package sky.project;


import sky.project.dao.employeeDAO.EmployeeDAO;
import sky.project.dao.employeeDAO.EmployeeDAOImpl;
import sky.project.models.Employee;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        employeeDAO.hireNewEmployee("Andrey", "Petrov", 27, 1);

        employeeDAO.refactorEmployeeById(27, "Andrey", "Vasiliev", 30, 1);

        System.out.println(employeeDAO.findEmployeeById(27));

        employeeDAO.fireEmployeeById(5);

        for (Employee employee : employeeDAO.getEmployees()) {
            System.out.println(employee);
        }


    }
}
