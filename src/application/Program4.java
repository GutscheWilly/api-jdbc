package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Database;
import database.DatabaseIntegrityException;

public class Program4 {
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(
                "DELETE FROM department "
                + "WHERE "
                + "Id = ?"
            );
            preparedStatement.setInt(1, 2);
            Integer rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
        }
        catch (SQLException sqlException) {
            throw new DatabaseIntegrityException(sqlException.getMessage());
        }
        finally {
            Database.closeStatement(preparedStatement);
            Database.closeConnection();
        } 
    }
}
