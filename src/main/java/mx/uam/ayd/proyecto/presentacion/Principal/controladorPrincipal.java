package mx.uam.ayd.proyecto.presentacion.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil.controladorCatalogoMezicuil;

@Component
public class controladorPrincipal {
    
    private final ServicioCliente servicioCliente;
    private final controladorCatalogoMezicuil controlTiendaLinea;
    private final vistaPrincipal ventanaPrincipal;
    private Cliente sesionActiva;
    @Autowired
    public controladorPrincipal(controladorCatalogoMezicuil controlTiendaLinea, vistaPrincipal ventanPrincipal, ServicioCliente servicioCliente){
        this.controlTiendaLinea = controlTiendaLinea;
        this.ventanaPrincipal = ventanPrincipal;
        this.servicioCliente = servicioCliente;
    }

    //inicializamos la ventana
    @PostConstruct
    public void inicializar(){
        ventanaPrincipal.setControlPrincipal(this);
    }

    public void inicia(){
        ventanaPrincipal.muestra(null);
    }

    public void visitaTiendaLinea(){
        controlTiendaLinea.inicia(sesionActiva.getidCliente());
    }

    public void buscaCliente(String Nombre){
        sesionActiva = servicioCliente.dameCliente(Nombre);
        if(sesionActiva != null){
            ventanaPrincipal.muestra(sesionActiva.getNombre());
        }else{
            ventanaPrincipal.mostrarMensaje("Ingresa un usuario registrado.");
        }
    }
}
