package sky.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplicationConnection {

    private Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost:5433/skypro";
        String USERNAME = "postgres";
        String PASSWORD = "Tilitilitatata12345";
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public final PreparedStatement getStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Что-то пошло не так");
            throw new RuntimeException(e);
        }
    }
}
