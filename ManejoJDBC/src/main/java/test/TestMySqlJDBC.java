package test;

import java.sql.*;

/**
 *
 * @author Uriel
 */
public class TestMySqlJDBC {
    public static void main(String[] args) {
        //var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&severTimezone=UTC&allowPublicKeyRetrieval=true";
        var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            /**
             * Esta linea es requerida para paginas web
             * Class.forName("com.mysql.cj.jdbc.Driver");
             * 
             * } catch (ClassNotFoundException ex) {
             *      Logger.getLogger(TestMySqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
             * 
             * 
             * Requerimos un tipo conexion
             */
            Connection conexion = DriverManager.getConnection(url, "root", "mysql");
            /**
             * Un objeto tipo statement que nos permite ejecutar sentencias de bases de datos.
             * instruccion --> Es un tipo interface que nos regresa la implementacion
             * dependiendo del conector en este caso MySQL
             * conexion.createStatement() --> Una clase completa
             */
            Statement instruccion = conexion.createStatement();
            
            /**
             * Podemos crear una sentencia
             */
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            /**
             * Ejecutamos la instruccion
             */
            ResultSet resultado = instruccion.executeQuery(sql);
            /**
             * Como puede devolver más de un resultado interactuamos con un While
             * resultado.next() --> Itera sobre los registros y se coloca sobre el
             * correspondiente
             */
            while(resultado.next()){
                System.out.println("Id persona:" +resultado.getInt("id_persona"));
                System.out.print(" Nombre: " + resultado.getString("nombre"));
                System.out.print(" Apellido: " + resultado.getString("apellido"));
                System.out.print(" Email: " + resultado.getString("email"));
                System.out.print(" Telefono: " + resultado.getString("telefono"));
                System.out.println("");
            }
            /**
             * Necesitamos cerrar cada uno de los objetos que abrimos
             */
            resultado.close();
            instruccion.close();
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
}
