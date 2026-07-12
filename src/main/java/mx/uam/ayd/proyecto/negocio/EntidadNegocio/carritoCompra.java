package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class carritoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarrito;

    private int cantidadTotalCompra;
    private float totalCalculado;

    public carritoCompra() {}

    @OneToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    private Cliente cliente;

    @OneToMany(targetEntity = Producto.class, fetch = FetchType.EAGER)
    private List<Producto> producto = new ArrayList<>();

    // tiene que llevar metodos para calcular el precio total
    

    public float getid() {return idCarrito;}
    public List<Producto> getProductos(){return producto;}
    public Cliente getCliente(){return cliente;}

    public int getCantidadTotalCompra() {
        this.cantidadTotalCompra = this.producto.size();

        return cantidadTotalCompra;
    }

    public float getTotalCalculado() {
        totalCalculado = 0;
        for(Producto p : producto){
            totalCalculado += p.getPrecio();
        }

        return totalCalculado;
    }

    //Creamos setters
    public void setIdCarrito(long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public void setCantidadTotalCompra(int cantidadTotalCompra) {
        this.cantidadTotalCompra = cantidadTotalCompra;
    }

    public void setTotalCalculado(float totalCalculado) {
        this.totalCalculado = totalCalculado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Este setter reemplaza la lista completa de productos por una nueva
    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }
}
