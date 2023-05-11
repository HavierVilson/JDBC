package sky.project.dao.cityDAO;

import sky.project.models.City;

import java.sql.SQLException;

public interface CityDAO {
    City findCityById(Integer id) throws SQLException;

    void addNewCityToDataBase(String name) throws SQLException;

    void refactorCityById(Integer id, String name) throws SQLException;

    void deleteCityById(Integer id) throws SQLException;
}
