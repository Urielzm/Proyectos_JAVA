package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

/**
 *
 * Para que la transaccion maneje rollback o commit se tiene que hacer en 
 * la misma conexion
 * 
 * Por ello esta clase no debe procesar la excepcion directamente, solo la 
 * propaga a la que se encarga de procesarla.
 * 
 * @author uriel
 */
public class PersonaDAO {
    
    /**
     * Necesitamso una conexión que no se cierre.
     */
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, "
            + "email, telefono FROM persona";
    
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, "
            + "apellido, email, telefono) "
            + "VALUES(?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, "
            + "apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    
    private static final String SQL_DELETE = "DELETE FROM persona WHERE "
            + "id_persona = ?";
    
    public PersonaDAO(){
        
    }
    
    /**
     * Constructor para recibir la conexion transaccional y esta no se va
     * a cerrar.
     * @param conexionTransaccional 
     */
    public PersonaDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    public List<Persona> seleccionar() throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        try {
            /**
             * Si HAY conexion transaccional ?
             *      USALA
             * SINO
             *      CREA UN CONEXION.
             */
            conn = conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new Persona(idPersona,nombre, apellido, email, telefono);
                personas.add(persona);
            }
        }
        finally{
            close(rs);
            close(stmt);
                
            /**
            * Si la conexion NO ES transaccional 
            *      CIERRALA.
            */
            if(conexionTransaccional == null){
                close(conn);
            }
        }
        return personas;
    }
    
    public int insertar(Persona persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            
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
    
    public int actualizar(Persona persona) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            
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
    
    public int eliminar(Persona persona) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
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
