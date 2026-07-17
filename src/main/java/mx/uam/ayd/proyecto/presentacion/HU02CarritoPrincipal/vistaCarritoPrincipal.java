package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import java.util.List;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;

@Component
public class vistaCarritoPrincipal {
    
    //Añadimos todos los atributos que estan dentro del archivo FXM que cambiaran su valor
    @FXML
    private Text cantidadTotalcarrito;
    @FXML
    private Text costoEnvioCarrito;
    @FXML
    private Text costoTotal;
    @FXML
    private FlowPane contenedorProductoscarrito;

    private Stage stage;
    private boolean inicializado = false;
    private controladorCarritoPrincipal controlcarritoPrincipal;

    // constructor vacio
    public vistaCarritoPrincipal() {
    }

    // inicializamos el controlador con un setter
    public void setControlador(controladorCarritoPrincipal carritoPrincipal) {
        this.controlcarritoPrincipal = carritoPrincipal;
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

    public void muestraCarrito(List<Producto> listaProd) {
        System.out.println("Ventana carrito mostrada");
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestraCarrito(listaProd));
            return;
        }
        
        inicializarUI();
        //borramos todo lo que ente dentro de la ventana
        contenedorProductoscarrito.getChildren().clear();

        for(Producto dentro : listaProd){
            VBox tarjeta = new VBox();
            Label nombre = new Label(dentro.getnombre());
            Button eliminarProd = new Button("Eliminar");
            Label precioUnitario = new Label("Precio Unitario: \n"+dentro.getPrecio());
            ImageView imagenProd = new ImageView(new Image(getClass().getResourceAsStream(dentro.getRutaImagen())));
            imagenProd.setFitHeight(150);
            imagenProd.setFitWidth(130);
            tarjeta.getChildren().addAll(imagenProd, nombre, precioUnitario, eliminarProd);

            // Aqui agregamos los productos en el contenedor
            contenedorProductoscarrito.getChildren().add(tarjeta);
        }



        stage.show();
    }
}
