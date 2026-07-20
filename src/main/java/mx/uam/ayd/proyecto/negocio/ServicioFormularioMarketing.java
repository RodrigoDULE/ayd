package mx.uam.ayd.proyecto.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositorioFormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.ArchivoReferencia;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing.DatosFormulario;

/**
 * Servicio de negocio de HU-04 encargado de registrar el Formulario
 * de Marketing: arma las entidades a partir de los datos capturados
 * en la vista y los guarda a través del repositorio.
 */
@Service
public class ServicioFormularioMarketing {

    private final RepositorioFormularioMarketing repositorioFormularioMarketing;

    public ServicioFormularioMarketing(RepositorioFormularioMarketing repositorioFormularioMarketing) {
        this.repositorioFormularioMarketing = repositorioFormularioMarketing;
    }

    /**
     * Registra el formulario: crea los ArchivoReferencia y el
     * FormularioMarketing con los datos recibidos, y los guarda.
     *
     * Es el método que llama ControlFormularioMarketing — coincide
     * con registrarFormulario(datosFormulario, archivo) del diagrama
     * de secuencia (aquí "archivo" es una lista de archivos).
     *
     * NOTA: el tipo de "archivos" (aquí java.io.File) es un supuesto
     * mío, porque todavía no construimos la vista. Ajústalo cuando
     * definamos con qué componente se cargan los archivos ahí.
     */
    public FormularioMarketing registrarFormulario(DatosFormulario datos, List<File> archivos) {

        List<ArchivoReferencia> instanciasArchivos = crearArchivosReferencia(archivos);

        FormularioMarketing formulario = new FormularioMarketing(datos, instanciasArchivos);

        return repositorioFormularioMarketing.save(formulario);
    }

    //Convierte cada File cargado por el usuario en un ArchivoReferencia, 
    // quedándose solo con su metadata (nombre, tipo, tamaño),
    private List<ArchivoReferencia> crearArchivosReferencia(List<File> archivos) {
        List<ArchivoReferencia> resultado = new ArrayList<>();
        if (archivos == null) {
            return resultado;
        }
        for (File archivo : archivos) {
            String nombre = archivo.getName();
            String tipo = obtenerExtension(nombre);
            Long tamanio = archivo.length();
            resultado.add(new ArchivoReferencia(nombre, tipo, tamanio));
        }
        return resultado;
    }

    /** Obtiene la extensión del archivo (ej. "pdf") a partir de su nombre. */
    private String obtenerExtension(String nombreArchivo) {
        int idx = nombreArchivo.lastIndexOf('.');
        if (idx == -1 || idx == nombreArchivo.length() - 1) {
            return "";
        }
        return nombreArchivo.substring(idx + 1).toLowerCase();
    }
}