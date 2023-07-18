/*

 */
package Libreria.servicios;
//import org.apache.commons.text.TextStringBuilder;
//import org.apache.commons.text.TextTable;
//import org.apache.commons.text.TextTableFactory;

import Libreria.entidades.Autor;
import Libreria.entidades.Editorial;
import Libreria.entidades.Libro;
import Libreria.persistencia.LibroDAO;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Elio
 */
public class LibroServicio {

    private final LibroDAO DAO;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public LibroServicio() {
        this.DAO = new LibroDAO();
    }

    public void crearLibroManual() {
        Random random = new Random();
        LibroServicio libroServ = new LibroServicio();
        AutorServicio autorServ = new AutorServicio();
        EditorialServicio editorialServ = new EditorialServicio();
        String nombreAutor;
        String nombreEditorial;
        Autor autor1;
        Editorial editorial1;
        Long isbn;
        // Traemos un Autor y una editorial para asignarle a un libro nuevo y comprobamos
        // si ya existe con un try agregado al metodo "crearLibro"
        System.out.println("Ingrese el nombre del autor");
        //ejemplo: String nombreAutor = "Dostoyevski";
        nombreAutor = leer.next();
        if (autorServ.buscarAutorPorNombre(nombreAutor) == null) {
            autor1 = autorServ.crearAutor(nombreAutor);
        } else {
            autor1 = autorServ.buscarAutorPorNombre(nombreAutor);
        }
        System.out.println("Ingrese el nombre de la editorial");
        // ejemplo: String nombreE = "Pengüin";
        nombreEditorial = leer.next();
        if (editorialServ.buscarEditorialPorNombre(nombreEditorial) == null) {
            editorial1 = editorialServ.crearEditorial(nombreEditorial);
        } else {
            editorial1 = editorialServ.buscarEditorialPorNombre(nombreEditorial);
        }
        System.out.println("Ingrese el nombre del libro;");
        String nombreL = leer.next();
        System.out.println("ingrese el año de publicacion");
        int anio = leer.nextInt();
        System.out.println("ingrese la cantidad de ejemplares");
        int ejemplares = leer.nextInt();
        isbn = (long) (random.nextDouble() * 9_000_000_000_000L) + 1_000_000_000_000L;

        libroServ.crearLibro(isbn, nombreL, anio, ejemplares, 0, ejemplares, autor1, editorial1);

    }

    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) {
        Libro libro = new Libro();

        if (titulo == null || anio == null || ejemplares == null || ejemplaresPrestados == null || ejemplaresRestantes == null || autor == null || editorial == null) {
            throw new IllegalArgumentException("Los campos deben estar completos, no pueden ser nulos.");
        }
        try {
            if (DAO.buscarLibroPorTitulo(titulo) == null) {
                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresPrestados(ejemplaresPrestados);
                libro.setEjemplaresRestantes(ejemplaresRestantes);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                DAO.guardar(libro);
                return libro;
            } else {
                System.out.println("El Libro ya existe");
                return null;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void DarDeAltaoBaja() {
        Integer id;
        Libro libro1;
        System.out.println("Seleccione el ID de un libro para dar de alta o baja?");
        for (Libro libro : DAO.listarLibros()) {
            System.out.println(libro);

        }
        id = leer.nextInt();
        libro1 = BuscarLibroPorId(id);

        if (libro1.isAlta()) {
            libro1.setAlta(false);
            System.out.println("El Libro fue dado de baja");
        } else {
            libro1.setAlta(true);
            System.out.println("El libro fue dado de alta");
        }
        DAO.editar(libro1);

    }

//    public void MostrarTabla(){
//     TableBuilder tableBuilder = new TableBuilder();
//        tableBuilder.addRow("Nombre", "Edad", "Correo");
//        tableBuilder.addSeparator();
//        tableBuilder.addRow("Juan", "25", "juan@example.com");
//        tableBuilder.addRow("María", "30", "maria@example.com");
//        tableBuilder.addRow("Pedro", "28", "pedro@example.com");
//
//        String table = tableBuilder.toString();
//        System.out.println(table);
//    
//    }
    public Libro BuscarLibroPorTitulo(String titulo) {
        try {
            return DAO.buscarLibroPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Libro> buscarLibroPorAutor(String nombre) {

        try {
            return DAO.buscarLibroPorAutor(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro BuscarLibroPorIsbn(Long isbn) {
        try {
            return DAO.buscarLibroPorIsbn(isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro BuscarLibroPorId(Integer id) {
        try {
            return DAO.buscarLibroPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Libro> listarLibros() {
        try {
            return DAO.listarLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Libro> buscarLibroPorEditorial(String nombre) {

        try {
            return DAO.buscarLibroPorEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
