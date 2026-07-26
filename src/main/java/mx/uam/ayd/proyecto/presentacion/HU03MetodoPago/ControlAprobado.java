package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlAprobado {
    private final VistaAprobado vistaAprobado;

    @Autowired
    public ControlAprobado(VistaAprobado vistaAprobado) {
        this.vistaAprobado = vistaAprobado;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaAprobado.setControlador(this);
    }

    public void muestra() {
        vistaAprobado.muestra();
    }

}