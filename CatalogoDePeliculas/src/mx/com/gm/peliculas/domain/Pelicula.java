package mx.com.gm.peliculas.domain;

/**
 *
 * @author Uriel
 */
public class Pelicula {
    /**
     * A las clases que representan tablas de la base de datos se conocen como
     * clase de entidad.
     */

    private String nombre;

    /**
     * Constructor vacio
     */
    public Pelicula() {

    }

    /**
     * Constructor con un argumento.
     */
    public Pelicula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
