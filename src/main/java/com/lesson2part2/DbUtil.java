package com.lesson2part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {


    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {

                String driver = "org.postgresql.Driver";
                Class.forName(driver);
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5433/postgres", "postgres",
                        "test");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }


    }
}
