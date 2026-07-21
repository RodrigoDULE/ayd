package mx.uam.ayd.proyecto.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mx.uam.ayd.proyecto.BaseIntegrationTest;
import mx.uam.ayd.proyecto.conffigPD.singleton;
import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.datos.repositorioProducto;
import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;
import mx.uam.ayd.proyecto.negocio.servicioCarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

public class CarritoIntegrationTest extends BaseIntegrationTest {
    
    @Autowired
    private repositorioCliente repoCliente;
    @Autowired
    private repositorioProducto repoProd;
    @Autowired
    private repositoriocarritoCompra repoCar;
    @Autowired
    private servicioCarritoCompra servicioCarrito;

    @Test
    @Transactional
    void TestAgregarProductoCarrito(){
        //given
        Producto producto = new Producto();
        Cliente cliente = new Cliente();
        producto.setcantidadStock(20);
        repoCliente.save(cliente);
        singleton.getInstance().iniciarSesion(cliente.getidCliente());

        repoProd.save(producto);
        
        //when
        boolean productoAgregado = servicioCarrito.agregarItem(producto, 5);

        //then
        assertTrue(productoAgregado);
    }

    @Test
    @Transactional
    void TestEliminarProducto(){

    }
}
