package part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static void main(String args[]){

        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";
        String user = "root";
        String password = "password";

        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = sb.toString();

        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("sucesso!");
        } catch (SQLException e){
            System.out.println("falha");
            e.printStackTrace();
        }

    }

}
