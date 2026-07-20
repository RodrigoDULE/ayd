package mx.uam.ayd.proyecto.datos;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;

public interface RepositorioFormularioMarketing extends CrudRepository<FormularioMarketing, Long> {

}