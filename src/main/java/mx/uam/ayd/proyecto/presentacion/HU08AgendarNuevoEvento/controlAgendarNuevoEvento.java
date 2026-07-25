package mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioEvento;
import mx.uam.ayd.proyecto.negocio.servicioEmpleado;
import mx.uam.ayd.proyecto.presentacion.Eventos.ControlEventos;

@Component
public class controlAgendarNuevoEvento {
    private final vistaAgendarNuevoEvento vistaAgendarNuevoEvento;
    private final ServicioEvento servicioEvento;
    private final servicioEmpleado servicioEmpleado;
    private final ControlEventos controlEventos;

    @Autowired
    public controlAgendarNuevoEvento(vistaAgendarNuevoEvento vistaAgendarNuevoEvento,
            ServicioEvento servicioEvento, servicioEmpleado servicioEmpleado,
            @Lazy ControlEventos controlEventos) {
        this.vistaAgendarNuevoEvento = vistaAgendarNuevoEvento;
        this.servicioEvento = servicioEvento;
        this.servicioEmpleado = servicioEmpleado;
        this.controlEventos = controlEventos;
    }

    public void iniciaVentanaAgendarNuevoEvento() {
        vistaAgendarNuevoEvento.muestra();
    }

    @PostConstruct
    public void inicializar() {
        vistaAgendarNuevoEvento.setControladorAgendarNuevoEvento(this);
    }

    // Retorna los nombres de los empleados desde el servicio
    public List<String> obtenerNombresEmpleados() {
        return servicioEmpleado.obtenerNombreEmpleados();
    }

    public void agregarEvento(String nombre, String tipo, LocalDate fecha,
            String horaInicio, String horaFin, String acuerdo, String lugar, String notas, int noAsistentes,
            int comision) {

        LocalDate notificacion = servicioEvento.calcularUnaSemanaAntes(fecha);
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
                notificacion);

        // Refrescar la ventana de Eventos con los datos actualizados
        controlEventos.inicia();
    }

    // Regla de negocio
    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        return servicioEvento.verificarDisponibilidad(fecha, horaInicio, horaFin);
    }

}