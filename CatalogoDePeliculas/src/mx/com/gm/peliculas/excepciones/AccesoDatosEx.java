package mx.com.gm.peliculas.excepciones;

/**
 *
 * @author Uriel
 */
public class AccesoDatosEx extends Exception{
    /**
     * Para que sea una clase de tipo excepción, debe extender de exception
     * también puede heradar de RuntimeException, pero ya no nos va a oblgar a
     * procesar la excepcion ya sea con un try catch o arrojandola en la 
     * firma del metodo.
     * 
     * Lo recomendable es usar Exception al inicio.
     */
    
    public AccesoDatosEx(String mensaje){
        super(mensaje);
    }
}
