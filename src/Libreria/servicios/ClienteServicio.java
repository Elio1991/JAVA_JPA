/*
 
 */
package Libreria.servicios;

import Libreria.entidades.Cliente;
import Libreria.persistencia.ClienteDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Elio
 */
public class ClienteServicio {

    private final ClienteDAO DAO;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public ClienteServicio() {
        this.DAO = new ClienteDAO();
    }


    

    public Cliente crearCliente() {
        //Long dni, String nombre, String apellido, String telefono
        Cliente cliente = new Cliente();
        Long dni;

        try {
            System.out.println("Ingrese el DNI");
            dni = leer.nextLong();
            if (DAO.buscarClientePorDNI(dni) == null) {
                cliente.setDni(dni);
                System.out.println("Ingrese el nombre");
                cliente.setNombre(leer.next());
                System.out.println("Ingrese el apellido");
                cliente.setApellido(leer.next());
                System.out.println("Ingrese el teléfono");
                cliente.setTelefono(leer.next());
                DAO.guardar(cliente);
                System.out.println("Cliente registrado con exito");
                return cliente;
            } else {
                System.out.println("El cliente ya esta registrado en la base de datos");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Cliente buscarClientePorDNI(Long dni) {

        try {
            return DAO.buscarClientePorDNI(dni);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("no se encontro el cliente en la base de datos");
            return null;
        }

    }
    
    public List<Cliente> listarClientes(){
    
    try {
            return DAO.listarClientes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }  
    }
}
