package mx.uam.ayd.proyecto.presentacion.HU08AgendarNuevoEvento;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class vistaAgendarNuevoEvento {
    private controlAgendarNuevoEvento control;
    private boolean initialized = false;
    private Stage stage;
    private Scene scenePrincipal;
    private Scene sceneConfirmation;

    @FXML
    private TextField txtNombreEvento;
    @FXML
    private ComboBox<String> cmbTipoEvento;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraFin;
    @FXML
    private ComboBox<String> cmbAcuerdo;
    @FXML
    private TextArea txtNotas;
    @FXML
    private ComboBox<String> cmbEmpleado;
    @FXML
    private ListView<String> lvEmpleados;

    @FXML
    public void initialize() {

        cmbTipoEvento.getItems().addAll(
                "Conferencia",
                "Reunión",
                "Taller");

        cmbAcuerdo.getItems().addAll(
                "Gratis",
                "Pago");

        cmbEmpleado.getItems().addAll(
                "Empleado 1",
                "Empleado 2",
                "Empleado 3");
    }

    // constructor sin parametos
    public vistaAgendarNuevoEvento() {
    }

    // set del controlador
    public void setControlador(controlAgendarNuevoEvento control) {
        this.control = control;
    }

    // método inicializar UI
    private void inicializarUI() {
        // Si la UI ya está inicializada, no hacer nada
        if (initialized) {
            return;
        }
        // Si no estamos en el hilo de JavaFX, pedir que lo haga
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::inicializarUI);
            return;
        }
        try {
            stage = new Stage();
            stage.setTitle("Agendar Nuevo Evento");

            FXMLLoader loaderPrincipal = new FXMLLoader(
                    getClass().getResource("/fxml/ventana-agendar-evento.fxml"));
            loaderPrincipal.setController(this);
            scenePrincipal = new Scene(loaderPrincipal.load());

            // Comentado temporalmente porque 'ventana-confirmacion-agendar-evento.fxml' no
            // existe en los recursos
            /*
             * FXMLLoader loaderConfirmacion = new FXMLLoader(
             * getClass().getResource("/fxml/ventana-confirmacion-agendar-evento.fxml"));
             * loaderConfirmacion.setController(this);
             * sceneConfirmation = new Scene(loaderConfirmacion.load());
             */

            initialized = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Muestra la ventana principal
    public void muestra() {
        // Si no estamos en el hilo de JavaFX, pedir que lo haga
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
            return;
        }
        // Inicializar la UI si no lo está
        inicializarUI();
        // Establecer la escena principal
        stage.setScene(scenePrincipal);
        // Mostrar la ventana
        stage.show();
    }

    @FXML
    public void agregarEmpleado() {
        String empleado = cmbEmpleado.getValue();
        if (empleado != null) {
            lvEmpleados.getItems().add(empleado);
            cmbEmpleado.setValue(null);
        }
    }

    @FXML
    public void cancelarEvento() {
        txtNombreEvento.clear();
        txtHoraInicio.clear();
        txtHoraFin.clear();
        txtNotas.clear();
        lvEmpleados.getItems().clear();
    }

    @FXML
    public void guardarEvento() {

        System.out.println("Guardando evento");
        System.out.println(
                "Evento: " + txtNombreEvento.getText());
    }

    @FXML
    public void confirmarRegistro() {

        System.out.println("Registro confirmado");

    }
}