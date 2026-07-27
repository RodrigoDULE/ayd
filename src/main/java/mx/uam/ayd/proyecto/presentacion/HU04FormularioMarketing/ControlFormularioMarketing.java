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
import mx.uam.ayd.proyecto.presentacion.HU10GuardarContenido.ControladorGuardarContenido;
import mx.uam.ayd.proyecto.negocio.ServicioGeneracionContenido.VariacionContenido;



@Component
public class ControlFormularioMarketing {

    private final VistaFormularioMarketing vistaFormularioMarketing;
    private final ServicioFormularioMarketing servicioFormularioMarketing;
    private final ServicioGeneracionContenido servicioGeneracionContenido;

    private FormularioMarketing formularioActual;

    private final ControladorGuardarContenido controlGuardarContenido;
    
    // TODO: Paso 2. Declara la variable del nuevo controlador.

    @Autowired
    public ControlFormularioMarketing(VistaFormularioMarketing vistaFormularioMarketing,
            ServicioFormularioMarketing servicioFormularioMarketing,
            ServicioGeneracionContenido servicioGeneracionContenido,
            ControladorGuardarContenido controladorGuardarContenido
            /* TODO: Paso 3. Agrega el controlador al constructor */
            /* , ControlNuevaHU controlNuevaHU */) {
        
        this.vistaFormularioMarketing = vistaFormularioMarketing;
        this.servicioFormularioMarketing = servicioFormularioMarketing;
        this.servicioGeneracionContenido = servicioGeneracionContenido;
        this.controlGuardarContenido= controladorGuardarContenido;
        
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

        DatosFormulario datos = new DatosFormulario(tipoContenido, plataformasDestino,cantidadVariaciones, fechaEstimadaPublicacion);

        FormularioMarketing formularioGuardado = servicioFormularioMarketing.registrarFormulario(datos, archivos);

            formularioActual = formularioGuardado;

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
        


        vistaFormularioMarketing.cerrarVentana();

        controlGuardarContenido.iniciaVentanaGuardarContenido(
        formularioActual,
        variacionElegida
        );

    }
}