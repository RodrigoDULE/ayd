package mx.uam.ayd.proyecto.negocio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.datos.RepositorioInsumo;
import mx.uam.ayd.proyecto.datos.RepositorioOrdenDeCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Insumo;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DetalleOrden;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.OrdenDeCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.OrdenDeCompra.EstadoOrden;

@Component
public class DatosPruebaInitializer implements CommandLineRunner {

    private final RepositorioInsumo repositorioInsumo;
    private final RepositorioOrdenDeCompra repositorioOrden;

    public DatosPruebaInitializer(RepositorioInsumo repositorioInsumo, RepositorioOrdenDeCompra repositorioOrden) {
        this.repositorioInsumo = repositorioInsumo;
        this.repositorioOrden = repositorioOrden;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (repositorioOrden.count() == 0) {
            System.out.println(">>> [DEBUG] Inicializando datos de prueba para las Órdenes de Compra...");

            // Creamos los Insumos
            Insumo mezcalEspadin = new Insumo("Mezcal Espadin Joven a Granel", 180.00, "Destilería Los Abuelos S.A. de C.V.", "Litros");
            Insumo botellasVidrio = new Insumo("Botellas de Vidrio 750ml Tipo B", 15.50, "Vidrios Oaxaqueños S.A.", "Unidades");
            Insumo etiquetasFinas = new Insumo("Etiquetas Finas de México", 3.15, "Etiquetas Finas S.A.", "Unidades");

            repositorioInsumo.save(mezcalEspadin);
            repositorioInsumo.save(botellasVidrio);
            repositorioInsumo.save(etiquetasFinas);

            // Primer orden de compra
            OrdenDeCompra ordenAbuelos = new OrdenDeCompra();
            ordenAbuelos.setEstadoOrden(EstadoOrden.REVISION_PENDIENTE);
            ordenAbuelos.setFactura("PO-2023-089"); 
            
            DetalleOrden detalle1 = new DetalleOrden();
            detalle1.setCantidad(500); 
            detalle1.setInsumo(mezcalEspadin);
            detalle1.setSubTotalLote(500 * mezcalEspadin.getPrecio()); // <-- Corregido con 'S' mayúscula
            
            DetalleOrden detalle2 = new DetalleOrden();
            detalle2.setCantidad(700); 
            detalle2.setInsumo(botellasVidrio);
            detalle2.setSubTotalLote(700 * botellasVidrio.getPrecio()); // <-- Corregido con 'S' mayúscula

            ordenAbuelos.agregarDetalle(detalle1);
            ordenAbuelos.agregarDetalle(detalle2);

            // Calculamos el total de la orden 
            double totalOrden1 = detalle1.getSubTotalLote() + detalle2.getSubTotalLote(); // <-- Corregido con 'S' mayúscula
            ordenAbuelos.setTotalOrdenCompra(totalOrden1); 

            repositorioOrden.save(ordenAbuelos);

            // compra 2
            OrdenDeCompra ordenEtiquetas = new OrdenDeCompra();
            ordenEtiquetas.setEstadoOrden(EstadoOrden.REVISION_PENDIENTE);
            ordenEtiquetas.setFactura("PO-2023-044");
            
            DetalleOrden detalle3 = new DetalleOrden();
            detalle3.setCantidad(1000); 
            detalle3.setInsumo(etiquetasFinas);
            detalle3.setSubTotalLote(1000 * etiquetasFinas.getPrecio()); // <-- Corregido con 'S' mayúscula

            ordenEtiquetas.agregarDetalle(detalle3);
            ordenEtiquetas.setTotalOrdenCompra(detalle3.getSubTotalLote()); // <-- Corregido con 'S' mayúscula

            repositorioOrden.save(ordenEtiquetas);

            System.out.println("datos de prueba creados con éxito");
        } else {
            System.out.println("ya existen esas ordenes en la base de datos");
        }
    }
}