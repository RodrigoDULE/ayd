package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Empleado;

public interface RepositorioEmpleado extends CrudRepository<Empleado, Long>{

    public Empleado findByIdEmpleado(long idEmpleado);

}
