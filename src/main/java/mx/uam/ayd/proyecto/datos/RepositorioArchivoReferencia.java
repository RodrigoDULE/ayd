

package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.ArchivoReferencia;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;


public interface RepositorioArchivoReferencia extends CrudRepository{

    public ArchivoReferencia findByIdArchivoReferencia(long id);

    public ArchivoReferencia findByNombArchivoReferencia(String nombre);

}
