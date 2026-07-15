package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.DetallesProductoAgregarCarrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javafx.fxml.FXML;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal.controladorCarritoPrincipal;

/**
 * controladorDetallesProductoAgregarCarrito
 */
@Component
public class controladorDetallesProductoAgregarCarrito {

    private final servicioCarritoCompra agregarCarrito;
    private final vistaDetallesProductoAgregarCarrito vistaDetalle;

    // conectar con HU02
    private final controladorCarritoPrincipal controlCarrito;

    @Autowired
    public controladorDetallesProductoAgregarCarrito(servicioCarritoCompra agregarCarrito,
            vistaDetallesProductoAgregarCarrito vistaDetalles,
            controladorCarritoPrincipal controladorCarritoPrincipal) {
        this.agregarCarrito = agregarCarrito;
        this.controlCarrito = controladorCarritoPrincipal;
        this.vistaDetalle = vistaDetalles;
    }

    @PostConstruct
    public void inicializa() {
        vistaDetalle.setControlador(this);
    }

    public void agregarProductoaCarrito(long idUsuario, Producto producto, int cantidad) {
        try {

            boolean bandera = agregarCarrito.agregarItem(idUsuario, producto, cantidad);

            if (bandera) {
                vistaDetalle.mostrarMensaje("Producto agregado correctamente");
            }

        } catch (Exception e) {
            vistaDetalle.mostrarMensaje("Error: si quieres modificar la cantidad, hazlo desde la ventana Carrito");
        }
    }

    @FXML
    public void visitaCarritoCompra(long idUsuario){
        controlCarrito.iniciaVentanaCarrito(idUsuario);
    }
}