/*

 */
package Libreria.servicios;

import Libreria.entidades.Autor;
import Libreria.persistencia.AutorDAO;

/**
 *
 * @author Elio
 */
public class AutorServicio {
    
    private final AutorDAO DAO;

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }


    
   
    public Autor crearAutor(String nombre) {
        Autor autor = new Autor();
        if (nombre == null) {
            throw new IllegalArgumentException("Los campos deben estar completos, no pueden ser nulos.");
         }   
        try {
            if(DAO.buscarAutorPorNombre(nombre) == null){
            autor.setNombre(nombre);
            DAO.guardar(autor);
            return autor;
            }else{
                System.out.println("El Autor ya existe en la base de datos");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarAutorPorNombre(String nombre){
    
        try {
            return DAO.buscarAutorPorNombre(nombre);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            return null;
        }
    }
    public void darDeBajaoAltaAutor(String nombre){
    
        Autor autor = buscarAutorPorNombre(nombre);
        if(autor.getAlta()){
        autor.setAlta(false);
            System.out.println("El autor fue dado de baja");
        }else{
        autor.setAlta(true);
            System.out.println("El autor fue dado de alta");
        }
        DAO.editar(autor);
    
    
    }
}
