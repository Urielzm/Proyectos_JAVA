/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.com.gm.peliculas.negocio;

/**
 *
 * @author uriel
 */
public interface ICatalogoPeliculas {
    
    void agregarPelicula(String nombrePelicula, String nombreArchivo);
    
    void listaPeliculas(String nombreArchivo);
    
    void buscarPelicula(String nombreArchivo, String buscar);
    
    void iniciarArchivo(String nombreArchivo);
    
}
