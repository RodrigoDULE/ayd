package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.DetallesProductoAgregarCarrito;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

/**
 * vistaDetallesProductoAgregarCarrito
 */
@Component
public class vistaDetallesProductoAgregarCarrito {

    @FXML
    private FlowPane imagenDetalles;
    @FXML
    private TextFlow nombreDetalles;
    @FXML
    private Text precioDetalles;
    @FXML
    private TextFlow descripcionProdDetalles;

    private Stage stage;
    private boolean inicializado = false;
    private controladorDetallesProductoAgregarCarrito controlDetalles;

    // constructor vacio
    public vistaDetallesProductoAgregarCarrito() {
    }

    // inicializamos el controlador con un setter
    public void setControlador(controladorDetallesProductoAgregarCarrito controlDetalles) {
        this.controlDetalles = controlDetalles;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-detalles-producto.fxml"));
            loader.setController(this); // le estamos diciendo a javafx que esta clase es la que controla el fxml
            Scene scene = new Scene(loader.load(), 650, 430);
            stage.setScene(scene);

            inicializado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void muestraDetallesProd(Producto producto) {
        System.out.println("EL producto en el que se esta trabajando "+producto);
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestraDetallesProd(producto));
            return;
        }

        inicializarUI();

        imagenDetalles.getChildren().clear();
        descripcionProdDetalles.getChildren().clear();
        nombreDetalles.getChildren().clear();
        

        // inicializamos el panel con la informacion del producto
        nombreDetalles.getChildren().add(new Text(producto.getnombre()));
        precioDetalles.setText("$" + producto.getPrecio() + " MXN");
        descripcionProdDetalles.getChildren().add(new Text(producto.getDescripcion()));

        // Cargamos la ruta de la imagen
        ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream(producto.getRutaImagen())));
        imagen.setFitWidth(300);
        imagen.setFitHeight(330);
        imagenDetalles.getChildren().add(imagen);


        stage.show();
    }

}