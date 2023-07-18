/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Libreria.persistencia;

import Libreria.entidades.Prestamo;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Elio
 */
public class PrestamoDAO extends DAO{

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

    public List<Prestamo> listarPrestamos() {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT P FROM Prestamo P", Prestamo.class).getResultList();
        desconectar();
        if (prestamos.isEmpty()) {
            throw new NoResultException("No se encontraron libros en la base de datos.");
        }
        return prestamos;
    }
    

    public List<Prestamo> buscarPrestamos(Long dni) {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT P FROM Prestamo P JOIN P.cliente C WHERE C.dni = :dni", Prestamo.class).setParameter("dni", dni).getResultList();
        desconectar();
        if (prestamos.isEmpty()) {
            throw new NoResultException("No se encontraron prestamos en la base de datos.");
        }
        return prestamos;
    }

    public Prestamo buscarUNPrestamoPorTitulo(String titulo) {
       try{
        conectar();
        Prestamo prestamo = em.createQuery("SELECT P FROM Prestamo P JOIN P.libro L WHERE L.titulo = :titulo", Prestamo.class).setParameter("titulo", titulo).getSingleResult();
        desconectar();
            return prestamo;
        } catch (NoResultException e) {
            return null;
        }

    }
    
    
}
