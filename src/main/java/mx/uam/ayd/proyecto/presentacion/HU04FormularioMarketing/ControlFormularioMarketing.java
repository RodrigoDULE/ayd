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

// TODO: Paso 1. Cuando crees la siguiente HU, importa su controlador aquí.
// import mx.uam.ayd.proyecto.presentacion.HUNueva.ControlNuevaHU;

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
    
    // TODO: Paso 2. Declara la variable del nuevo controlador.
    // private final ControlNuevaHU controlNuevaHU;

    @Autowired
    public ControlFormularioMarketing(VistaFormularioMarketing vistaFormularioMarketing,
            ServicioFormularioMarketing servicioFormularioMarketing,
            ServicioGeneracionContenido servicioGeneracionContenido
            /* TODO: Paso 3. Agrega el controlador al constructor */
            /* , ControlNuevaHU controlNuevaHU */) {
        
        this.vistaFormularioMarketing = vistaFormularioMarketing;
        this.servicioFormularioMarketing = servicioFormularioMarketing;
        this.servicioGeneracionContenido = servicioGeneracionContenido;
        
        // TODO: Paso 4. Inicializa la variable.
        // this.controlNuevaHU = controlNuevaHU;
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
     * secuencia.
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
     * Se ejecuta cuando el usuario da clic en "Elegir" sobre una
     * variación específica (imagen o texto). Pasa la referencia exacta 
     * a la siguiente historia de usuario.
     */
    public void seleccionarVariacion(VariacionContenido variacionElegida) {
        
        // Acción temporal para que puedas probar que los botones de la vista funcionan
        vistaFormularioMarketing.mostrarMensaje("Elegiste: " + variacionElegida.getNombre());
        
        // TODO: Paso 5. Cuando construyas la otra HU, borra el 'mostrarMensaje' de arriba
        // y descomenta estas dos líneas para hacer el cambio de pantalla:
        
        // vistaFormularioMarketing.cerrarVentana();
        // controlNuevaHU.iniciaVentana(variacionElegida);
    }
}