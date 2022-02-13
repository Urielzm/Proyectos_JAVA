package mx.com.gm.peliculas.datos;
import java.util.List;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

/**
 *
 * @author uriel
 */
public interface IAccesoDatos {
    /**
     * Las interfaces solo manejan atributos constantes, por defecto son public, static y final.
     */
    
    /**
     * Cualquier metodo que creemos va a ser publico y abstracto
     * en automatico el compilador nos lo agrega.
     * @param nombreArchivo
     * @return 
     */
    boolean existe(String nombreRecurso) throws AccesoDatosEx;
    
    /**
     *
     * @param nombre
     * @return
     */
    List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx;
    
    void escribir(Pelicula pelicula, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;
    
    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;
    
    void crear(String nombreRecurso) throws AccesoDatosEx;
    
    void borrar(String nombreRecurso) throws AccesoDatosEx;
    
}
