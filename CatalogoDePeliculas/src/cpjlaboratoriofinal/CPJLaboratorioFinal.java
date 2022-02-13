/*
 * Capa de presentacion
 */
package cpjlaboratoriofinal;
import java.util.Scanner;
import mx.com.gm.peliculas.negocio.*;

/**
 * @author uriel
 */
public class CPJLaboratorioFinal {
    public static void main(String[] args) {
        
        var opcion = -1;
        var scanner = new Scanner(System.in);
        /**
         * Ya no interactuamos con la capa de datos(bajo nivel), sino con la
         * capa de negocios, ya no interactuamos a bajo nivel.
         * 
         * Estamos usando el concepto de bajo acoplamiento la menor cantidad de 
         * relaciones entre las clases.
         * 
         * También usamos el concepto de alta cuestion que indica que cada clse
         * se encarga de realizar su tarea y no más.
         * 
         */
        ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
        
        while( opcion != 0){
            System.out.println("Elige una opción: \n"
                    + "1. Iniciar catalogo de peliculas \n"
                    + "2. Agregar pelicual \n"
                    + "3. Listar peliculas \n"
                    + "4. Buscar pelicula \n"
                    + "0. Salir");
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch(opcion){
                case 1:
                    catalogo.iniciarCatalogoPeliculas();
                    break;
                case 2:
                    System.out.println("introduce el nombre de la pelicula");
                    var nombre = scanner.nextLine();
                    catalogo.agregarPelicula(nombre);
                    break;
                case 3:
                    catalogo.listaPeliculas();
                    break;
                case 4:
                    System.out.println("Introduce una pelicula a buscar");
                    var buscar = scanner.nextLine();
                    catalogo.buscarPelicula(buscar);
                    break;
                case 0:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        
    }
}
