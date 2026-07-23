package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing.TipoContenido;

/**
 * Servicio de negocio de HU-04 encargado de "generar" el contenido de marketing
 * a partir de un FormularioMarketing ya guardado.
 */
@Service
public class ServicioGeneracionContenido {

    /**
     * Representa una variación de contenido generada.
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

    /**
     * Genera (de forma simulada) las variaciones de contenido para el formulario recibido.
     */
    public List<VariacionContenido> generarVariaciones(FormularioMarketing formularioGuardado) {
        return simularGeneracion(formularioGuardado);
    }

    /**
     * Simula la generación ajustando el texto devuelto según el TipoContenido.
     */
    private List<VariacionContenido> simularGeneracion(FormularioMarketing formulario) {
        List<VariacionContenido> variaciones = new ArrayList<>();

        int cantidad = formulario.getCantidadVariaciones() != null ? formulario.getCantidadVariaciones() : 1;
        TipoContenido tipoContenido = formulario.getTipoContenido();

        // Arreglo de frases de ejemplo para simular copys/textos de marketing
        String[] textosMarketing = {
            "¡Elixir ancestral para el alma! Descubre el sabor auténtico de nuestro mezcal artesanal, hecho 100% de agave de origen.",
            "Para todo mal, mezcal; para todo bien, también. Disfruta una tradición viva llena de carácter y aroma ahumado.",
            "Sabor que honra la tierra. Cada gota cuenta una historia de maestría, tradición y pasion palenquera. ¡Pide el tuyo!"

        };

        for (int i = 0; i < cantidad; i++) {
            char letra = (char) ('A' + i);
            String nombre;
            String descripcion;

            if (tipoContenido == TipoContenido.SOLO_TEXTO) {
                // Para SOLO_TEXTO asignamos un copy persuasivo al campo 'nombre' (que es el que se muestra al centro)
                nombre = textosMarketing[i % textosMarketing.length];
                descripcion = "Opción " + letra + ": Copy promocional orientado a conversión.";
            } else {
                // Para IMAGEN_ESTATICA u otros tipos visuales
                nombre = "Variación " + letra;
                descripcion = "Diseño gráfico adaptado para plataformas de marketing.";
            }

            variaciones.add(new VariacionContenido(nombre, descripcion));
        }

        return variaciones;
    }
}