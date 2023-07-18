/*/
 // Esta clase hereda de la clase padre DAO y vemos que
/// se especifica la generalización (<T>) como Persona
/// esto permite que los métodos heredados donde se solicita
/// una parámetro T, en esta clase serán de tipo Persona
 */
package Libreria.persistencia;

import Libreria.entidades.Editorial;
import javax.persistence.NoResultException;

/**
 *
 * @author Elio
 */
public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void eliminar(Editorial objeto) {
        super.eliminar(objeto);
    }

    @Override
    public void editar(Editorial objeto) {
        super.editar(objeto);
    }

    @Override
    public void guardar(Editorial objeto) {
        super.guardar(objeto);
    }

    public Editorial BuscarEditorialPorNombre(String nombre) {
        try {
            conectar();
            Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            desconectar();

            return editorial;

        } catch (NoResultException e) {
            return null;
        }
    }
}
