package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.servicioProducto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

/**
 * controladorCatalogoMezicuil
 */

@Component
public class controladorCatalogoMezicuil {

    private final servicioProducto servicioProducto;
    private final vistaCatalogoMezicuil vistaCatalogo;

    @Autowired
    public controladorCatalogoMezicuil(servicioProducto servicioProducto, vistaCatalogoMezicuil vistaCatalogo){
        this.servicioProducto = servicioProducto;
        this.vistaCatalogo = vistaCatalogo;
    }

    public void inicia(){
        List<Producto> prod = servicioProducto.obtenerProductosDisponibles();
        vistaCatalogo.muestra(prod);
    }

    
    
}