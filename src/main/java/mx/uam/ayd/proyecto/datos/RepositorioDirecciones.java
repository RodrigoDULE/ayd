package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio;

/**
 * Repositorio de la capa datos para HU-05 (Direcciones de envío).
 *
 * findByClienteAndActivaTrue solo trae las direcciones que siguen activas,
 * porque una dirección eliminada (baja lógica) no debe aparecer en
 * la lista aunque siga guardada en la base de datos.
 */
public interface RepositorioDirecciones extends CrudRepository<DireccionEnvio, Long> {

    public List<DireccionEnvio> findByClienteAndActivaTrue(Cliente cliente);
}