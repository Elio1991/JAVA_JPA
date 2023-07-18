/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria.persistencia;

import Libreria.entidades.Cliente;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Elio
 */
public class ClienteDAO extends DAO{

    @Override
    public void eliminar(Object objeto) {
        super.eliminar(objeto); 
    }

    @Override
    public void editar(Object objeto) {
        super.editar(objeto); 
    }

    @Override
    public void guardar(Object objeto) {
        super.guardar(objeto); 
    }

    public Cliente buscarClientePorDNI(Long dni) {
  
         try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery(" SELECT C FROM Cliente C WHERE C.dni = :dni")
                    .setParameter("dni", dni).getSingleResult();
            desconectar();
            return cliente;
        } catch (NoResultException e) {
            return null;
        }
        
        
    }

    public List<Cliente> listarClientes() {
         conectar();
        List<Cliente> clientes = em.createQuery("SELECT C FROM Cliente C", Cliente.class).getResultList();
        desconectar();
        if (clientes.isEmpty()) {
            throw new NoResultException("No se encontraron clientes en la base de datos.");
        }
        return clientes;
    }

   
    
}
