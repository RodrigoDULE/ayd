package mx.uam.ayd.proyecto.negocio;

import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.repositoriocarritoCompra;
import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.datos.repositorioProducto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Producto;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.carritoCompra;

@Service
public class servicioCarritoCompra {
    
    private final repositoriocarritoCompra repoCarrito;
    private final repositorioCliente repoCliente;
    private final repositorioProducto repositorioProd;
    private int sobrantes;
    
    // Añadimos ambos repositorios al constructor
    public servicioCarritoCompra(repositoriocarritoCompra repoCarrito, repositorioCliente repoCliente, repositorioProducto rp) {
        this.repoCarrito = repoCarrito;
        this.repoCliente = repoCliente;
        this.repositorioProd = rp;
    }

    // Cambié el retorno a 'boolean' para que coincida exactamente con tu diagrama
    // de secuencia
    /*
    System.out.println("Producto agregado al dueño: " + idUsuario);
    Cliente pertenece = repoCliente.findByIdCliente(idUsuario);
    producto.setingresarIdDueño(pertenece);
    rp.save(producto);
    */
    public boolean agregarItem(long idUsuario, Producto producto, int cantidad) {
        //verificamos si el cliente cuenta con un carrito de compra
        //recuperamos al cliente con el Id, va a carrito ---- carritoCompra----idCarrito
        Cliente dueño = repoCliente.findByIdCliente(idUsuario);
        sobrantes = 0;

        if(dueño.getCarritoCompra() == null){
            System.out.println("No tiene carrito, creando carrito");
            carritoCompra nuevo = new carritoCompra();
            //agreamos producto dentro del carrito
            nuevo.setProducto(producto);
            //agregamos la cantidad total de unidades del mismmo producto
            nuevo.setCantidadTotalCompra(cantidad);
            //agregamos precio total
            float total = producto.getPrecio() * cantidad;
            nuevo.setTotalCalculado(total);
            
            dueño.setcarritoCompra(nuevo);
            //decrementamos los productos en existencia
            sobrantes = producto.getcantidadStock() - cantidad;
            producto.setcantidadStock(sobrantes);
            //agregamos las unidades que el clietne quiere del producto
            repoCarrito.save(nuevo); //dara error si no lo ponemos ya que el id del carrito dentro de usuario es unicamente una refererncia y apuntaria a null
            //guardamos al dueño con la referencia del carrito
            repoCliente.save(dueño);
            //actualizamos el la cantidad dentro de stock;
            repositorioProd.save(producto);
            return true;
        }
        
        System.out.println("Ya tiene carrito, guardando");
        carritoCompra posee = dueño.getCarritoCompra(); // capturamos la referencia del objeto en memoria
        carritoCompra viejo = repoCarrito.findByIdCarrito(posee.getid());
        
        viejo.setProducto(producto);
        viejo.setCantidadTotalCompra(cantidad);
        //calculamos total de compra
        float total = producto.getPrecio() * cantidad;
        viejo.setTotalCalculado(total);
        dueño.setcarritoCompra(viejo);
        //decrementamos los productos en existencia
        sobrantes = producto.getcantidadStock() - cantidad;
        producto.setcantidadStock(sobrantes);
        //agregamos las unidades que el clietne quiere del producto
        repositorioProd.save(producto);
        //guardamos el carrito y el el cliente con se carrito actualizado
        repoCarrito.save(viejo);
        repoCliente.save(dueño);
        
        return true;

    }
}