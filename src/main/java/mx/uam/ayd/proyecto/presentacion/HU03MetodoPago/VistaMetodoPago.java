package mx.uam.ayd.proyecto.presentacion.HU03MetodoPago;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class VistaMetodoPago {

    private ControlMetodoPago controlador;
    private Stage stage;
    private Scene scenePrincipal;
    private Scene sceneConfirmation;

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

        String tarjeta = numeroTarjeta.getText();
        String nombre = nombreTarjeta.getText();
        String fecha = fechaExpiracion.getText();
        String codigo = cvv.getText();

        System.out.println("Número de tarjeta: " + numeroTarjeta);
        System.out.println("Nombre en la tarjeta: " + nombreTarjeta);
        System.out.println("Fecha de expiración: " + fechaExpiracion);
        System.out.println("CVV: " + cvv);
    }

}
