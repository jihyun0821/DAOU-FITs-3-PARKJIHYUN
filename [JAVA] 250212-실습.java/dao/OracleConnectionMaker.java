package lecture.day7.di.booksearchmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionMaker implements ConnectionMaker {

    private static OracleConnectionMaker instance = new OracleConnectionMaker();
    private Connection connection;

    private OracleConnectionMaker() {
        System.out.println("Creating Oracle Connection");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "C##JDBC_PRACTICE";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
    }

    public static OracleConnectionMaker getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() {
        System.out.println("Getting Oracle Connection");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String username = "C##JDBC_PRACTICE";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
