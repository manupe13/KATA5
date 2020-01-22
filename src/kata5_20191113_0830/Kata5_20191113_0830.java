package kata5_20191113_0830;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5_20191113_0830 {
    
    public static void main(String[] args) {
        String URL_BD_SQLite = new String("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\DB_SQLite\\miErcoles.db");
        Connection connection = connect(URL_BD_SQLite);
        
    }
    

    private static Connection connect(String URL_BD_SQLite) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de Datos conectada...");
            selectData_PEOPLE(connection); 
            insertData_PEOPLE(connection);
            System.out.println("* * * * *");
            selectData_PEOPLE(connection); 
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5_20191113_0830::connect (SQLException) " + exception.getMessage());
        }
        finally{
            try{
                if (connection != null)
                    connection.close();
            }
            catch (SQLException exception) {
                System.out.println("ERROR Kata5_20191113_0830::connect-finally (SQLException) " + exception.getMessage());
            }
        }
        return connection;
    }

    private static void selectData_PEOPLE(Connection connection) {
        String SQL = "SELECT * FROM PEOPLE";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(SQL);
            System.out.println("ID \t NAME \t APELIDOS \t DEPARTAMENTO");
            while (resultset.next()) {
                System.out.println(resultset.getInt("ID") + " \t " + 
                                   resultset.getString("NAME") + " \t " + 
                                   resultset.getString("APELLIDOS") + " \t " +
                                   resultset.getString("DEPARTAMENTO"));
            }
        
        }
        catch (SQLException exception) {
                System.out.println("ERROR Kata5_20191113_0830::selectData_PEOPLE (SQLException) " + exception.getMessage());
        }
         
    }
    private static void insertData_PEOPLE(Connection connection) {
        String SQL = "INSERT INTO PEOPLE(ID, NAME, APELLIDOS, DEPARTAMENTO) VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement preparedstatement = connection.prepareStatement(SQL);
            preparedstatement.setInt(1, 23);
            preparedstatement.setString(2, "Juan");
            preparedstatement.setString(3, "Quesada");
            preparedstatement.setString(4, "Compras");
            preparedstatement.executeUpdate();
        }
        catch (SQLException exception) {
                System.out.println("ERROR Kata5_20191113_0830::selectData_PEOPLE (SQLException) " + exception.getMessage());
        }
        
        
        
    }
    
}
