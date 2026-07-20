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

    //Las 3 opciones de tipo de contenido que muestra 
    public enum TipoContenido {
        SOLO_TEXTO,
        IMAGEN_ESTATICA,
        VIDEO_REEL
    }

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
    // Archivos de referencia que el usuario cargó como base para la generación. 
    // cascade + orphanRemoval: si se guarda/borra el formulario, sus
    // archivos se guardan/borran junto con él automáticamente.
    @OneToMany(mappedBy = "formularioMarketing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoReferencia> archivos = new ArrayList<>();

    //Constructor vacío. JPA lo necesita internamente para poderreconstruir el objeto cuando lo lee de la base de datos.
    public FormularioMarketing() {
    }


    //Constructor con todos los datos capturados en el formulario. Es el que usa ServicioFormularioMarketing para crear la instancia
    public FormularioMarketing(TipoContenido tipoContenido, List<String> plataformasDestino,
            Integer cantidadVariaciones, LocalDate fechaEstimadaPublicacion, List<ArchivoReferencia> archivos) {
        this.tipoContenido = tipoContenido;
        this.plataformasDestino = plataformasDestino;
        this.cantidadVariaciones = cantidadVariaciones;
        this.fechaEstimadaPublicacion = fechaEstimadaPublicacion;
        agregarArchivos(archivos);
    }

    /**
     * Asocia una lista de archivos a este formulario.
     * No basta con hacer this.archivos.add(archivo): como la relación es
     * bidireccional, también hay que decirle a cada ArchivoReferencia
     * quién es su formulario (archivo.setFormularioMarketing(this)),
     * o JPA no sabrá guardar la relación correctamente.
     */
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