/*
 * Capa de nivel de servicio o negocios
 */
package mx.com.gm.peliculas.negocio;

/**
 *
 * @author uriel
 */
public interface ICatalogoPeliculas {
    
    String NOMBRE_RECURSO = "PELICULAS.txt";
    
    void agregarPelicula(String nombrePelicula);
    
    void listaPeliculas();
    
    void buscarPelicula(String buscar);
    
    void iniciarCatalogoPeliculas();
    
}
