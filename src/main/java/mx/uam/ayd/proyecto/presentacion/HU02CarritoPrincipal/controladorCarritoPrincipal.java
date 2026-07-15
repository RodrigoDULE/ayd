package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;

@Component
public class controladorCarritoPrincipal {
    
    private final vistaCarritoPrincipal vistaCarritoPrincipal;
    private final repositoriocarritoCompra carritoCompra;
    private long idActivo;
    
    @Autowired
    public controladorCarritoPrincipal(vistaCarritoPrincipal vistaCarritoPrincipal, repositoriocarritoCompra carritoCompra){
        this.vistaCarritoPrincipal = new vistaCarritoPrincipal();
        this.carritoCompra = carritoCompra;
    }

    public void iniciaVentanaCarrito(long idUsuario) {
        idActivo = idUsuario;
        vistaCarritoPrincipal.muestraCarrito(idUsuario);
    }
}
