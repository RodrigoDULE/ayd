package mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class controlAgendarNuevoEvento {
    private final vistaAgendarNuevoEvento vistaAgendarNuevoEvento;

    @Autowired
    public controlAgendarNuevoEvento(vistaAgendarNuevoEvento vistaAgendarNuevoEvento) {
        this.vistaAgendarNuevoEvento = vistaAgendarNuevoEvento;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaAgendarNuevoEvento.setControlador(this);
    }

    public void iniciaVentanaAgendarNuevoEvento() {
        vistaAgendarNuevoEvento.muestra();
    }
}