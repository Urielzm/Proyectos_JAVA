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
     * Para las transacciones necesitamos una conexion que no se cierre hasta 
     * hacer el commit o Rollback
     */
    private Connection conexionTransaccional;
    
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
     * Constructor para que reciba conexiones
     */
    public UsuarioDAO(){
        
    }
    public UsuarioDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    /**
     * Metodo para listar usuarios.
     */
    public List<Usuario> seleccionar() throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            
            //Realizamos la conexion a la BD.
            conn = conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
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
            
        } 
        finally{
            close(rs);
            close(stmt);
            if(conexionTransaccional == null){
                close(conn);
            }
        }
        
        return usuarios;
    }
    
    /**
     * Metodo de insertar devuelve cuantos registros va a modificar.
     */
    public int insertar(Usuario usuario) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            
            /**
             * Devuelve el numero de registros.
             */
            registros = stmt.executeUpdate();
            
        } 
        /**
         * Cerramos los objetos. 
         */
        finally{
            close(stmt);
            if(conexionTransaccional == null){
                close(conn);
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
    public int actualizar(Usuario usuario) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            
        } 
        finally{
            close(stmt);
            if(conexionTransaccional == null){
               close(conn);
            }
        }
        return registros;
    }
    
    /**
     * Metodo para eliminar.
     * @param usuario
     * @return 
     */
    public int eliminar(Usuario usuario) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            registros = stmt.executeUpdate();
            
        } 
        finally{
            close(stmt);
            if(conexionTransaccional == null){
               close(conn);
            }
        }
        return registros;
    }
    
}
