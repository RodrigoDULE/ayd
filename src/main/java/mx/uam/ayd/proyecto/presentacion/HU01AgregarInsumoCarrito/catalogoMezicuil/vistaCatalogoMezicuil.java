package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

/**
 * vistaCatalogoMezicuil
 */
@Component
public class vistaCatalogoMezicuil {

    @FXML
    private TextField buscaProducto;

    public void muestra(List<Producto> prod){
        System.out.println("Los elementos dentro del arreglo son los siguientes: ");

        for(Producto p : prod){
            System.out.println(p);
        }

        System.out.println();
    }
}