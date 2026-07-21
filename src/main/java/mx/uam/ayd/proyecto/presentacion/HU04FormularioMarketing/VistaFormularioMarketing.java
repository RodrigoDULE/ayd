package mx.uam.ayd.proyecto.presentacion.HU04FormularioMarketing;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing.TipoContenido;
import mx.uam.ayd.proyecto.negocio.ServicioGeneracionContenido.VariacionContenido;


@Component
public class VistaFormularioMarketing {

    /** Formato de fecha usado en el campo de texto (ej. 05/09/27). */
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yy");

    /** Cuántas tarjetas de variación caben en la pantalla de resultados (diseño fijo, no dinámico). */
    private static final int MAXIMO_VARIACIONES = 3;

    private ControlFormularioMarketing control;
    private boolean initialized = false;
    private Stage stage;
    private Scene scenePrincipal;
    private Scene sceneResultados;
    private List<File> archivosSeleccionados = new ArrayList<>();

    // ===== Campos del FXML "Principal" =====

    @FXML
    private RadioButton radioSoloTexto;

    @FXML
    private RadioButton radioImagenEstatica;

    @FXML
    private CheckBox checkInstagramPost;

    @FXML
    private CheckBox checkFacebookPost;

    @FXML
    private CheckBox checkLinkedin;

    @FXML
    private CheckBox checkEmail;

    @FXML
    private Button botonSeleccionarArchivos;

    @FXML
    private TextField campoCantidadVariaciones;

    @FXML
    private TextField selectorFechaPublicacion;

    @FXML
    private Button botonIniciarGeneracion;

    // ===== Campos del FXML "Resultados" =====

    @FXML
    private Label etiquetaResumenTipoContenido;

    @FXML
    private Label etiquetaResumenPlataformas;

    @FXML
    private Label etiquetaResumenFecha;

    @FXML
    private Label contenidoVariacion1;

    @FXML
    private Label contenidoVariacion2;

    @FXML
    private Label contenidoVariacion3;

    @FXML
    private Label descripcionVariacion1;

    @FXML
    private Label descripcionVariacion2;

    @FXML
    private Label descripcionVariacion3;

    @FXML
    private Button botonGenerarDeNuevo;

    @FXML
    private Button botonFinalizar;

    public VistaFormularioMarketing() {
    }

    public void setControlador(ControlFormularioMarketing control) {
        this.control = control;
    }

    private void inicializarUI() {
        if (initialized) {
            return;
        }

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::inicializarUI);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Formulario de Marketing");

            FXMLLoader loaderPrincipal = new FXMLLoader(
                    getClass().getResource("/fxml/ventana_FormularioMarketingPrincipal.fxml"));
            loaderPrincipal.setController(this);
            scenePrincipal = new Scene(loaderPrincipal.load());

            FXMLLoader loaderResultados = new FXMLLoader(
                    getClass().getResource("/fxml/ventana_FormularioMarketingResultados.fxml"));
            loaderResultados.setController(this);
            sceneResultados = new Scene(loaderResultados.load());

            initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Abre la ventana mostrando la pantalla del formulario. */
    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
            return;
        }

        inicializarUI();
        stage.setScene(scenePrincipal);
        stage.show();
    }

    @FXML
    private void handleSeleccionarArchivos() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona los archivos de referencia");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF, TXT, DOCX", "*.pdf", "*.txt", "*.docx"));

        List<File> seleccionados = fileChooser.showOpenMultipleDialog(stage);
        if (seleccionados != null) {
            archivosSeleccionados = seleccionados;
        }
    }

    @FXML
    private void handleIniciarGeneracion() {
        if (!validarFormulario()) {
            return;
        }

        if (control != null) {
            control.procesarGeneracion(
                    obtenerTipoContenido(),
                    obtenerPlataformasSeleccionadas(),
                    obtenerCantidadVariaciones(),
                    obtenerFechaPublicacion(),
                    archivosSeleccionados);
        }
    }

    @FXML
    private void handleGenerarDeNuevo() {
        stage.setScene(scenePrincipal);
    }

    @FXML
    private void handleFinalizar() {
        if (control != null) {
            control.finalizar();
        }
    }

    /** Cierra la ventana del formulario. La usa el controlador al finalizar. */
    public void cerrarVentana() {
        if (stage != null) {
            stage.close();
        }
    }

    //Valida los campos obligatorios  Como la pantalla de resultados solo tiene 
    // 3 tarjetas fijas (no es una lista dinámica)
    private boolean validarFormulario() {
        if (obtenerTipoContenido() == null) {
            mostrarMensaje("Selecciona un tipo de contenido.");
            return false;
        }
        if (obtenerPlataformasSeleccionadas().isEmpty()) {
            mostrarMensaje("Selecciona al menos una plataforma de destino.");
            return false;
        }
        Integer cantidad = obtenerCantidadVariaciones();
        if (cantidad == null || cantidad < 1 || cantidad > MAXIMO_VARIACIONES) {
            mostrarMensaje("Indica cuántas variaciones quieres generar (entre 1 y " + MAXIMO_VARIACIONES + ").");
            return false;
        }
        if (obtenerFechaPublicacion() == null) {
            mostrarMensaje("Indica una fecha de publicación válida, formato dd/mm/aa.");
            return false;
        }
        return true;
    }

    private TipoContenido obtenerTipoContenido() {
        if (radioSoloTexto.isSelected()) {
            return TipoContenido.SOLO_TEXTO;
        }
        if (radioImagenEstatica.isSelected()) {
            return TipoContenido.IMAGEN_ESTATICA;
        }
        return null;
    }

    private List<String> obtenerPlataformasSeleccionadas() {
        List<String> plataformas = new ArrayList<>();
        if (checkInstagramPost.isSelected()) {
            plataformas.add("Instagram post");
        }
        if (checkFacebookPost.isSelected()) {
            plataformas.add("Facebook post");
        }
        if (checkLinkedin.isSelected()) {
            plataformas.add("Linkedin");
        }
        if (checkEmail.isSelected()) {
            plataformas.add("Email");
        }
        return plataformas;
    }

    private Integer obtenerCantidadVariaciones() {
        try {
            return Integer.parseInt(campoCantidadVariaciones.getText().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Convierte el texto del campo de fecha (dd/MM/yy) a LocalDate. */
    private LocalDate obtenerFechaPublicacion() {
        try {
            return LocalDate.parse(selectorFechaPublicacion.getText().trim(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Habilita de nuevo el botón de generar para cuando el usuario regrese al
     * formulario con "Generar de nuevo".
     */
    public void habilitarBotonGenerar() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::habilitarBotonGenerar);
            return;
        }
        botonIniciarGeneracion.setDisable(false);
    }

    /**
     * Muestra las variaciones generadas. Llena las 3 tarjetas fijas de la pantalla de
     * resultados; si se pidieron menos de 3, las tarjetas sobrantes
     * quedan vacías.
     */
    public void mostrarListaVariaciones(FormularioMarketing formulario, List<VariacionContenido> variaciones) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.mostrarListaVariaciones(formulario, variaciones));
            return;
        }

        etiquetaResumenTipoContenido.setText(formulario.getTipoContenido().name());
        etiquetaResumenPlataformas.setText(String.join(", ", formulario.getPlataformasDestino()));
        etiquetaResumenFecha.setText(formulario.getFechaEstimadaPublicacion().toString());

        Label[] cajasContenido = { contenidoVariacion1, contenidoVariacion2, contenidoVariacion3 };
        Label[] cajasDescripcion = { descripcionVariacion1, descripcionVariacion2, descripcionVariacion3 };

        for (int i = 0; i < cajasContenido.length; i++) {
            if (i < variaciones.size()) {
                VariacionContenido variacion = variaciones.get(i);
                if (formulario.getTipoContenido() == TipoContenido.IMAGEN_ESTATICA) {
                    // Marcador visual de imagen simulada, no hay archivo real generado.
                    cajasContenido[i].setText("🖼");
                    cajasContenido[i].setStyle("-fx-font-size: 36px;");
                } else {
                    cajasContenido[i].setText(variacion.getNombre());
                    cajasContenido[i].setStyle("");
                }
                cajasDescripcion[i].setText(variacion.getDescripcion());
            } else {
                cajasContenido[i].setText("");
                cajasDescripcion[i].setText("");
            }
        }

        stage.setScene(sceneResultados);
    }

    public void mostrarMensaje(String mensaje) {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.mostrarMensaje(mensaje));
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}