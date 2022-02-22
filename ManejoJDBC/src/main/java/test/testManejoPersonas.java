package test;

import datos.PersonaDAO;
import datos.UsuarioDAO;
import domain.Persona;
import domain.Usuario;
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
        
//        Persona personaEliminar = new Persona(4);
//        personaDAO.eliminar(personaEliminar);
        
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

        /**
         * USARIO
         */
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
//        Usuario usuarioInsertar = new Usuario("Jayline2", "4321");
//        usuarioDAO.insertar(usuarioInsertar);

//        Usuario usuarioActualizar = new Usuario(2,"Jayline.Ojeda", "43215");
//        usuarioDAO.actualizar(usuarioActualizar);
        
//        Usuario usuarioActualizar = new Usuario(1,"Uriel.Zagoya", "43215");
//        usuarioDAO.actualizar(usuarioActualizar);
        
//        Usuario usuarioEliminar = new Usuario(3);
//        usuarioDAO.eliminar(usuarioEliminar);
        
        List<Usuario> usuarios = usuarioDAO.seleccionar();
        usuarios.forEach(usuario -> {
            System.out.println("usuario = " + usuario);
        });
        
    }
}
