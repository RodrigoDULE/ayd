package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;

//Servicio de negocio de HU-04 encargado de "generar" el contenido de marketing a partir de un FormularioMarketing ya guardado.
@Service
public class ServicioGeneracionContenido {

    /**
     * Representa una variación de contenido generada (ej. "Variación A:
     * Enfoque Visual"). Va anidada aquí, no en EntidadNegocio, porque
     * NO es una entidad de base de datos: solo existe mientras se
     * genera y se muestra en pantalla, nunca se persiste. Mismo patrón
     * que ya usan con OrdenDeCompra.EstadoOrden.
     */
    public static class VariacionContenido {

        private String nombre;
        private String descripcion;

        public VariacionContenido(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    //Genera (de forma simulada) las variaciones de contenido paravel formulario recibido. Es el método que llama

    public List<VariacionContenido> generarVariaciones(FormularioMarketing formularioGuardado) {
        return simularGeneracion(formularioGuardado);
    }

    //Simula la generación: arma una variación por cada letra (A, B, C...) hasta completar 
    // cantidadVariaciones, usando el tipo de contenido del formulario como referencia
    private List<VariacionContenido> simularGeneracion(FormularioMarketing formulario) {
        List<VariacionContenido> variaciones = new ArrayList<>();

        int cantidad = formulario.getCantidadVariaciones() != null ? formulario.getCantidadVariaciones() : 1;
        String tipoContenido = formulario.getTipoContenido() != null
                ? formulario.getTipoContenido().name()
                : "CONTENIDO";

        for (int i = 0; i < cantidad; i++) {
            char letra = (char) ('A' + i);
            String nombre = "Variación " + letra;
            String descripcion = "Contenido de tipo " + tipoContenido + " generado automáticamente (simulado).";
            variaciones.add(new VariacionContenido(nombre, descripcion));
        }

        return variaciones;
    }
}