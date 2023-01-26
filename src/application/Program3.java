package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;

public class Program3 {
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(
                "UPDATE seller "
                + "SET BaseSalary = BaseSalary + ? "
                + "WHERE "
                + "(DepartmentId = ?)"
            );
            preparedStatement.setDouble(1, 200.0);
            preparedStatement.setInt(2, 2);
            Integer rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Done! Rows affected = " + rowsAffected);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {
            Database.closeStatement(preparedStatement);
            Database.closeConnection();
        }
    }
}
