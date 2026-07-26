package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago.MetodoPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.presentacion.HU03MetodoPago.Aprobado.ControlAprobado;

@Component
public class ControlMetodoPago {
    private final VistaMetodoPago vistaMetodoPago;
    private final servicioCarritoCompra servicioCarritoCompra;
    private ControlAprobado controlAprobado;

    @Autowired
    public ControlMetodoPago(VistaMetodoPago vistaMetodoPago, servicioCarritoCompra servicioCarritoCompra,
            ControlAprobado controlAprobado) {
        this.vistaMetodoPago = vistaMetodoPago;
        this.servicioCarritoCompra = servicioCarritoCompra;
        this.controlAprobado = controlAprobado;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaMetodoPago.setControlador(this);
    }

    public void iniciaVentanaMetodoPago() {
        vistaMetodoPago.muestra();
    }

    public void iniciaVentanaAprobado() {
        controlAprobado.muestra();
    }

    public boolean simulacionConexionApi() {
        return servicioCarritoCompra.simulacionConexionApi();
    }

}
