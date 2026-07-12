package mx.uam.ayd.proyecto.negocio;

import org.springframework.stereotype.Service;
import java.util.Optional;

import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;
// Tendrás que importar el repositorio de tu Cliente (asumiendo que se llama así)
import mx.uam.ayd.proyecto.datos.repositorioCliente; 
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

@Service
public class servicioCarritoCompra {
    
    private final repositoriocarritoCompra repoCarrito;
    // Agregamos el repositorio del cliente para poder buscarlo cuando creemos un carrito nuevo
    private final repositorioCliente repoCliente; 

    // Añadimos ambos repositorios al constructor
    public servicioCarritoCompra(repositoriocarritoCompra repoCarrito, repositorioCliente repoCliente){
        this.repoCarrito = repoCarrito;
        this.repoCliente = repoCliente;
    }

    // Cambié el retorno a 'boolean' para que coincida exactamente con tu diagrama de secuencia
    public boolean agregarItem(long idUsuario, Producto producto, int cantidad){
        try {
            carritoCompra carrito = repoCarrito.findByClienteId(idUsuario); 

            if(carrito == null){
                carrito = new carritoCompra();
                
                Optional<Cliente> clienteEncontrado = repoCliente.findById(idUsuario);
                
                if(clienteEncontrado.isPresent()){
                    carrito.setCliente(clienteEncontrado.get());
                } else {
                    System.out.println("Error: El cliente con ID " + idUsuario + " no existe.");
                    return false; // Abortamos si el cliente no existe
                }
            }

            for (int i = 0; i < cantidad; i++) {
                carrito.getProductos().add(producto);
            }

            repoCarrito.save(carrito);

            return true; 

        } catch (Exception e) {
            System.out.println("Excepción al guardar en carrito: " + e.getMessage());
            return false;
        }
    }
}