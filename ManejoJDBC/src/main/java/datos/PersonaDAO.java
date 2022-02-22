package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

/**
 *
 * @author uriel
 * 
 * Para poder realziar las operaciones sobre la base de datos se usara esta clase
 * y es conocida como: DATA ACCESS OBJECT(DAO).
 * 
 * Realiza las operaciones de INSERT, SELECT, UPDATE, DELETE, pero sobre la 
 * tabla de perosna (enitdad persona).
 * 
 */
public class PersonaDAO {
    /**
     * Las sentencias son creaadas al inicio de la clase.
     */
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, "
            + "email, telefono FROM persona";
    
    /**
     * En este caso usamos "valores en codigo duro" necesitamos pasar parametros
     * por ello usamos "?".
     */
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, "
            + "apellido, email, telefono) "
            + "VALUES(?, ?, ?, ?)";
    
    /**
     * Query para actualizar un registro.
     */
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, "
            + "apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    
    /**
     * Query para eliminar un registro.
     */
    private static final String SQL_DELETE = "DELETE FROM persona WHERE "
            + "id_persona = ?";
    
    /**
     * Metodo para listar los registros de la Base dedatos en objetos Persona
     * Regresa una lista de objetos tipo persona.
     */
    public List<Persona> seleccionar(){
        /**
         * En este caso no aplica var porque estamos inicializando 
         * la variable con null.
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        try {
            /**
             * Nos conectamos a la base de datos
             * 
             * Podemos omitir el nombre de la clase y hacer un import estatic
             * de todos los metodos de la clase que hemos utilizado.
             * "import static datos.Conexion.*;"
             * 
             */
            conn = getConnection();
            
            /**
             * Definimos la sentencia que vamos a usra con "PreparedStatement"
             * regresa un instancia de la interfaz que implemento
             * "PreparedStatement".
             */
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            /**
             * Debemos iterar e inicializar los objetos de tipo persona por cada
             * registro que tengamos en la tabla de base de datos.
             */
            while(rs.next()){
                /**
                 * Recuperamos los campos de los registros.
                 */
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                /**
                 * Convertimos informacion de la base de datos hacia objetos JAVA.
                 * Esto hace el Framework de JAVA regresa objetos de java completos.
                 */
                persona = new Persona(idPersona,nombre, apellido, email, telefono);
                
                /**
                 * Agregamos a la lista cada persona.
                 */
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        /**
         * Cerrramos los objetos en el orden inverso en que se hayan ido abierto
         * El bloque de try catch lo podemos enviar a la calse de conexión.
         */
        finally{
            try {
                /**
                 * No necesitamos el nombre de nustra clase porque ya importamos
                 * los metodos estaticos .
                 */
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        /**
         * Regresamos la lista de personas.
         */
        return personas;
    }
    
    /**
     * Metodo de insertar devuelve cuantos registros va a modificar.
     */
    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            /**
             * Podemos usar el metodo directamente porque es estatico y hemos importado
             * los metodos estaticos.
             *
             */
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            /**
             * El numero que se le pone como campos corresponde a los atributos 
             * del Query "VALUES(?, ?, ?, ?)"
             */
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            
            /**
             * Ejecutamos nuestra sentencia y nos requresa el numero de registros
             * que afecta
             * 
             * .executeUpdate() --> Ejecuta las sentencias y actualiza el estado
             * en la base de datos, puede ejecutar sentencias de tipo
             * INSERT, UPDATE, DELETE.
             */
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        /**
         * Cerramos los objetos 
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
     * Metodo de actualizar, regresa la cantidad de registros que fueron 
     * actualizados.
     */
    public int actualizar(Persona persona){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            
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
     * Metodo de eliminar, regresa la cantidad de registros que fueron 
     * eliminados.
     */
    public int eliminar(Persona persona){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1, persona.getIdPersona());
            
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
