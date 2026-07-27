package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;

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

            // 1. Creamos los Insumos con SKUs reales
            Insumo mezcalEspadin = new Insumo("Mezcal Espadin Joven a Granel", 180.00, "Destilería Los Abuelos S.A. de C.V.", "Litros", "MEZ-ESP-01");
            Insumo botellasVidrio = new Insumo("Botellas de Vidrio 750ml Tipo B", 15.50, "Destilería Los Abuelos S.A.", "Unidades", "BVS-750-01");
            Insumo etiquetasFinas = new Insumo("Etiquetas Finas de México", 3.15, "Etiquetas Finas S.A.", "Unidades", "ETQ-FIN-001");

            repositorioInsumo.save(mezcalEspadin);
            repositorioInsumo.save(botellasVidrio);
            repositorioInsumo.save(etiquetasFinas);

            // --- ORDEN 1: Pendiente de Revisión ---
            OrdenDeCompra ordenAbuelos = new OrdenDeCompra();
            ordenAbuelos.setEstadoOrden(EstadoOrden.REVISION_PENDIENTE);
            ordenAbuelos.setFactura("PO-2026-089"); 
            ordenAbuelos.setFechaCreacion(LocalDate.now());
            
            DetalleOrden detalle1 = new DetalleOrden();
            detalle1.setCantidad(500); 
            detalle1.setInsumo(mezcalEspadin);
            detalle1.setSubTotalLote(500 * mezcalEspadin.getPrecio());
            
            DetalleOrden detalle2 = new DetalleOrden();
            detalle2.setCantidad(700); 
            detalle2.setInsumo(botellasVidrio);
            detalle2.setSubTotalLote(700 * botellasVidrio.getPrecio()); 

            ordenAbuelos.agregarDetalle(detalle1);
            ordenAbuelos.agregarDetalle(detalle2);

            double totalOrden1 = detalle1.getSubTotalLote() + detalle2.getSubTotalLote(); 
            ordenAbuelos.setTotalOrdenCompra(totalOrden1); 
            repositorioOrden.save(ordenAbuelos);

            // --- ORDEN 2: Pendiente de Revisión ---
            OrdenDeCompra ordenEtiquetas = new OrdenDeCompra();
            ordenEtiquetas.setEstadoOrden(EstadoOrden.REVISION_PENDIENTE);
            ordenEtiquetas.setFactura("PO-2026-044");
            ordenEtiquetas.setFechaCreacion(LocalDate.now());
            
            DetalleOrden detalle3 = new DetalleOrden();
            detalle3.setCantidad(1000); 
            detalle3.setInsumo(etiquetasFinas);
            detalle3.setSubTotalLote(1000 * etiquetasFinas.getPrecio()); 

            ordenEtiquetas.agregarDetalle(detalle3);
            ordenEtiquetas.setTotalOrdenCompra(detalle3.getSubTotalLote()); 
            repositorioOrden.save(ordenEtiquetas);

            // --- ORDEN 3: ENVIADA (Especial para probar Recepción de Mercancía HU-07) ---
            OrdenDeCompra ordenRecepcion = new OrdenDeCompra();
            ordenRecepcion.setEstadoOrden(EstadoOrden.ENVIADA); // Estado correcto para recibir
            ordenRecepcion.setFactura("PO-2023-102");
            ordenRecepcion.setFechaCreacion(LocalDate.now().minusDays(5)); // Se creó hace 5 días
            ordenRecepcion.setFechaEnvio(LocalDate.now().minusDays(2));    // Se envió hace 2 días
            
            // Cantidades pequeñas para no estar 2 horas escaneando en las pruebas
            DetalleOrden detalle4 = new DetalleOrden();
            detalle4.setCantidad(3); // Solo escanearemos 3 botellas
            detalle4.setInsumo(botellasVidrio);
            detalle4.setSubTotalLote(3 * botellasVidrio.getPrecio()); 

            DetalleOrden detalle5 = new DetalleOrden();
            detalle5.setCantidad(2); // Y solo 2 etiquetas
            detalle5.setInsumo(etiquetasFinas);
            detalle5.setSubTotalLote(2 * etiquetasFinas.getPrecio()); 

            ordenRecepcion.agregarDetalle(detalle4);
            ordenRecepcion.agregarDetalle(detalle5);
            ordenRecepcion.setTotalOrdenCompra(detalle4.getSubTotalLote() + detalle5.getSubTotalLote()); 
            
            repositorioOrden.save(ordenRecepcion);

            System.out.println("datos de prueba creados con éxito. Se generaron 3 órdenes.");
        } else {
            System.out.println("ya existen órdenes en la base de datos, nada nuevo que hacer.");
        }
    }
}