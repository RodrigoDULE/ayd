package mx.uam.ayd.proyecto.presentacion.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.conffigPD.gestionCliente;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil.controladorCatalogoMezicuil;
import mx.uam.ayd.proyecto.presentacion.HU04FormularioMarketing.ControlFormularioMarketing;
import mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento.controlAgendarNuevoEvento;
import mx.uam.ayd.proyecto.presentacion.HU_06.ControladorOrdenesPendientes;


@Component
public class controladorPrincipal {

    private final ServicioCliente servicioCliente;
    private final controladorCatalogoMezicuil controlTiendaLinea;
    private final vistaPrincipal ventanaPrincipal;
    private final ControlFormularioMarketing controlFormularioMarketing;
    private final controlAgendarNuevoEvento controlAgendarNuevoEvento;
    private final ControladorOrdenesPendientes controladorOrdenes;

    private Cliente sesionActiva;

    @Autowired
    public controladorPrincipal(controladorCatalogoMezicuil controlTiendaLinea, vistaPrincipal ventanPrincipal,
            ServicioCliente servicioCliente, ControlFormularioMarketing controlFormularioMarketing,
            controlAgendarNuevoEvento controlAgendarNuevoEvento, ControladorOrdenesPendientes controladorOrdenes) {
        this.controlTiendaLinea = controlTiendaLinea;
        this.ventanaPrincipal = ventanPrincipal;
        this.servicioCliente = servicioCliente;
        this.controlFormularioMarketing = controlFormularioMarketing;
        this.controlAgendarNuevoEvento = controlAgendarNuevoEvento;
        this.controladorOrdenes = controladorOrdenes;
    }

    // inicializamos la ventana
    @PostConstruct
    public void inicializar() {
        ventanaPrincipal.setControlPrincipal(this);
    }

    public void inicia() {
        ventanaPrincipal.muestra(null);
    }

    public void visitaTiendaLinea() {
        controlTiendaLinea.inicia();
    }

    public void agendaNuevoEvento() {
        controlAgendarNuevoEvento.iniciaVentanaAgendarNuevoEvento();
    }

    public void abreFormularioMarketing() {
        controlFormularioMarketing.iniciaVentanaFormularioMarketing();
    }

    // control hacia HU_6 de Jean

    public void irAVentanaOrdenesCreadas()
    {
        controladorOrdenes.iniciaVentanaOrdenesCreadas();
    }

    // fin

    public void buscaCliente(String Nombre) {

        sesionActiva = servicioCliente.dameCliente(Nombre);
        // establecemos el id del usuario en el gestionCliente
        gestionCliente.getInstance().iniciarSesion(sesionActiva.getidCliente());
        System.out.println("EL id del usuaario que ingreso al sistema es: " + gestionCliente.getInstance().getIdActivo());
        if (sesionActiva != null) {
            ventanaPrincipal.muestra(sesionActiva.getNombre());
        } else {
            ventanaPrincipal.mostrarMensaje("Ingresa un usuario registrado.");
        }
    }
}