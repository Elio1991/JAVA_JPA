/*

 */
package Libreria.servicios;

import Libreria.entidades.Editorial;
import Libreria.persistencia.EditorialDAO;

/**
 *
 * @author Elio
 */
public class EditorialServicio {

    private final EditorialDAO DAO;
  
    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }
    

    public Editorial crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
         if (nombre == null) {
            throw new IllegalArgumentException("Los campos deben estar completos, no pueden ser nulos.");
         }   
        
        try {
            if(DAO.BuscarEditorialPorNombre(nombre) == null){
            editorial.setNombre(nombre);
            DAO.guardar(editorial);
            return editorial;
            }else{
                System.out.println("La Editorial ya existe");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    
    public Editorial buscarEditorialPorNombre(String nombre){
        try {
            
         return DAO.BuscarEditorialPorNombre(nombre);
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    
    }
    
    public void darDeAltaoBajaEditorial(String nombre){
        
        Editorial editorial = buscarEditorialPorNombre(nombre);
        if(editorial.getAlta()){
        editorial.setAlta(false);
            System.out.println("La editorial fue dada de baja");
        }else{
            System.out.println("La editorial fue dada de alta");    
            }
        DAO.editar(editorial);
        
    }
}
