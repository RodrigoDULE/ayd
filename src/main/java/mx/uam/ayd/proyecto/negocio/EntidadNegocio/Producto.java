package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProducto;

    private String Nombre;
    private float Precio;
    private String Descripcion;
    private int CantidadStock;
    private String tipoProd;
    private int contenidoMCL;
    private float GradoAlcohol;

    public Producto(){}
    public Producto(String Nombre, float precio, String Descripcion, int CantidadStock, int contenidoMCL, float GradoAlcohol, String tipoProd){
        this.Nombre = Nombre;
        this.Precio = precio;
        this.Descripcion = Descripcion;
        this.CantidadStock = CantidadStock;
        this.tipoProd = tipoProd;
        this.contenidoMCL = contenidoMCL;
        this.GradoAlcohol = GradoAlcohol;
    }

    //realizamos getters
    public long getidProducto(){return idProducto;}
    public String getNombre(){return Nombre;}
    public float getPrecio(){return Precio;}
    public String getDescripcion(){return Descripcion;}
    public int getcantidadStock(){return CantidadStock;}
    public int getcontenidoMCL(){return contenidoMCL;}
    public float getGradoAlcohol(){ return GradoAlcohol;}
    public String getTipoProd(){ return tipoProd;}

    //realizamos setters
    public void setidProducto(long idProducto){this.idProducto = idProducto;}
    public void setNombre(String Nombre){this.Nombre = Nombre;}
    public void setPrecio(float Precio){this.Precio = Precio;}
    public void setDescripcion(String Descripcion){this.Descripcion = Descripcion;}
    public void setcantidadStock(int CantidadStock){this.CantidadStock = CantidadStock;}
    public void setcontenidoMCL(int contenidoMCL){this.contenidoMCL = contenidoMCL;}
    public void setGradoAlcohol(float GradoAlcohol){ this.GradoAlcohol = GradoAlcohol;}
    public void setGradoAlcohol(String tipoProd){ this.tipoProd = tipoProd;}
}
