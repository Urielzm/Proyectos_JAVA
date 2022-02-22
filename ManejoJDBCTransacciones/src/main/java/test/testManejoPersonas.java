package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author uriel
 */
public class testManejoPersonas {

    public static void main(String[] args) {
        
        /**
         * Para poder usar la variable dentro del bloque try y catch (poder hacer
         * ROllBack) debemos declararla fuera del bloque aunque se instancie dentro.
         */
        Connection conexion = null;
        
        try {
            /**
             * Obtenemos la conexion fuera de la calse PersonaDAO
             */
            conexion = Conexion.getConnection();
            
            /**
             * Revisamos si la conexion esta en AUTOCOMIT por default es true
             */
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            /**
             * Si no se le pasa la conexion se realizarian las operaciones SQL
             * sin la oportunidad de hacer RollBack o Commit.
             */
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            
            /**
             * Vamos a hacer un UPDATE y un ISERT dentro de la misma transaccion.
             */
            Persona personaCambio = new Persona(2, "Karla Ivone", "Gomez", 
                    "kGomnez@mail.com", "5566881122");
            personaDAO.actualizar(personaCambio);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Carlos");
            
            /**
             * Para provocar el fallo y que observemos el RollBack podemos
             * ingresamos un apellido de mas de 45 caracteres.
             * nuevaPersona.setApellido("Ramirez1111111111111111111111111111111111111111111111111");
             */
            nuevaPersona.setApellido("Ramirez");
            
            personaDAO.insertar(nuevaPersona);
            
            /**
             * Hacemos el commit de la transaccion.
             */
            conexion.commit();
            System.out.println("Se ha hacho cimmit de la transaccion");
                    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al RollBack");
            
            try {
                /**
                 * La variable conexion debe estar fuera de este bloque o no se podra
                 * realizar la conexion.
                 */
                conexion.rollback();
                
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
    }
}
