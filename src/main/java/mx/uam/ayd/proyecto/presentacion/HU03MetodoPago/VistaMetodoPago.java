package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class VistaMetodoPago {

    private ControlMetodoPago controlador;
    private Stage stage;
    private Scene scenePrincipal;

    private boolean initialized = false;

    @FXML
    private TextField numeroTarjeta;
    @FXML
    private TextField nombreTarjeta;
    @FXML
    private TextField fechaExpiracion;
    @FXML
    private TextField cvv;

    public VistaMetodoPago() {
    }

    public void setControlador(ControlMetodoPago control) {
        this.controlador = control;
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
            stage.setTitle("Método de Pago");

            FXMLLoader loaderPrincipal = new FXMLLoader(
                    getClass().getResource("/fxml/ventana-metodo-pago.fxml"));
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
        stage.setScene(scenePrincipal);
        stage.show();
    }

    @FXML
    public void realizarPago() {
        if (validaDatosTarjeta()) {
            String tarjeta = numeroTarjeta.getText();
            String nombre = nombreTarjeta.getText();
            String fecha = fechaExpiracion.getText();
            String codigo = cvv.getText();
            System.out.println("Número de tarjeta: " + tarjeta);
            System.out.println("Nombre en la tarjeta: " + nombre);
            System.out.println("Fecha de expiración: " + fecha);
            System.out.println("CVV: " + codigo);
        } else {
            System.out.println("Error al realizar el pago");
        }
    }

    private boolean validaDatosTarjeta() {
        // Número de tarjeta: exactamente 16 dígitos
        if (!numeroTarjeta.getText().matches("\\d{16}")) {
            mostrarMensaje("El número de tarjeta debe tener 16 dígitos");
            return false;
        }

        // Nombre: no vacío y sin números
        if (nombreTarjeta.getText().isBlank()) {
            mostrarMensaje("El nombre no puede estar vacío");
            return false;
        }

        if (nombreTarjeta.getText().matches(".*\\d.*")) {
            mostrarMensaje("El nombre no puede contener números");
            return false;
        }

        // Fecha de expiración: formato MM/AA
        if (!fechaExpiracion.getText().matches("\\d{2}/\\d{2}")) {
            mostrarMensaje("La fecha de expiración debe tener el formato MM/AA");
            return false;
        }

        // CVV: exactamente 3 dígitos
        if (!cvv.getText().matches("\\d{3}")) {
            mostrarMensaje("El CVV debe tener 3 dígitos");
            return false;
        }
        mostrarMensaje("Datos de tarjeta válidos");
        // Todos los datos son válidos
        return true;
    }

    private void mostrarMensaje(String mensaje) {
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
