package mx.uam.ayd.proyecto.presentacion.HU05CamposDeEnvio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioDireccionesEnvio;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio.DatosDireccion;

/**
 * Controlador de HU-05 (Direcciones de envío). Orquesta los cuatro
 * flujos del diagrama de secuencia: listar, registrar, marcar
 * predeterminada, eliminar.
 */
@Component
public class ControlDireccionesEnvio {

    private final VistaDireccionesEnvio vistaDireccionesEnvio;
    private final ServicioDireccionesEnvio servicioDireccionesEnvio;

    /** El Cliente para el que se está gestionando direcciones en esta sesión. */
    private Cliente clienteActivo;

    @Autowired
    public ControlDireccionesEnvio(VistaDireccionesEnvio vistaDireccionesEnvio,
            ServicioDireccionesEnvio servicioDireccionesEnvio) {
        this.vistaDireccionesEnvio = vistaDireccionesEnvio;
        this.servicioDireccionesEnvio = servicioDireccionesEnvio;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaDireccionesEnvio.setControlador(this);
    }

    /**
     * Abre la ventana de direcciones para el cliente dado
     * (iniciaVentana(idUsuario) en el diagrama — aquí recibe el
     * Cliente completo, como ya quedamos con el compañero de equipo
     * que integra esta parte).
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
     * Unifica lo que en el diagrama son 3 llamadas con nombres
     * distintos (actualizarVista, refrescarInterfaz,
     * removerDireccionDePantalla) — las tres terminan haciendo lo
     * mismo: refrescar la lista completa.
     */
    private void actualizarListaDirecciones() {
        List<DireccionEnvio> direcciones = servicioDireccionesEnvio.obtenerListaDirecciones(clienteActivo);
        vistaDireccionesEnvio.muestraDirecciones(direcciones, clienteActivo);
    }
}