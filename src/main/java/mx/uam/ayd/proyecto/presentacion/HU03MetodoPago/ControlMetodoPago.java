package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ControlMetodoPago {
    private final VistaMetodoPago vistaMetodoPago;

    @Autowired
    public ControlMetodoPago(VistaMetodoPago vistaMetodoPago) {
        this.vistaMetodoPago = vistaMetodoPago;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaMetodoPago.setControlador(this);
    }

    public void iniciaVentanaMetodoPago() {
        vistaMetodoPago.muestra();
    }
}
