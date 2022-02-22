package test;

import datos.Conexion;
import datos.UsuarioDAO;
import domain.Usuario;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author uriel
 */
public class TestManejoUsuario {

    public static void main(String[] args) {
        
        /**
         * Para poder usar la variable dentro del bloque try y catch (poder hacer
         * ROllBack) debemos declararla fuera del bloque aunque se instancie dentro.
         */
        Connection conexion = null;
        
        try {
            /**
             * Obtenemos la conexion fuera de la calse UsuarioDAO
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
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            
            /**
             * Vamos a hacer un UPDATE y un ISERT dentro de la misma transaccion.
             */
            Usuario usuarioCambio = new Usuario(2, "Jayline.Ojeda", "54321");
            usuarioDAO.actualizar(usuarioCambio);
            
            Usuario nuevaUsuario = new Usuario();
            nuevaUsuario.setUsuario("Carlos.Fulano");
            
            /**
             * Para provocar el fallo y que observemos el RollBack podemos
             * ingresamos un apellido de mas de 45 caracteres.
             * nuevaUsuario.setPassword("Ramirez1111111111111111111111111111111111111111111111111");
             */
            nuevaUsuario.setPassword("1111");
            
            usuarioDAO.insertar(nuevaUsuario);
            
            /**
             * Hacemos el commit de la transaccion.
             */
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
                    
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
