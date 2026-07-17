package mx.uam.ayd.proyecto.negocio.EntidadNegocio;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


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

    //después getters y setters
}