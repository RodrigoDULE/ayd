package mx.uam.ayd.proyecto.presentacion.HU09CalendarioDeEventos;

//importar paqueterias de listas
import org.springframework.stereotype.Component;
import java.util.List;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;
import mx.uam.ayd.proyecto.presentacion.HU02CarritoPrincipal.controladorCarritoPrincipal;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Empleado;




@Component
public class VistaCalendarioEventos {

    //cosas del archivo FXML
    @FXML
    private Button btnPrev;//<Button fx:id="btnPrev" text="◀"/>

    @FXML
    private Button btnNext;//<Label fx:id="lblMes" text="JUNIO 2026" style="-fx-font-size:20px;-fx-font-weight:bold;"/>
 

    @FXML
    private Label lblMes;//<Button fx:id="btnNext" text="▶"/>

    @FXML
    private GridPane calendarGrid;//<GridPane fx:id="calendarGrid" gridLinesVisible="true">

    @FXML
    private TextField txtBuscar;//<TextField fx:id="txtBuscar" promptText="Buscar evento..." HBox.hgrow="ALWAYS"/>

    @FXML
    private VBox panelEventos;//<VBox fx:id="panelEventos" spacing="10">
    


    private Stage stage;
    private boolean inicializado = false;
    private ControladorCalendarioEventos controlcalendario;

  
    // constructor vacio
    public VistaCalendarioEventos() {
    }
    
    // inicializamos el controlador con un setter
    public void setControlador(ControladorCalendarioEventos calendario) {
        this.controlcalendario = calendario;
    }


    // UI
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
            stage.setTitle("Calendario de Eventos");

            // Cargamos el fxml que tiene que ver con esta ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ventana-calendario-evento.fxml"));
            loader.setController(this); // le estamos diciendo a javafx que esta clase es la que controla el fxml
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/css/estilos-mezicuil.css").toExternalForm());//Estilo css
            stage.setScene(scene);

            inicializado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void muestraCalendario(){


        
    }
    
}








