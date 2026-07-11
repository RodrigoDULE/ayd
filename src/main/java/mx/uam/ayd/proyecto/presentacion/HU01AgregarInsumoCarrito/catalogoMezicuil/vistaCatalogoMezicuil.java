package mx.uam.ayd.proyecto.presentacion.HU01AgregarInsumoCarrito.catalogoMezicuil;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

/**
 * vistaCatalogoMezicuil
 */
@Component
public class vistaCatalogoMezicuil {

    private Stage stage;
    private boolean inicializado = false;
    private controladorCatalogoMezicuil controlCatalgo;

    @FXML
    private TextField buscaProducto;

    @FXML 
    private RadioButton boxTodo;

    @FXML
    private FlowPane contenedorProductos;

    // inicializamos un constructor vacio
    public vistaCatalogoMezicuil() {
    }

    // inicializamos el metodo que permite visualzar la nueva ventana
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
            stage.setTitle("Catalogo Mezicuil");

            // Cargamos el fxml que tiene que ver con esta ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-catalogo-Mezicuil.fxml"));
            loader.setController(this); // le estamos diciendo a javafx que esta clase es la que controla el fxml
            Scene scene = new Scene(loader.load(), 650, 430);
            stage.setScene(scene);

            inicializado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // establecemos el controlador asiciado a esta ventana
    public void setControlador(controladorCatalogoMezicuil control) {
        this.controlCatalgo = control;
    }

    public void muestra(List<Producto> prod) {
        System.out.println("Se esta mostrando la ventana del catalogo de Mezicuil");
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestra(prod));
            return;
        }

        //

        inicializarUI();
        System.out.println("Los elementos dentro del arreglo son los siguientes: ");

        for (Producto p : prod) {
            VBox tarjeta = new VBox();
            Label nombre = new Label(p.getNombre());
            Label precio = new Label("$"+p.getPrecio());
            ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream(p.getRutaImagen())));
            //Le damos un tamaño bonito a la imagen
            imagen.setFitHeight(150);
            imagen.setFitWidth(130);
            tarjeta.getChildren().addAll(imagen, nombre, precio);


            //Aqui agregamos los productos en el contenedor
            contenedorProductos.getChildren().add(tarjeta);
        }

        boxTodo.setSelected(true);
        stage.show();
    }

}