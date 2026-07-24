package mx.uam.ayd.proyecto.presentacion.Eventos;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento.controlAgendarNuevoEvento;

@Component
public class ControlEventos {

    private VistaEventos vista;
    private controlAgendarNuevoEvento controlAgendarNuevoEvento;

    @Autowired
    public ControlEventos(VistaEventos vista, controlAgendarNuevoEvento controlAgendarNuevoEvento) {
        this.vista = vista;
        this.controlAgendarNuevoEvento = controlAgendarNuevoEvento;
    }

    @PostConstruct
    public void init() {
        vista.setControlador(this);
    }

    public void inicia() {
        vista.inicia();
    }

    public void abrirAgendarEvento() {
        // Lógica para abrir la vista de agendar evento
        System.out.println("Abrir Agendar Evento");
        controlAgendarNuevoEvento.iniciaVentanaAgendarNuevoEvento();
    }

    public void abrirCalendario() {
        // Lógica para abrir la vista del calendario
        System.out.println("Abrir Calendario");
    }

}