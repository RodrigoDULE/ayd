
package mx.uam.ayd.proyecto.datos;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.OrdenDeCompra;
import java.util.List;


public interface RepositorioOrdenDeCompra extends CrudRepository<OrdenDeCompra, Long>
{
    //public OrdenDeCompra findById(long id);
    public List<OrdenDeCompra> findByEstado(OrdenDeCompra.EstadoOrden estado);
}


