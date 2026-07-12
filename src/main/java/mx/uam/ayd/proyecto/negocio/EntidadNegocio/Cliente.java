package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import jakarta.persistence.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private int edad;

    public Cliente(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    //cada cliente le pertenece cero o un carrito, dentro de la tabla de cliente se agregara una coluna especial llamda carrito_id, puede ser nulo o uno, es como una llave foranea
    @OneToOne(targetEntity = carritoCompra.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private carritoCompra carrito;

    //Inicializamos un constructor vacio
    public Cliente(){}

    public long getidCliente(){return id;}
    public String getNombre(){return nombre;}
    public int getEdad(){return edad;}
}
