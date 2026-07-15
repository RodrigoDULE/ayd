package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.DetallesProductoAgregarCarrito.controladorDetallesProductoAgregarCarrito;

@Component
public class vistaCarritoPrincipal {
    
    private long idActivo;
    private Producto actual;

    private Stage stage;
    private boolean inicializado = false;
    private controladorCarritoPrincipal carritoPrincipal;

    // constructor vacio
    public vistaCarritoPrincipal() {
    }

    // inicializamos el controlador con un setter
    public void setControlador(controladorCarritoPrincipal carritoPrincipal) {
        this.carritoPrincipal = carritoPrincipal;
    }

    // inicializamos UI
    private void inicializarUI() {
        if (inicializado) {
            return;
        }

        // crea UI solo si estamos en el hilo de JAVAFX
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(this::inicializarUI);
            return;
        }

        try {
            stage = new Stage();
            stage.setTitle("Detalles de Producto");

            // Cargamos el fxml que tiene que ver con esta ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-carrito-principal.fxml"));
            loader.setController(this); // le estamos diciendo a javafx que esta clase es la que controla el fxml
            Scene scene = new Scene(loader.load(), 600, 410);
            stage.setScene(scene);

            inicializado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void muestraCarrito(long idUsuario) {
        idActivo = idUsuario;

        System.out.println("Ventana carrito mostrada");
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestraCarrito(idUsuario));
            return;
        }

        inicializarUI();

        stage.show();
    }
}
