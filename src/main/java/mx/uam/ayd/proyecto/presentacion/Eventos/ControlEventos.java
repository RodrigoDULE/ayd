package mx.uam.ayd.proyecto.presentacion.Eventos;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento.controlAgendarNuevoEvento;
import mx.uam.ayd.proyecto.presentacion.HU09CalendarioDeEventos.ControladorCalendarioEventos;
import mx.uam.ayd.proyecto.presentacion.HU09CalendarioDeEventos.VistaCalendarioEventos;
import mx.uam.ayd.proyecto.negocio.ServicioEvento;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;

@Component
public class ControlEventos {

    private VistaEventos vista;
    private controlAgendarNuevoEvento controlAgendarNuevoEvento;
    private ServicioEvento servicioEvento;

    
    
    @Autowired
    private ControladorCalendarioEventos controlCalendario;
    public ControlEventos(VistaEventos vista, controlAgendarNuevoEvento controlAgendarNuevoEvento,
            ServicioEvento servicioEvento) {
        this.vista = vista;
        this.controlAgendarNuevoEvento = controlAgendarNuevoEvento;
        this.servicioEvento = servicioEvento;
    }

    @PostConstruct
    public void inicializar() {
        vista.setControlador(this);
    }

    public void inicia() {
        List<Evento> listaEventos = servicioEvento.obtenerEventos();
        vista.inicia(listaEventos);
    }

    public void abrirAgendarEvento() {
        // Lógica para abrir la vista de agendar evento
        System.out.println("Abrir Agendar Evento");
        controlAgendarNuevoEvento.iniciaVentanaAgendarNuevoEvento();
    }

    public void abrirCalendario() {
        // Lógica para abrir la vista del calendario
        System.out.println("Abrir Calendario");
       // VistaCalendarioEventos.muestraCalendario();
       controlCalendario.inicia();
    }

}