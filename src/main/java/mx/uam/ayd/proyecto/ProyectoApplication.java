package mx.uam.ayd.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.datos.repositorioProducto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.presentacion.Principal.controladorPrincipal;

/**
 * 
 * Clase principal que arranca la aplicación
 * construida usando el principio de
 * inversión de control
 * Adaptada para usar JavaFX
 * 
 * @author Humberto Cervantes (c) 21 Nov 2022
 */
@SpringBootApplication
public class ProyectoApplication {

	private final controladorPrincipal controlPrincipal;
	private final repositorioProducto repositorioProd;
	private final repositorioCliente repositorioCliente;

	@Autowired
	public ProyectoApplication(controladorPrincipal controlPrincipal, repositorioProducto repositorioProd, repositorioCliente repositorioCliente) {
		this.controlPrincipal = controlPrincipal;
		this.repositorioProd = repositorioProd;
		this.repositorioCliente = repositorioCliente;
	}

	/**
	 * Método principal
	 *
	 * @param args argumentos de la línea de comando
	 */
	public static void main(String[] args) {
		// Launch JavaFX application
		Application.launch(JavaFXApplication.class, args);
	}

	/**
	 * Clase interna para manejar la inicialización de JavaFX
	 */
	public static class JavaFXApplication extends Application {

		private static ConfigurableApplicationContext applicationContext;

		@Override
		public void init() throws Exception {
			// Create Spring application context
			SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);
			builder.headless(false);
			applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
		}

		@Override
		public void start(Stage primaryStage) {
			// Initialize the application on the JavaFX thread
			Platform.runLater(() -> {
				applicationContext.getBean(ProyectoApplication.class).inicia();
			});
		}

		@Override
		public void stop() throws Exception {
			applicationContext.close();
			Platform.exit();
		}
	}

	/**
	 * Metodo que arranca la aplicacion
	 * inicializa la bd y arranca el controlador
	 */
	public void inicia() {
		inicializaBD();
		inicializaBDUsaurio();

		// Make sure controllers are created on JavaFX thread
		Platform.runLater(() -> {
			controlPrincipal.inicia();
		});
	}

	public void inicializaBDUsaurio(){
		repositorioCliente.save(new Cliente("Valeria", 20));
		repositorioCliente.save(new Cliente("Jose", 21));
		repositorioCliente.save(new Cliente("Leonardo",23));
		repositorioCliente.save(new Cliente("Jean", 22));
		repositorioCliente.save(new Cliente("Rodrigo", 27));
		repositorioCliente.save(new Cliente("Humberto",40));
	}

	/**
	 * Inicializa la BD con datos
	 */
	public void inicializaBD() {
		// Vamos a crear los dos grupos de usuarios

		Producto prod = new Producto();
		prod.setnombre("Lista de Productos Disponibles");
		
		repositorioProd.save(new Producto("Mezcal Espadin Joven", 450.0f, "Mezcal artesanal notas citricas y ahumado suave",
				50, 750, 40.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Mezcal Reposado con Gusano", 550.0f,
				"Reposado 6 meses en barrica de roble con gusano de maguey", 30, 750, 38.0f, "Mezcal Tradicional", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Mezcal Anejo Reserva", 850.0f, "Anejado por 14 meses, notas de vainilla y madera",
				20, 750, 40.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Mezcal Tobala Silvestre", 1100.0f,
				"Elaborado con agave silvestre, sabor complejo y floral", 15, 750, 42.0f, "Mezcal Premium", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Mezcal Tepeztate", 1250.0f, "Agave madurado por 25 anos, notas herbales intensas",
				10, 750, 45.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Mezcal Artesanal de Pechuga", 1500.0f,
				"Doble destilación con pechuga de pavo y frutas de temporada", 5, 750, 46.0f,
				"Mezcal", "/Imagenes/imagenPrincipal.jpg"));

		// Cremas de Mezcal (Menor grado de alcohol)
		repositorioProd.save(new Producto("Crema de Mezcal sabor Cafe", 250.0f,
				"Deliciosa crema dulce con toque intenso a cafe tostado", 40, 750, 15.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Crema de Mezcal sabor Pinon", 280.0f,
				"Crema dulce con autentico sabor a piñón rosa", 35, 750, 15.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Crema de Mezcal sabor Fresa", 250.0f,
				"Licor cremoso ideal para postres o digestivo", 45, 750, 15.0f, "Mezcal", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Licor de Damiana", 350.0f, "Licor herbal tradicional endulzado", 25, 750, 20.0f,
				"Mezcal", "/Imagenes/imagenPrincipal.jpg"));

		// Complementos y Accesorios (0 grado de alcohol)
		repositorioProd.save(new Producto("Sal de Gusano (100g)", 120.0f, "Sal del mar con chile costeño y gusano tostado",
				100, 100, 0.0f, "Complementos", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Sal de Chapulín (100g)", 150.0f,
				"Mezcla de chapulín oaxaqueño tostado, sal y chile", 85, 100, 0.0f, "Complementos", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Set de Vasos Veladora", 180.0f,
				"Juego de 4 vasos tradicionales de vidrio para mezcal", 40, 0, 0.0f, "Complementos", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Jícara Mezcalera Tallada", 90.0f,
				"Jícara natural tallada a mano con base de carrizo", 60, 0, 0.0f, "Complementos", "/Imagenes/imagenPrincipal.jpg"));
		repositorioProd.save(new Producto("Cerveza Artesanal con Agave", 85.0f,
				"Cerveza tipo Pale Ale con un toque de miel de agave", 120, 355, 6.0f, "Complementos", "/Imagenes/imagenPrincipal.jpg"));
	}
}
