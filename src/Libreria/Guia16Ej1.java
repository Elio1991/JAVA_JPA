/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Libreria;


import Libreria.entidades.Cliente;
import Libreria.entidades.Libro;
import Libreria.entidades.Prestamo;
import Libreria.servicios.AutorServicio;
import Libreria.servicios.ClienteServicio;
import Libreria.servicios.EditorialServicio;
import Libreria.servicios.LibroServicio;
import Libreria.servicios.PrestamoServicio;
import java.util.Scanner;


/**
 *
 * @author Elio
 */
public class Guia16Ej1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LibroServicio libroServ = new LibroServicio();
        AutorServicio autorServ = new AutorServicio();
        EditorialServicio editorialServ = new EditorialServicio();
        ClienteServicio clienteServ = new ClienteServicio();
        PrestamoServicio prestamoServ = new PrestamoServicio();
        
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        String nombreAutor;
        String nombreEditorial;

        Long isbn;
        int resp;
        // Instancia el menu de ejecucion.
         MenuEjecucion menuEjecutable = new MenuEjecucion();

        // Llamo al metodo que crea autores, editoriales y libros en mi clase "MenuEjecucion"
         int id = 1;
         if(libroServ.BuscarLibroPorId(id) == null){
          menuEjecutable.CrearEntidades();
          System.out.println("Entidades Creadas con exito");
         }   
    
        do {

            System.out.println("--------------------MENU---------------------");
            System.out.println("#############################################");
            System.out.println("1. Crear Libro");
            System.out.println("2. Crear Cliente");
            System.out.println("3. Listar Libros, listar clientes, Listar Prestamos.");
            System.out.println("4. Dar de alta o baja un Autor, una Editorial o un libro.");
            System.out.println("5. Buscar un Autor por Nombre.");
            System.out.println("6. Búscar un libro por ISBN.");
            System.out.println("7. Buscar un libro por título.");
            System.out.println("8. Buscar un libro/s por nombre de Autor.");
            System.out.println("9. Buscar un libro/s por nombre de Editorial.");
            System.out.println("10. Buscar Cliente por DNI");
            System.out.println("11. Solicitar prestamo de un libro.");
            System.out.println("12. Devolucion de libro.");
            System.out.println("13. Salir");

            resp = leer.nextInt();
            while ( resp < 1 && resp > 13) {
                System.out.println("ingrese una opcion valida");
                resp = leer.nextInt();
            }
            

            switch (resp) {
                case 1:
                    libroServ.crearLibroManual();
                    break;
            
                case 2:
                    clienteServ.crearCliente();
                    break;
                case 3:
                    String ops;
                    System.out.println("A. Listar libros");
                    System.out.println("B. Listar Clientes");
                    System.out.println("C. Listar Prestamos");
                    ops = leer.next();
                    if(ops.equalsIgnoreCase("A")){
                    // Listar todos los libros
                    for (Libro libro : libroServ.listarLibros()) {
                        System.out.println(libro);
                    }
                    }else if (ops.equalsIgnoreCase("B")){
                        for (Cliente clientes : clienteServ.listarClientes()) {
                            System.out.println(clientes);
                        }
                    
                    }else if(ops.equalsIgnoreCase("C")){
                        for (Prestamo prestamos : prestamoServ.listarPrestamos()) {
                            System.out.println(prestamos);
                        }
                    
                    }
                    break;
                case 4:
                    String resp1;
                    System.out.println("A. Dar De Baja/Alta un Autor");
                    System.out.println("B. Dar de Baja/Alta una Editorial");
                    System.out.println("C. Dar de Baja/Alta un Libro");
                    resp1 = leer.next();
                    if (resp1.equalsIgnoreCase("A")) {
                        System.out.println("Ingrese el nombre del autor");
                        nombreAutor = leer.next();
                        // ejemplo: 
                        // String nombreAutor = "Dostoyevski";
                        System.out.println(autorServ.buscarAutorPorNombre(nombreAutor));
                    } else if (resp1.equalsIgnoreCase("B")) {
                        System.out.println("Ingrese el nombre de la editorial");
                        nombreEditorial = leer.next();
                        // ejemplo: 
                        // String nombreE = "Destino";
                        editorialServ.darDeAltaoBajaEditorial(nombreEditorial);

                    } else if (resp1.equalsIgnoreCase("C")) {

                        libroServ.DarDeAltaoBaja();

                    }
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del Autor");
                    nombreAutor = leer.next();
                    // ejemplo:
                    // String nombreAutor = "Dostoyevski";
                    if (autorServ.buscarAutorPorNombre(nombreAutor) == null) {
                        System.out.println("No se encontro al Autor solicitado");
                    } else {
                        System.out.println(autorServ.buscarAutorPorNombre(nombreAutor));
                    }
                    break;
                case 6:
                    System.out.println("ingrese el isbn del libro");
                    isbn = leer.nextLong();
                    // ejemplo: 
                    // Long isbn = 9788478884957;
                    if (libroServ.BuscarLibroPorIsbn(isbn) == null) {
                        System.out.println("No se encontro el libro por el isbn solicitado");
                    } else {
                        System.out.println(libroServ.BuscarLibroPorIsbn(isbn));
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el titulo del libro");
                    String tituloL = leer.next();
                    // ejemplo;
                    // String titulo = "Canción de Hielo y Fuego";
                    if (libroServ.BuscarLibroPorTitulo(tituloL) == null) {
                        System.out.println("No se encontro el libro por el titulo solicitado");
                    } else {
                        System.out.println(libroServ.BuscarLibroPorTitulo(tituloL));
                    }
                    break;
                case 8:
                    System.out.println("Ingrese el nombre del Autor");
                    nombreAutor = leer.next();
                    // String nombre = "Neil Gaiman";
                    if (autorServ.buscarAutorPorNombre(nombreAutor) == null) {
                        System.out.println("No se encontraron libros para el autor solicitado");
                    } else {
                        System.out.println(libroServ.buscarLibroPorAutor(nombreAutor));
                    }
                    break;
                case 9:
                    System.out.println("Ingrese el nombre de la editorial");
                    nombreEditorial = leer.next();
                    // ejemplo:
                    // String nombre = "Destino";
                    System.out.println(libroServ.buscarLibroPorEditorial(nombreEditorial));
                    break;
                case 10:
                    System.out.println("ingrese el dni:");
                    Long dni = leer.nextLong();
                    System.out.println(clienteServ.buscarClientePorDNI(dni));
                    break;
                case 11:
                   
                    // No toma el dni del cliente.
                    prestamoServ.solicitarPrestamo();
                    break;
                case 12:
                    prestamoServ.devolucionLibro();
                    break;
            }

        } while (resp != 13);
    }

}
