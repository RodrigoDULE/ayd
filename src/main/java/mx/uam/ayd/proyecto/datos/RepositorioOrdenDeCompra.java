
package mx.uam.ayd.proyecto.datos;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.OrdenDeCompra;


public interface RepositorioOrdenDeCompra extends CrudRepository<OrdenDeCompra, Long>
{
    public OrdenDeCompra findById(long id);
}


