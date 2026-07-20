package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.datos.repositorioProducto;
import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

@ExtendWith(MockitoExtension.class)
public class servicioCarritoCompraTest {

    @Mock
    private repositoriocarritoCompra repoCarrito;
    @Mock
    private repositorioCliente repoCliente;
    @Mock
    private repositorioProducto repositorioProd;

    @InjectMocks
    private servicioCarritoCompra servicioCarritoCompra;

    @Test
    void testagregarItem() {
        // caso 1: retorna falso si es que no se pudo agregar el producto

        // 1. PREPARACIÓN (Arrange)
        long idUsuario = 1L;
        int cantidad = 2; // La cantidad que intentamos agregar
        Producto productoMock = Mockito.mock(Producto.class);

        // Configuramos el comportamiento: simulamos que NO hay stock (<= 0)
        Mockito.when(productoMock.getcantidadStock()).thenReturn(0);

        // 2. EJECUCIÓN (Act)
        // Llamamos al método real de nuestro servicio inyectado
        boolean resultado = servicioCarritoCompra.agregarItem(idUsuario, productoMock, cantidad);

        // 3. VERIFICACIÓN (Assert)
        // Verificamos que el resultado sea efectivamente 'false'
        assertFalse(resultado);

        // caso 2: retorna verdadero si es que se agrego el producto exitosamente
        Cliente clienteFalso = new Cliente();
        // recuperamos el cliente
        when(repoCliente.findByIdCliente(idUsuario)).thenReturn(clienteFalso);
        // configiramos el comportamiento simulando que hay mas productos
        when(productoMock.getcantidadStock()).thenReturn(5);

        boolean resultado2 = servicioCarritoCompra.agregarItem(idUsuario, productoMock, cantidad);
        assertTrue(resultado2);
    }

    @Test
    void testrecuperaProductoEnCarrito() {
        // Caso 1: recupera el carrito con productos dentro
        long idActivo = 1L;
        Cliente cliente_falso = new Cliente();
        // regresa un cliente falso
        when(repoCliente.findByIdCliente(idActivo)).thenReturn(cliente_falso);
        carritoCompra miCarrito = new carritoCompra();
        Producto productoFalso = new Producto(); // o un mock de producto
        // Le agregamos el producto al carrito
        miCarrito.setProducto(productoFalso);
        // Y le asignamos el carrito al cliente
        cliente_falso.setcarritoCompra(miCarrito);
        carritoCompra car = servicioCarritoCompra.recuperaProductoEnCarrito(idActivo);
        assertNotNull(car, "El carrito devuelto no debería ser null");


        // caso 2: se retorna Null
        Cliente Falso2 = new Cliente();
        when(repoCliente.findByIdCliente(idActivo)).thenReturn(Falso2);
        carritoCompra res = servicioCarritoCompra.recuperaProductoEnCarrito(idActivo); 
        assertNull(res);

    }


    @Test
    void testEliminarProdCarrito(){
        //given
        long idActivo = 1L;
        Cliente clFalso = new Cliente();
        carritoCompra car = new carritoCompra();
        Producto prod = new Producto();
        car.setProducto(prod);
        clFalso.setcarritoCompra(car);

        when(repoCliente.findByIdCliente(idActivo)).thenReturn(clFalso); //Se activa el cliente

        //when 
        boolean res = servicioCarritoCompra.EliminarProdCarrito(idActivo, prod);

        //then
        assertTrue(res);
    }
}
