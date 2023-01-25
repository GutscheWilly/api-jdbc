package application;

import java.sql.Connection;

import database.Database;

public class Program {
    
    public static void main(String[] args) throws Exception {
        Connection connection = Database.getConnection();
        connection.close();
    }
}
