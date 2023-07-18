/*
// Esta clase hereda de la clase padre DAO y vemos que
// se especifica la generalización (<T>) como Persona
// esto permite que los métodos heredados donde se solicita
// una parámetro T, en esta clase serán de tipo Persona
 */
package Libreria.persistencia;

import Libreria.entidades.Libro;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Elio
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void eliminar(Libro objeto) {
        super.eliminar(objeto);
    }

    @Override
    public void editar(Libro objeto) {
        super.editar(objeto);
    }

    @Override
    public void guardar(Libro objeto) {
        super.guardar(objeto);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            conectar();
            Libro libro = em.createQuery("SELECT L FROM Libro L WHERE L.titulo = :titulo", Libro.class)
                    .setParameter("titulo", titulo).getSingleResult();

            desconectar();
            return libro;

        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Libro> listarLibros() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT L FROM Libro L", Libro.class).getResultList();
        desconectar();
        if (libros.isEmpty()) {
            throw new NoResultException("No se encontraron libros en la base de datos.");
        }
        return libros;
    }

    public List<Libro> buscarLibroPorAutor(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT L FROM Libro L JOIN L.autor A WHERE A.nombre = :nombre", Libro.class)
                .setParameter("nombre", nombre).getResultList();
        desconectar();

        if (libros.isEmpty()) {
            throw new NoResultException("No se encontraron libros en la base de datos para el autor especificado");
        }
        return libros;
    }
    
    public Libro buscarLibroPorIsbn(Long isbn){
       try {
            conectar();
            Libro libro = em.createQuery("SELECT L FROM Libro L WHERE L.isbn = :isbn", Libro.class)
                    .setParameter("isbn", isbn).getSingleResult();

            desconectar();
            return libro;

        } catch (NoResultException e) {
            return null;
        }
       
    }
     public Libro buscarLibroPorId(Integer id){
       try {
            conectar();
            Libro libro = em.createQuery("SELECT L FROM Libro L WHERE L.id = :id", Libro.class)
                    .setParameter("id", id).getSingleResult();

            desconectar();
            return libro;

        } catch (NoResultException e) {
            return null;
        }
       
    }
     
    public List<Libro> buscarLibroPorEditorial(String nombre){
       
        conectar();
        List<Libro> libros = em.createQuery("SELECT L FROM Libro L JOIN L.editorial E WHERE E.nombre = :nombre", Libro.class)
                .setParameter("nombre", nombre).getResultList();
        desconectar();

     // if (libros.isEmpty()) {
     //     throw new NoResultException("No se encontraron libros en la base de datos para la editorial especificada");
     // }
        return libros;
       
    
    
    } 
}
