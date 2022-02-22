package datos;

/**
 * Tambien podemos solo colocar import java.sql.*
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Uriel
 * 
 * Esta clase solo se encarga de crear la conexion y de cerrar los objetos.
 * 
 */
public class Conexion {
    /**
     * Creamos nuestras constante para nuestra cadena de conexion.
     */
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "mysql";
    
    /**
     * Vamos a definir un metodo que nos devuelve la conexion a la base de datos
     * y reportamos la excepcion agregandola a la firma y menajarla en donde
     * se hizo la llamada a este metodo.
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    /**
     * Cerramos los objetos.
     * 
     * @param rs
     * @throws SQLException 
     */
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement smtm) throws SQLException{
        smtm.close();
    }
    
    /**
     * Prepare Statement tiene un mejor perfomance que Statement.
     */
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
