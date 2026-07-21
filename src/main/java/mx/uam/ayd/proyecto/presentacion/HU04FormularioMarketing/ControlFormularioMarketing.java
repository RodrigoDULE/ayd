package mx.uam.ayd.proyecto.presentacion.HU04FormularioMarketing;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import mx.uam.ayd.proyecto.negocio.ServicioFormularioMarketing;
import mx.uam.ayd.proyecto.negocio.ServicioGeneracionContenido;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing.DatosFormulario;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing.TipoContenido;
import mx.uam.ayd.proyecto.negocio.ServicioGeneracionContenido.VariacionContenido;

/**
 * Controlador de HU-04 (Formulario de Marketing). Orquesta el flujo
 * completo descrito en el diagrama de secuencia: registrar el
 * formulario, habilitar de nuevo el botón, generar las variaciones
 * (simuladas) y mandarlas a mostrar en la vista.
 */
@Component
public class ControlFormularioMarketing {

    private final VistaFormularioMarketing vistaFormularioMarketing;
    private final ServicioFormularioMarketing servicioFormularioMarketing;
    private final ServicioGeneracionContenido servicioGeneracionContenido;

    @Autowired
    public ControlFormularioMarketing(VistaFormularioMarketing vistaFormularioMarketing,
            ServicioFormularioMarketing servicioFormularioMarketing,
            ServicioGeneracionContenido servicioGeneracionContenido) {
        this.vistaFormularioMarketing = vistaFormularioMarketing;
        this.servicioFormularioMarketing = servicioFormularioMarketing;
        this.servicioGeneracionContenido = servicioGeneracionContenido;
    }

    @PostConstruct
    private void inicializarControlador() {
        vistaFormularioMarketing.setControlador(this);
    }

    /** Abre la ventana del Formulario de Marketing. */
    public void iniciaVentanaFormularioMarketing() {
        vistaFormularioMarketing.muestra();
    }

    /**
     * procesarGeneracion(datosFormulario, archivo) del diagrama de
     * secuencia. Aquí "datosFormulario" llega desarmado en variables
     * sueltas porque así las captura la vista de los controles de
     * JavaFX; aquí mismo se empaquetan en el DatosFormulario que
     * espera el servicio.
     */
    public void procesarGeneracion(TipoContenido tipoContenido, List<String> plataformasDestino,
            Integer cantidadVariaciones, LocalDate fechaEstimadaPublicacion, List<File> archivos) {

        DatosFormulario datos = new DatosFormulario(tipoContenido, plataformasDestino,
                cantidadVariaciones, fechaEstimadaPublicacion);

        FormularioMarketing formularioGuardado = servicioFormularioMarketing.registrarFormulario(datos, archivos);

        vistaFormularioMarketing.habilitarBotonGenerar();

        List<VariacionContenido> listaVariaciones = servicioGeneracionContenido.generarVariaciones(formularioGuardado);

        vistaFormularioMarketing.mostrarListaVariaciones(formularioGuardado, listaVariaciones);
    }

    /**
     * Se ejecuta cuando el usuario da clic en "Finalizar" en la
     * pantalla de resultados. Según lo que me dijiste, ahí debería
     * abrirse la pantalla de HU-10 — pero esa HU todavía no existe
     * en el proyecto, así que por ahora solo cierra esta ventana.
     *
     * Cuando construyas HU-10, aquí es donde reemplazas esta línea
     * por la llamada a su controlador, por ejemplo algo como:
     *   controlHU10.iniciaVentana(formularioGuardado);
     */
    public void finalizar() {
        vistaFormularioMarketing.cerrarVentana();
    }
}