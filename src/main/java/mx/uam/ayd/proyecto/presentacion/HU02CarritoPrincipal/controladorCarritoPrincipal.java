package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

@Component
public class controladorCarritoPrincipal {
    
    private final vistaCarritoPrincipal vistaCarritoPrincipal;
    private final servicioCarritoCompra servicioCarritoCompra;
    
    @Autowired
    public controladorCarritoPrincipal(vistaCarritoPrincipal vistaCarritoPrincipal, servicioCarritoCompra servicioCarritoCompra){
        this.vistaCarritoPrincipal = new vistaCarritoPrincipal();
        this.servicioCarritoCompra=servicioCarritoCompra;
    }

    @PostConstruct
    private void inicializarControlador(){
        vistaCarritoPrincipal.setControlador(this);
    }

    public void iniciaVentanaCarrito() {
        carritoCompra car = servicioCarritoCompra.recuperaProductoEnCarrito();
        if(car != null){
            vistaCarritoPrincipal.muestraCarrito(car);
        }else{
            vistaCarritoPrincipal.mostrarMensaje("Error, el cliente no tiene carrito activo");
        }
    }

    public void EliminarProd(Producto prod){
        servicioCarritoCompra.EliminarProdCarrito(prod);
    }
}
