package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class carritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarrito;

    private int cantidadTotalCompra;
    private float totalCalculado;

    public carritoCompra() {}

    //Solo necesitamos saber que productos estan dentro de carrito
    @OneToMany(targetEntity = Producto.class, fetch = FetchType.EAGER)
    private List<Producto> producto = new ArrayList<>();

    // tiene que llevar metodos para calcular el precio total
    

    public long getid() {return idCarrito;}
    public List<Producto> getProductos(){return producto;}

    public int getCantidadTotalCompra() {
        return cantidadTotalCompra;
    }

    public float getTotalCalculado() {
        return totalCalculado;
    }

    //Creamos setters
    public void setIdCarrito(long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public void setCantidadTotalCompra(int cantidadTotalCompra) {
        this.cantidadTotalCompra += cantidadTotalCompra; //para que se vayan sumando a los demas producto que agregamos
    }

    public void setTotalCalculado(float totalCalculado) {
        this.totalCalculado = totalCalculado;
    }
    // Este setter reemplaza la lista completa de productos por una nueva
    public void setProducto(Producto producto) {
        this.producto.add(producto);//solo agrega un producto a la vez
    }
}
