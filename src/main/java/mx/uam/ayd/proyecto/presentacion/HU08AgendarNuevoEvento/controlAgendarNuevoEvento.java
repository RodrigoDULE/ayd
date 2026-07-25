package mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioEvento;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;
import mx.uam.ayd.proyecto.presentacion.Eventos.ControlEventos;

@Component
public class controlAgendarNuevoEvento {
    private final vistaAgendarNuevoEvento vistaAgendarNuevoEvento;
    private final ServicioEvento servicioEvento;
    private final ControlEventos controlEventos;

    @Autowired
    public controlAgendarNuevoEvento(vistaAgendarNuevoEvento vistaAgendarNuevoEvento,
            ServicioEvento servicioEvento,
            @Lazy ControlEventos controlEventos) {
        this.vistaAgendarNuevoEvento = vistaAgendarNuevoEvento;
        this.servicioEvento = servicioEvento;
        this.controlEventos = controlEventos;
    }

    public void iniciaVentanaAgendarNuevoEvento() {
        vistaAgendarNuevoEvento.muestra();
    }

    @PostConstruct
    public void inicializar() {
        vistaAgendarNuevoEvento.setControladorAgendarNuevoEvento(this);
    }

    public void agregarEvento(String nombre, String tipo, LocalDate fecha,
            String horaInicio, String horaFin, String acuerdo, String lugar, String notas) {

        servicioEvento.agregarEvento(
                nombre,
                tipo,
                fecha,
                horaInicio,
                horaFin,
                acuerdo,
                lugar,
                notas);

        // Refrescar la ventana de Eventos con los datos actualizados
        controlEventos.inicia();
    }

    public void agregarEvento() {

    }
}