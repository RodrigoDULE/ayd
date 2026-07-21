package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FormularioMarketing {

    //opciones de contenido
    public enum TipoContenido {
        SOLO_TEXTO,
        IMAGEN_ESTATICA
    }

    // Agrupa los datos capturados en el formulario (todo excepto los archivos, que se manejan aparte).
    public static class DatosFormulario {

        private TipoContenido tipoContenido;
        private List<String> plataformasDestino;
        private Integer cantidadVariaciones;
        private LocalDate fechaEstimadaPublicacion;

        public DatosFormulario(TipoContenido tipoContenido, List<String> plataformasDestino,
                Integer cantidadVariaciones, LocalDate fechaEstimadaPublicacion) {
            this.tipoContenido = tipoContenido;
            this.plataformasDestino = plataformasDestino;
            this.cantidadVariaciones = cantidadVariaciones;
            this.fechaEstimadaPublicacion = fechaEstimadaPublicacion;
        }

        public TipoContenido getTipoContenido() {
            return tipoContenido;
        }

        public List<String> getPlataformasDestino() {
            return plataformasDestino;
        }

        public Integer getCantidadVariaciones() {
            return cantidadVariaciones;
        }

        public LocalDate getFechaEstimadaPublicacion() {
            return fechaEstimadaPublicacion;
        }
    }

    // Identificador autogenerado por la base de datos (clave primaria)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tipo de contenido elegido (una sola opción, tipo radio button)
    private TipoContenido tipoContenido;

    // Plataformas de destino seleccionadas por el usuario (checkboxes),
    // ej: ["Instagram Post", "Instagram Reels"]
    private List<String> plataformasDestino = new ArrayList<>();

    // Cuántas variaciones de contenido quiere generar el usuario
    private Integer cantidadVariaciones;

    // Fecha en la que el usuario planea publicar el contenido
    private LocalDate fechaEstimadaPublicacion;

    // Archivos de referencia que el usuario cargó como base para la
    // generación
    @OneToMany(mappedBy = "formularioMarketing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoReferencia> archivos = new ArrayList<>();

    //Constructor Vacío
    public FormularioMarketing() {
    }

    //recibe los datos ya empaquetados en un DatosFormulario y la lista de archivos ya convertidos 
    // a ArchivoReferencia. 
    public FormularioMarketing(DatosFormulario datos, List<ArchivoReferencia> archivos) {
        this.tipoContenido = datos.getTipoContenido();
        this.plataformasDestino = datos.getPlataformasDestino();
        this.cantidadVariaciones = datos.getCantidadVariaciones();
        this.fechaEstimadaPublicacion = datos.getFechaEstimadaPublicacion();
        agregarArchivos(archivos);
    }

    //Asocia una lista de archivos a este formulario.
    public void agregarArchivos(List<ArchivoReferencia> nuevosArchivos) {
        if (nuevosArchivos == null) {
            return;
        }
        for (ArchivoReferencia archivo : nuevosArchivos) {
            archivo.setFormularioMarketing(this);
            this.archivos.add(archivo);
        }
    }

    // --- Getters y setters ---
    public Long getId() {
        return id;
    }

    public TipoContenido getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(TipoContenido tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public List<String> getPlataformasDestino() {
        return plataformasDestino;
    }

    public void setPlataformasDestino(List<String> plataformasDestino) {
        this.plataformasDestino = plataformasDestino;
    }

    public Integer getCantidadVariaciones() {
        return cantidadVariaciones;
    }

    public void setCantidadVariaciones(Integer cantidadVariaciones) {
        this.cantidadVariaciones = cantidadVariaciones;
    }

    public LocalDate getFechaEstimadaPublicacion() {
        return fechaEstimadaPublicacion;
    }

    public void setFechaEstimadaPublicacion(LocalDate fechaEstimadaPublicacion) {
        this.fechaEstimadaPublicacion = fechaEstimadaPublicacion;
    }

    public List<ArchivoReferencia> getArchivos() {
        return archivos;
    }
}