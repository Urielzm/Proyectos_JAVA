package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.*;

/**
 *
 * @author uriel
 */
public class testManejoPersonas {

    public static void main(String[] args) {
        /**
         * Con la clase de PersonaDAO vamos a interactuar con nuestra PersonaDAO
         * es una implementación directa, pero sería mejor usar una interfaz,
         * para que sea independiente de la implementacioin.
         */
        PersonaDAO personaDAO = new PersonaDAO();

        /**
         * Insertamos una persona, tenemos diferentes constructores, pero en
         * este caso nos conviene el constructor que no necesita id_persona ya
         * que es autoincremento en l BD.
         */
//        Persona personaNueva = new Persona("Carlos", "Esparza", "cesparza@gmail.com", "556677882233");
//        personaDAO.insertar(personaNueva);
        
        /**
         * Modificamos un registro
         */
//        Persona personaModificar = new Persona(4, "Juan Carlos", "Esparza", "jcesparza@mail.com", "556677882233");
//        personaDAO.actualizar(personaModificar);
        
        Persona personaEliminar = new Persona(4);
        personaDAO.eliminar(personaEliminar);
        
        /**
         * Regresamos una lista de personas y la recorremos con una funcion
         * lamda.
         */
        List<Persona> personas = personaDAO.seleccionar();

//        for(Persona persona: personas){
//            System.out.println("persona = " + persona);
//        }
        personas.forEach(persona -> {
            System.out.println("persona = " + persona);
        });

    }
}
