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

/**
 * Entidad de negocio de la HU-04 (Formulario de Marketing).
 *
 * Representa la configuración que el usuario define en el
 * "Configurador de Contenido" antes de iniciar la generación
 * automatizada: qué tipo de contenido quiere, en qué plataformas
 * se publicará, cuántas variaciones generar, para cuándo, y qué
 * archivos de referencia adjuntó.
 *
 * Es la clase que en el diagrama de secuencia se crea con:
 * new FormularioMarketing(datos, instanciaArchivos)
 */
@Entity
public class FormularioMarketing {

    /**
     * Las 3 opciones de tipo de contenido que muestra el
     * "Configurador de Contenido" (Solo Texto / Imagen Estática / Video-Reel).
     * Es un enum interno, igual que EstadoOrden dentro de OrdenDeCompra.
     */
    public enum TipoContenido {
        SOLO_TEXTO,
        IMAGEN_ESTATICA,
        VIDEO_REEL
    }

    /**
     * Agrupa los datos capturados en el formulario (todo excepto los
     * archivos, que se manejan aparte). Es el "datosFormulario" que
     * viaja de la vista al controlador y al servicio en el diagrama
     * de secuencia, y el primer parámetro del constructor de abajo.
     *
     * No es una entidad: es solo un contenedor de datos (DTO).
     */
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
    // generación. mappedBy indica que ArchivoReferencia es quien tiene
    // la llave foránea (relación bidireccional uno-a-muchos).
    // cascade + orphanRemoval: si se guarda/borra el formulario, sus
    // archivos se guardan/borran junto con él automáticamente.
    @OneToMany(mappedBy = "formularioMarketing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArchivoReferencia> archivos = new ArrayList<>();

    /**
     * Constructor vacío. JPA lo necesita internamente para poder
     * reconstruir el objeto cuando lo lee de la base de datos.
     */
    public FormularioMarketing() {
    }

    /**
     * Constructor principal: recibe los datos ya empaquetados en un
     * DatosFormulario y la lista de archivos ya convertidos a
     * ArchivoReferencia. Coincide tal cual con el diagrama de
     * secuencia: new FormularioMarketing(datos, instanciaArchivos).
     */
    public FormularioMarketing(DatosFormulario datos, List<ArchivoReferencia> archivos) {
        this.tipoContenido = datos.getTipoContenido();
        this.plataformasDestino = datos.getPlataformasDestino();
        this.cantidadVariaciones = datos.getCantidadVariaciones();
        this.fechaEstimadaPublicacion = datos.getFechaEstimadaPublicacion();
        agregarArchivos(archivos);
    }

    /**
     * Asocia una lista de archivos a este formulario.
     *
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
    // Los usa JPA internamente y también el resto de las capas
    // (servicio, controlador) para leer/modificar los datos del formulario.

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