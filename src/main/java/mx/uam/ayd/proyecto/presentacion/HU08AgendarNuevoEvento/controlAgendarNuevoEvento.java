package mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Empleado;
import mx.uam.ayd.proyecto.negocio.ServicioEvento;
import mx.uam.ayd.proyecto.negocio.servicioEmpleado;

@Component
public class controlAgendarNuevoEvento {
    private final vistaAgendarNuevoEvento vistaAgendarNuevoEvento;
    private final ServicioEvento servicioEvento;
    private final servicioEmpleado servicioEmpleado;
    private Runnable refrescarEventosCallback;

    @Autowired
    public controlAgendarNuevoEvento(vistaAgendarNuevoEvento vistaAgendarNuevoEvento,
            ServicioEvento servicioEvento, servicioEmpleado servicioEmpleado) {
        this.vistaAgendarNuevoEvento = vistaAgendarNuevoEvento;
        this.servicioEvento = servicioEvento;
        this.servicioEmpleado = servicioEmpleado;
    }

    public void iniciaVentanaAgendarNuevoEvento() {
        vistaAgendarNuevoEvento.muestra();
    }

    @PostConstruct
    public void inicializar() {
        vistaAgendarNuevoEvento.setControladorAgendarNuevoEvento(this);
    }

    public void setRefrescarEventosCallback(Runnable refrescarEventosCallback) {
        this.refrescarEventosCallback = refrescarEventosCallback;
    }

    // Retorna los nombres de los empleados desde el servicio
    public List<String> obtenerNombresEmpleados() {
        return servicioEmpleado.obtenerNombreEmpleados();
    }

    public List<Empleado> obtenerEmpleadosSeleccionados(List<String> nombresEmpleados) {
        return servicioEmpleado.obtenerEmpleadosPorNombre(nombresEmpleados);
    }

    public void agregarEvento(String nombre, String tipo, LocalDate fecha,
            String horaInicio, String horaFin, String acuerdo, String lugar, String notas, int noAsistentes,
            int comision, List<String> nombresEmpleados) {

        LocalDate notificacion = servicioEvento.calcularUnaSemanaAntes(fecha);
        List<Empleado> empleados = obtenerEmpleadosSeleccionados(nombresEmpleados);
        servicioEvento.agregarEvento(
                nombre,
                tipo,
                fecha,
                horaInicio,
                horaFin,
                acuerdo,
                lugar,
                notas,
                noAsistentes,
                comision,
                notificacion,
                empleados);

        if (refrescarEventosCallback != null) {
            refrescarEventosCallback.run();
        }
    }

    // Regla de negocio
    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        return servicioEvento.verificarDisponibilidad(fecha, horaInicio, horaFin);
    }

}