package domain;

/**
 *
 * @author Uriel
 * 
 * Esta clase tiene los atributos de la tabla Persona de Base de datos
 * se le conoce como de dominio o entidad.
 * 
 */
public class Persona {
    private int idPersona ;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    /**
     * Metodo para listar personas.
     */
    public Persona() {
    }

    /**
     * Para eliminar un registro basta con el Id
     * @param idPersona 
     */
    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Para hacer el Insert de un registro no necesitamso el ID
     * 
     * @param nombre
     * @param apellido
     * @param email
     * @param telefono 
     */
    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    /**
     * Para modificar un registro vale la pena tener todos los atributos.
     */    
    public Persona(int idPersona, String nombre, String apellido, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
}
