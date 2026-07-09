package mx.uam.ayd.proyecto.datos;
import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

public interface repositorioProducto extends CrudRepository<Producto, Long>{
    
}
