package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class carritoCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarrito;

    private int cantidadTotalCompra;
    private float totalCalculado;

    public carritoCompra(int cantidadTotalCompra, float totalCalculado){
        this.cantidadTotalCompra = cantidadTotalCompra;
        this.totalCalculado = totalCalculado;
    }

    //tiene que llevar metodos para calcular el precio total
    public float getid(){return idCarrito;};
    public int getCantidadTotalCompra(){return cantidadTotalCompra;}
    public float getTotalCalculado(){return totalCalculado;}
}
