package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
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


    //Inicializamos el controlador con la vista de catalogo, el postContruct sirve para ejecutar un metodo justo despues de crear el constructo e inyectar dependencias
    @PostConstruct
    public void inicializar(){
        vistaCatalogo.setControlador(this);
    }

    public void inicia(String idUsuario){
        System.out.println("EL cliente con sesion activa es: " + idUsuario);
        List<Producto> prod = servicioProducto.obtenerProductosDisponibles();
        vistaCatalogo.muestra(prod);
    }

    public void validarCriterio(String criterio){
        List<Producto> prodCriterio = new ArrayList<Producto>();
        
        if("Todo".equals(criterio)){
            prodCriterio = servicioProducto.obtenerProductosDisponibles();
            vistaCatalogo.muestra(prodCriterio);
        }else if("Mezcal".equals(criterio) || "Complementos".equals(criterio)){
            prodCriterio = servicioProducto.obtenerProductoPorCriterio(criterio);
            System.out.println("Producto encontrados: ");
            for(Producto a: prodCriterio){
                System.out.println(a);
            }
            vistaCatalogo.muestra(prodCriterio);
        }else{
            //Aqui entra el caso de la barra de busqueda
            System.out.println("Dentro de barra busqueda: "+ criterio);
            prodCriterio=servicioProducto.obtenerPorBusquedaPersonalizada(criterio);

            System.out.println("Tamaño de los porductos encontrados: "+ prodCriterio.size());
            vistaCatalogo.muestra(prodCriterio);
        }
    }
    
}