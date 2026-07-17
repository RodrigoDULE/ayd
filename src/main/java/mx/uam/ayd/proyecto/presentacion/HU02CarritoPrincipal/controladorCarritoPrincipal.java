package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

@Component
public class controladorCarritoPrincipal {
    
    private final vistaCarritoPrincipal vistaCarritoPrincipal;
    private final repositoriocarritoCompra carritoCompra;
    private final servicioCarritoCompra servicioCarritoCompra;
    private long idActivo;
    
    @Autowired
    public controladorCarritoPrincipal(vistaCarritoPrincipal vistaCarritoPrincipal, repositoriocarritoCompra carritoCompra, servicioCarritoCompra servicioCarritoCompra){
        this.vistaCarritoPrincipal = new vistaCarritoPrincipal();
        this.carritoCompra = carritoCompra;
        this.servicioCarritoCompra=servicioCarritoCompra;
    }

    public void iniciaVentanaCarrito(long idUsuario) {
        idActivo = idUsuario;
        List<Producto> listaProducto = servicioCarritoCompra.recuperaProductoEnCarrito(idUsuario);

        vistaCarritoPrincipal.muestraCarrito(listaProducto);
    }


}
