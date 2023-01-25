package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.Database;

public class Program2 {
    
    public static void main(String[] args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentID) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "Carl Purple");
            preparedStatement.setString(2, "carl@gmail.com");
            preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/1985").getTime()));
            preparedStatement.setDouble(4, 3000.0);
            preparedStatement.setInt(5, 4);
            Integer rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No changes!");
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        finally {
            Database.closeStatement(preparedStatement);
            Database.closeConnection();
        }
    }
}
