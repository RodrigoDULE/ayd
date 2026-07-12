package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.DetallesProductoAgregarCarrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

/**
 * controladorDetallesProductoAgregarCarrito
 */
@Component
public class controladorDetallesProductoAgregarCarrito {

    private final servicioCarritoCompra agregarCarrito;
    private final vistaDetallesProductoAgregarCarrito vistaDetalle;

    @Autowired
    public controladorDetallesProductoAgregarCarrito(servicioCarritoCompra agregarCarrito, vistaDetallesProductoAgregarCarrito vistaDetalles){
        this.agregarCarrito = agregarCarrito;
        this.vistaDetalle = vistaDetalles;
    }

    @PostConstruct
    public void inicializa(){
        vistaDetalle.setControlador(this);
    }

    public boolean agregarProductoaCarrito(long idUsuario, Producto producto, int cantidad){
        boolean bandera = agregarCarrito.agregarItem(idUsuario, producto, cantidad);

        if(bandera){
            return true;
        }
        return false;
    }
}