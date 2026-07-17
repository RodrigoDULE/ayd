package mx.uam.ayd.proyecto.negocio.EntidadNegocio;
import jakarta.persistence.*;


@Entity
public class DetalleOrden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private double subTotalLote;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenDeCompra ordenDeCompra;

    public DetalleOrden(){}

    public DetalleOrden(int cantidad, double subTotalLote, Insumo insumo, OrdenDeCompra ordenDeCompra)
    {

        this.cantidad = cantidad;
        this.subTotalLote = subTotalLote;
        this.insumo = insumo;
        this.ordenDeCompra = ordenDeCompra;

    }


    //getters
    public Long getId(){return id;}
    public int getCantidad(){return cantidad;}
    public double getSubTotalLote(){return subTotalLote;}
    public Insumo getInsumo(){return insumo;}
    public OrdenDeCompra getOrdenDeCompra(){return ordenDeCompra;}


    //setters
    public void setCantidad(int cantidad){this.cantidad = cantidad;}
    public void setSubTotalLote(double subTotalLote){this.subTotalLote = subTotalLote;}
    public void setInsumo(Insumo insumo){this.insumo = insumo;}
    public void setOrdenDeCompra(OrdenDeCompra ordenDeCompra){this.ordenDeCompra = ordenDeCompra;}

}
