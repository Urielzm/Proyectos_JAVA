package datos;

import static datos.Conexion.*;
import domain.Usuario;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Uriel
 */
public class UsuarioDAO {
    /**
     * Querys.
     */
    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password "
            + " FROM usuario";
    
    private static final String SQL_INSERT = "INSERT INTO usuario (usuario, "
            + " password) VALUES(?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE usuario SET usuario = ?, "
            + " password = ? WHERE id_usuario = ?";
    
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE "
            + "id_usuario = ?";
    
    /**
     * Metodo para listar usuarios.
     */
    public List<Usuario> seleccionar(){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            
            //Realizamos la conexion a la BD.
            conn = getConnection();
            //Indicamos la sentencia a ejecutar.
            stmt = conn.prepareStatement(SQL_SELECT);
            //Devuelve el resultado del Query.
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                /**
                 * Recuperamos los campos de los registros.
                 */
                int idUsuario = rs.getInt("id_usuario");
                String usuarioNombre = rs.getString("usuario");
                String password = rs.getString("password");
                
                /**
                 * Convertimos informacion de la base de datos hacia objetos JAVA.
                 */
                usuario = new Usuario(idUsuario, usuarioNombre, password );
                
                /**
                 * Agregamos a la lista cada usuario.
                 */
                usuarios.add(usuario);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                /**
                 * Cerramos los objetos.
                 */
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return usuarios;
    }
    
    /**
     * Metodo de insertar devuelve cuantos registros va a modificar.
     */
    public int insertar(Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            
            /**
             * Devuelve el numero de registros.
             */
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        /**
         * Cerramos los objetos. 
         */
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        /**
         * Regresamos el numero de registros afectados.
         */
        return registros;
    }
    
    /**
     * Metodo de actualizar.
     */
    public int actualizar(Usuario usuario){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    /**
     * Metodo para eliminar.
     * @param usuario
     * @return 
     */
    public int eliminar(Usuario usuario){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
}
