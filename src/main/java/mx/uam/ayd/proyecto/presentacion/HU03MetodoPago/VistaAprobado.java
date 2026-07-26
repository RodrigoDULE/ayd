package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
public class VistaAprobado {

    private boolean initialized = false;
    private ControlAprobado controlador;
    private Stage stage;
    private Scene scenePrincipal;
    @FXML
    private Label lblFecha;

    @FXML
    private Label lblMetodo;

    @FXML
    private Label lblMonto;

    public VistaAprobado() {
    }

    public void setControlador(ControlAprobado controlador) {
        this.controlador = controlador;
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
            stage.setTitle("Pago Aprobado");

            FXMLLoader loaderPrincipal = new FXMLLoader(
                    getClass().getResource("/fxml/ventana_Aprobado.fxml"));
            loaderPrincipal.setController(this);
            scenePrincipal = new Scene(loaderPrincipal.load());

            initialized = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void muestra() {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::muestra);
            return;
        }
        inicializarUI();
        mostrarDatos(LocalDate.now(), 100);
        stage.setScene(scenePrincipal);
        stage.show();
    }

    public void mostrarDatos(LocalDate fecha, double monto) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        lblFecha.setText(fecha.format(formato));
        lblMetodo.setText("Tarjeta de Crédito");
        lblMonto.setText(String.format("$%,.2f", monto));
    }

    @FXML
    private void handleVolverInicio() {
        stage.close();
    }
}