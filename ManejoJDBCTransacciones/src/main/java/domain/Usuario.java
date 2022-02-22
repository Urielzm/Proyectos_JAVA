package domain;

/**
 *
 * @author Uriel
 */
public class Usuario {
    
    private int idUsuario ;
    private String usuario;
    private String password;
    
    /**
     * Contructor para listar los usuarios.
     */
    public Usuario(){
        
    }
    
    /**
     * Constructor para eliminar un usuario.
     * @param idUsuario 
     */
    public Usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    /**
     * Constructor para insertar un usuario.
     * 
     * @param usuario
     * @param password 
     */
    public Usuario(String usuario, String password){
        this.usuario = usuario;
        this.password = password;
    }
    
    /**
     * Contructor para actualizar un registro.
     */
    public Usuario(int idUsuario, String usuario, String password){
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
    }

    /**
     * Setters and Getters.
     * @return 
     */
    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", usuario=" + usuario + ", password=" + password + '}';
    }

}
