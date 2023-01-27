package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;
import database.DatabaseException;

public class Program5 {
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = Database.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            Integer firstUpdate = statement.executeUpdate("UPDATE seller SET BaseSalary = 999.0 WHERE DepartmentId = 1");
            Integer secondUpdate = statement.executeUpdate("UPDATE seller SET BaseSalary = 4999.0 WHERE DepartmentId = 4");
            connection.commit();
            System.out.println("First update: " + firstUpdate);
            System.out.println("Second update: " + secondUpdate);
        }
        catch (SQLException sqlException1) {
            try {
                connection.rollback();
                throw new DatabaseException("Transaction rolled back! Erro: " + sqlException1.getMessage());
            }
            catch (SQLException sqlException2) {
                throw new DatabaseException("Erro trying rollback! " + sqlException2.getMessage());
            }
        }
        finally {
            Database.closeStatement(statement);
            Database.closeConnection();
        }
    }
}
