/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria.servicios;

import Libreria.entidades.Cliente;
import Libreria.entidades.Libro;
import Libreria.entidades.Prestamo;
import Libreria.persistencia.LibroDAO;
import Libreria.persistencia.PrestamoDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Elio
 */
public class PrestamoServicio {
    
    private ClienteServicio clienteServicio;
    private LibroServicio libroServicio;
    
    private final PrestamoDAO DAOP;
    private final LibroDAO DAOL;
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public PrestamoServicio() {
        this.DAOL = new LibroDAO();
        this.DAOP = new PrestamoDAO();
        this.clienteServicio = new ClienteServicio();
        this.libroServicio = new LibroServicio();
    }

    
    
    public Prestamo solicitarPrestamo() {
        Date fechaPrestamo = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPrestamo);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date fechaDevolucion = calendar.getTime();
        Long dni;
        String titulo;
        Prestamo prestamo = new Prestamo();
        Cliente cliente;
        Libro libro = new Libro();
        
        
        try{
          System.out.println("Ingrese el DNI del cliente");
          dni = leer.nextLong();
        // Comprobamos si el cliente esta registrado en la base de datos.
        if (clienteServicio.buscarClientePorDNI(dni) == null) {
            System.out.println("No se encontro un cliente asociado en la base de datos");
            // Llamamos al metodo para registrar un cliente nuevo
           System.out.println("Registrar cliente");
            clienteServicio.crearCliente();
            // Si esta asociado ↓    
         } 
            cliente = clienteServicio.buscarClientePorDNI(dni);
            System.out.println("Ingrese el titulo del libro que desea solicitar");
            titulo = leer.next();
            // Comprobamos que el libro que buscamos por el titulo, este en la base de datos.
            if (libroServicio.BuscarLibroPorTitulo(titulo) == null) {
                System.out.println("No se encontro el libro por el titulo solicitado");
                // Si esta en la base de datos ↓    
            } else {
                libro = libroServicio.BuscarLibroPorTitulo(titulo);
                // Comprobamos que haya ejemplares disponibles
                if (libro.getEjemplaresRestantes() > 0) {
                    System.out.println("Hay ejemplares disponibles");
                    System.out.println("asignando libro..");
                    libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                } else {
                    System.out.println("No se encontraron ejemplares para realizar el prestamo");
                }
            }
            // Guardo los cambios en la cantidad de ejemplares de libros
            DAOL.editar(libro);
            // Seteo y guardo el nuevo prestamo realizado
            prestamo.setLibro(libro);
            //prestamo.setLibro(new LibroServicio().BuscarLibroPorTitulo(titulo));
            prestamo.setAlta(true);
            prestamo.setCliente(cliente);
            System.out.println("Tiene 7 dias para la devolucion del libro");
            System.out.println("Fecha actual: " + fechaPrestamo);
            System.out.println("Fecha de devolución: " + fechaDevolucion);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            DAOP.guardar(prestamo);
            return prestamo;
      
        }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("alfhaldkf");
                return null;
            }
      
    }
    public List<Prestamo> listarPrestamos(){
    try {
            return DAOP.listarPrestamos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }  
    
    }
    
    public List<Prestamo> buscarPrestamoPorDni(){
     Long dni;
        try {
         System.out.println("Ingrese el DNI");
         dni = leer.nextLong();
            return DAOP.buscarPrestamos(dni);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }  

    }
    
    public Prestamo buscarUNPrestamoPorTitulo(String titulo){

        try {
        
            return DAOP.buscarUNPrestamoPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }  
    
    
    
    }
    
    public void devolucionLibro(){
    Long dni;
    String titulo;
    Prestamo prestamo;
    try {
         System.out.println("Ingrese el DNI");
         dni = leer.nextLong();
    
            if(DAOP.buscarPrestamos(dni) != null){
                for (Prestamo aux : DAOP.buscarPrestamos(dni)) {
                    System.out.println(aux.getLibro().getTitulo());
  
                }
                  System.out.println("Ingrese el título para la devolución");
                  titulo = leer.next();
                  prestamo = DAOP.buscarUNPrestamoPorTitulo(titulo);
                  prestamo.setAlta(false);
                  prestamo.getLibro().setEjemplaresPrestados(prestamo.getLibro().getEjemplaresPrestados()-1);
                  prestamo.getLibro().setEjemplaresRestantes(prestamo.getLibro().getEjemplaresRestantes()+1);
                  DAOP.editar(prestamo);
                  System.out.println("Titulo Devuelto Correctamente");
 
            }else{
                System.out.println("Gracias!");
            }
      
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    }
 
    
}
