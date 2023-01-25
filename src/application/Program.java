package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Program {
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from department");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + " - " + resultSet.getString("Name"));
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(statement);
            Database.closeConnection();
        }
    }
}
