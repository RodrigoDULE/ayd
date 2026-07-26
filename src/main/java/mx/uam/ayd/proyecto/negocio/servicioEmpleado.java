package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.datos.RepositorioEmpleado;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class servicioEmpleado {

    private final RepositorioEmpleado repoEmpleado;

    // asignacion
    public servicioEmpleado(RepositorioEmpleado repoEmpleado) {
        this.repoEmpleado = repoEmpleado;
    }

    public List<String> obtenerNombreEmpleados() {
        return repoEmpleado.findAllNombres();
    }

    public List<Empleado> obtenerEmpleadosPorNombre(List<String> nombresEmpleado) {
        return repoEmpleado.findByNombreEmpleadoIn(nombresEmpleado);
    }
}
