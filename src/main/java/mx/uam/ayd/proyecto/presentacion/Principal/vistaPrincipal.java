package mx.uam.ayd.proyecto.presentacion.Principal;

import java.io.IOException;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component
public class vistaPrincipal {

    private controladorPrincipal control;
    private boolean Initialized = false;
    private Stage stage;

    // Inicializo los botones que aparecen al incio de la app, vamos a hacer que la
    // app interactue con ellos
    @FXML
    private Button IngresarTienda;

    @FXML
    private TextField ingresarUsuario;

    @FXML
    private Text textoPrincipal;

    public vistaPrincipal() {
    }

    // inicializa la vista principal con la que el usuario interactuara
    private void inicializarUI() {
        if (Initialized) {
            return;
        }

        // Create UI only if we're on JavaFX thread
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::inicializarUI);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Mi Aplicación");

            // Load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-principal.fxml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 600, 450);
            stage.setScene(scene);

            Initialized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // creamos un setter para establecer un controlador a la vista
    public void setControlPrincipal(controladorPrincipal control) {
        this.control = control;
    }

    // metodo para mostrar la ventana principal
    public void muestra(String NombreUsuario) {

        if (NombreUsuario == null) {
            
            // this.control = control;
            System.out.println("Intentando abrir la ventana principal");
            if (!Platform.isFxApplicationThread()) {
                Platform.runLater(() -> this.muestra(null));
                return;
            }
            
            inicializarUI();

            // Deshabilitamos los botones al inicio del mundo
            IngresarTienda.setDisable(true);
            ingresarUsuario.setText("Rodrigo");
            stage.show();
        }else{
            System.out.println("Intentando abrir la ventana principal");
            if (!Platform.isFxApplicationThread()) {
                Platform.runLater(() -> this.muestra(NombreUsuario));
                return;
            }
            
            inicializarUI();
            // Deshabilitamos los botones al inicio del mundo
            IngresarTienda.setDisable(false);
            ingresarUsuario.setDisable(false);
            textoPrincipal.setText("Bienvenido, "+NombreUsuario);
            System.out.println(NombreUsuario);
            stage.show();
        }


    }

    // Inicializamos los metodos de los botones de la ventana principal
    @FXML
    private void handleVisitarTiendaLinea() {
        System.out.println("Boton presionado");
        if (control != null) {
            control.visitaTiendaLinea();
        }
    }

    @FXML
    private void revisarInventario() {
        if (control != null) {

        }
    }

    @FXML
    private void agendarEvento() {
        if (control != null) {

        }
    }

    @FXML
    private void ingresarUsuario() {
        if (ingresarUsuario.getText().isEmpty()) {
            System.out.println("Ingresa un usuario válido");
            return;
        }

        if (control != null) {
            control.buscaCliente(ingresarUsuario.getText());
        }
    }

}
