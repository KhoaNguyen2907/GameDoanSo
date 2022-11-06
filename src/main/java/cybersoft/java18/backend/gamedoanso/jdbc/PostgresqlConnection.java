package cybersoft.java18.backend.gamedoanso.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
    private static final String URL = "jdbc:postgresql://ec2-52-207-15-147.compute-1.amazonaws.com:5432/da22k6q682g4o1";
    private static final String USERNAME = "cgicahcxfhepkp";
    private static final String PASSWORD = "17b203a95d39f3e0997adc0ad2ae576715c5faab9d4b8d40255ee360cc3861ae";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
        System.out.println("Kết nối thành công");

    }
}
