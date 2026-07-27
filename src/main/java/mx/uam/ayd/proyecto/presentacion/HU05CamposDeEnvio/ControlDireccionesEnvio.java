package mx.uam.ayd.proyecto.presentacion.HU05CamposDeEnvio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioDireccionesEnvio;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio.DatosDireccion;

// 1. IMPORTAMOS EL CONTROLADOR DE LA HU-03
import mx.uam.ayd.proyecto.presentacion.HU03MetodoPago.MetodoPago.ControlMetodoPago;

/**
 * Controlador de HU-05 (Direcciones de envío). Orquesta los cuatro
 * flujos del diagrama de secuencia: listar, registrar, marcar
 * predeterminada, eliminar.
 */
@Component
public class ControlDireccionesEnvio {

    private final VistaDireccionesEnvio vistaDireccionesEnvio;
    private final ServicioDireccionesEnvio servicioDireccionesEnvio;

    // 2. DECLARAMOS EL CONTROLADOR DE PAGO
    private final ControlMetodoPago controlMetodoPago;

    /** El Cliente para el que se está gestionando direcciones en esta sesión. */
    private Cliente clienteActivo;

    // 3. LO INYECTAMOS EN EL CONSTRUCTOR
    @Autowired
    public ControlDireccionesEnvio(VistaDireccionesEnvio vistaDireccionesEnvio,
            ServicioDireccionesEnvio servicioDireccionesEnvio,
            ControlMetodoPago controlMetodoPago) {
        this.vistaDireccionesEnvio = vistaDireccionesEnvio;
        this.servicioDireccionesEnvio = servicioDireccionesEnvio;
        this.controlMetodoPago = controlMetodoPago;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaDireccionesEnvio.setControlador(this);
    }

    /**
     * Abre la ventana de direcciones para el cliente dado
     */
    public void iniciaVentana(Cliente cliente) {
        this.clienteActivo = cliente;
        actualizarListaDirecciones();
    }

    /** procesarRegistroDireccion(datosFormulario) del diagrama. */
    public void procesarRegistroDireccion(DatosDireccion datos) {
        servicioDireccionesEnvio.registrarDireccion(datos, clienteActivo);
        actualizarListaDirecciones();
    }

    /** establecerPredeterminada(idDireccion, idUsuario) del diagrama. */
    public void establecerPredeterminada(long idDireccion) {
        boolean exito = servicioDireccionesEnvio.marcarComoPredeterminada(idDireccion, clienteActivo);
        if (!exito) {
            vistaDireccionesEnvio.mostrarMensaje("No se encontró la dirección seleccionada.");
            return;
        }
        actualizarListaDirecciones();
    }

    /** solicitarEliminarDirección(idDireccion) del diagrama. */
    public void solicitarEliminarDireccion(long idDireccion) {
        boolean exito = servicioDireccionesEnvio.eliminarDireccion(idDireccion);
        if (!exito) {
            vistaDireccionesEnvio.mostrarMensaje("No se encontró la dirección a eliminar.");
            return;
        }
        actualizarListaDirecciones();
    }

    /**
     * Vuelve a pedir la lista actualizada y se la manda a la vista.
     */
    private void actualizarListaDirecciones() {
        List<DireccionEnvio> direcciones = servicioDireccionesEnvio.obtenerListaDirecciones(clienteActivo);
        vistaDireccionesEnvio.muestraDirecciones(direcciones, clienteActivo);
    }

    // ====================================================================
    // PUENTE HACIA LA HU-03 (MÉTODO DE PAGO)
    // ====================================================================
    public void continuarAlPago(DireccionEnvio direccionSeleccionada) {
        // Como el método de tu compañero aún no recibe parámetros,
        // simplemente disparamos el inicio de su ventana.
        controlMetodoPago.iniciaVentanaMetodoPago(direccionSeleccionada);

        // (Opcional) Si quieres que tu ventana de direcciones se cierre al abrir la de
        // pago,
        // tendrías que crear un método en tu vistaDireccionesEnvio.cerrar() y llamarlo
        // aquí.
    }
}