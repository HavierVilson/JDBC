package sky.project.dao.cityDAO;

import sky.project.connection.ApplicationConnection;
import sky.project.models.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAOImpl implements CityDAO {

    private final ApplicationConnection applicationConnection = new ApplicationConnection();

    @Override
    public City findCityById(Integer id) throws SQLException {

        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("select * from city where id = (?)")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return new City(resultSet.getString("name"));

        }
    }

    @Override
    public void addNewCityToDataBase(String name) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("insert into city (name) values (?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void refactorCityById(Integer id, String name) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("update city set name=? where id=(?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteCityById(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement =
                     applicationConnection.getStatement
                             ("delete from city where id =(?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
