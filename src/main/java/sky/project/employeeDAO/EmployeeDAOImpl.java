package sky.project.dao.employeeDAO;

import sky.project.connection.ApplicationConnection;
import sky.project.dao.cityDAO.CityDAO;
import sky.project.dao.cityDAO.CityDAOImpl;
import sky.project.models.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    CityDAO cityDAO = new CityDAOImpl();

    ApplicationConnection applicationConnection = new ApplicationConnection();


    @Override
    public Employee findEmployeeById(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("select * from employee where id=(?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return new Employee(resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    cityDAO.findCityById(resultSet.getInt("city_id")));
        }
    }

    @Override
    public void hireNewEmployee(String firstName, String lastName,
                                Integer age, Integer city_id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("insert into employee(first_name, last_name, age, city_id) values (?,?,?,?)")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, city_id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("select * from employee")) {
            List<Employee> employees = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        cityDAO.findCityById(resultSet.getInt("city_id"))));
            }
            return employees;
        }
    }

    @Override
    public void refactorEmployeeById(Integer id, String firstName, String lastName,
                                     Integer age, Integer city_id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("update employee set first_name = ?, last_name = ?, age = ?, city_id =? where id =(?)")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, city_id);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void fireEmployeeById(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("delete from employee where id=(?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }

    }

}
