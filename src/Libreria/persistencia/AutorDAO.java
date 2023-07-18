/*
// Esta clase hereda de la clase padre DAO y vemos que
// se especifica la generalización (<T>) como Persona
// esto permite que los métodos heredados donde se solicita
// una parámetro T, en esta clase serán de tipo Persona
 */
package Libreria.persistencia;

import Libreria.entidades.Autor;
import javax.persistence.NoResultException;

/**
 *
 * @author Elio
 */
public class AutorDAO extends DAO<Autor> {

    @Override
    public void eliminar(Autor objeto) {
        super.eliminar(objeto);
    }

    @Override
    public void editar(Autor objeto) {
        super.editar(objeto);
    }

    @Override
    public void guardar(Autor objeto) {
        super.guardar(objeto);
    }

    public Autor buscarAutorPorNombre(String nombre) {
        try {
            conectar();
            Autor autor = (Autor) em.createQuery(" SELECT A FROM Autor A WHERE A.nombre = :nombre and A.alta = 1")
                    .setParameter("nombre", nombre).getSingleResult();
            desconectar();
            return autor;
        } catch (NoResultException e) {
            return null;
        }

    }
}
