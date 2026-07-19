package mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal;

import java.util.List;

import org.springframework.stereotype.Component;
//librerias para escuchador
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

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

    public void muestraCarrito(carritoCompra carrito) {
        System.out.println("Ventana carrito mostrada");
        List<Producto> listaProd = carrito.getProductoenCarrito();

        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> muestraCarrito(carrito));
            return;
        }
        
        inicializarUI();
        //borramos todo lo que ente dentro de la ventana
        contenedorProductoscarrito.getChildren().clear();
        
        cantidadTotalcarrito.setText("$"+carrito.getTotalCalculado()+"MXN");

        String envio = (carrito.getenvioGratis()) ? "Envio Gratis" : "$320.00 MXN";
        costoEnvioCarrito.setText(envio);

        //contenedor siempre va a estar vacio, por eso es mejor si la lista esta vacia
        if(listaProd.isEmpty()){
                Button itCatalogo = new Button("Explora nuestros productos disponibles");
                contenedorProductoscarrito.getChildren().add(itCatalogo);

        }else{
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
                
                // Le agregamos un escuchador a las tarjetas
                eliminarProd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent evento) {
                        handleEliminarProd(dentro);
                        contenedorProductoscarrito.getChildren().remove(tarjeta); //Eliminamos la tarjeta del producto
                    }
                });
            }
        }
            stage.show();
    }

    private void handleEliminarProd(Producto p){
        if(controlcarritoPrincipal != null){
            controlcarritoPrincipal.EliminarProd(p);
        }
    }

    //Metodo para mostrar mensajes de error o advertencia
    public void mostrarMensaje(String mensaje){
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
