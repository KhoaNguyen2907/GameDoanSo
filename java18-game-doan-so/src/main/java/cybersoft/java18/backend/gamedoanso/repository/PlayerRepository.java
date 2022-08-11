package cybersoft.java18.backend.gamedoanso.repository;

import cybersoft.java18.backend.gamedoanso.jdbc.MySQLConnection;
import cybersoft.java18.backend.gamedoanso.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerRepository {

    public Player findByUserName(String userName) {
        // Connect to database
        try (Connection connection = MySQLConnection.getConnection()) {
            // Create a query to find player by username
            String query = "select * from Player where username = ?";

            // Create a prepared statement to execute query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            // Return result from result set.
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return new Player(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("name"));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Error while connecting to db: %s", e.getMessage()));
        }
    }


    public void save(String userName, String password, String name) {
        // Create connection to db

        if (isValid(userName, password, name)) {
            if (!isExisted(userName)) {
                try (Connection connection = MySQLConnection.getConnection()) {
                    // Create a query
                    String query = "insert into Player(username,password,name) " +
                            "values(?,?,?)";
                    // Execute prepared statement
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, userName);
                    statement.setString(2, password);
                    statement.setString(3, name);
                    statement.executeUpdate();
                } catch (Exception e) {
                    throw new RuntimeException(
                            String.format("Error while connecting to db: %s", e.getMessage()));
                }
            }
        }
    }

    private boolean isExisted(String userName) {
        if (findByUserName(userName) != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValid(String userName, String password, String name) {
        if (userName == null || password == null || name == null
                || "".equals(userName) || "".equals(password) || "".equals(name)) {
            return false;
        }
        return true;
    }
}
