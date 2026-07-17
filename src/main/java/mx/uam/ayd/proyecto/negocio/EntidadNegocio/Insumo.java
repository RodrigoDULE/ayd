package mx.uam.ayd.proyecto.negocio.EntidadNegocio;
import jakarta.persistence.*;


@Entity
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String nombre;
    private double precio;
    private String proveedor;
    private String unidadDeMedida;
    
    public Insumo() {}

    public Insumo(String nombre, double precio, String proveedor, String unidadDeMedida)
    {

        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
        this.unidadDeMedida = unidadDeMedida;

    }

    //getters

    public Long getId(){return id;}
    public String getNombre(){return nombre;}
    public double getPrecio(){return precio;}
    public String getProveedor(){return proveedor;}
    public String getUnidadDeMedida(){return unidadDeMedida;}


    //setters

    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPrecio(double precio ){this.precio = precio;}
    public void setProveedor(String proveedor){this.proveedor = proveedor;}
    public void setUnidadDeMedida(String unidadDeMedida){this.unidadDeMedida = unidadDeMedida;}

}