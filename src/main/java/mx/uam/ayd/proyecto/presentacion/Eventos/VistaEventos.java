package mx.uam.ayd.proyecto.presentacion.Eventos;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@Component
public class VistaEventos {

    private ControlEventos controlador;

    private Stage stage;
    private Scene scene;

    private boolean initialized = false;

    @FXML
    private Button btnNuevoEvento;

    @FXML
    private Button btnCalendario;

    @FXML
    private FlowPane contenedorEventos;

    public void setControlador(ControlEventos controlador) {
        this.controlador = controlador;
    }

    public void inicia() {
        if (!initialized) {
            inicializarUI();
        }
        stage.show();
    }

    private void inicializarUI() {

        Runnable tarea = () -> {
            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/ventana-eventos.fxml"));

                loader.setController(this);

                Pane root = loader.load();

                scene = new Scene(root);

                stage = new Stage();
                stage.setTitle("Eventos");
                stage.setScene(scene);

                initialized = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        if (Platform.isFxApplicationThread()) {
            tarea.run();
        } else {
            Platform.runLater(tarea);
        }
    }

    @FXML
    private void abrirAgendarEvento() {
        controlador.abrirAgendarEvento();

    }

    @FXML
    private void abrirCalendario() {
        controlador.abrirCalendario();
    }

}
