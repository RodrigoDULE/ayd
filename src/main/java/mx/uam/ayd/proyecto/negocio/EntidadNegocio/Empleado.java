package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmpleado;

    private String nombreEmpleado;
    private String telefono;

    public Empleado(String nombreEmpleado, String telefono){
        this.nombreEmpleado=nombreEmpleado;
        this.telefono=telefono;
    }
    
    //constructor vacio
    public Empleado(){

    }

    /*
    //cardinalidad con evento
    @ManyToMany(targetEntity=Evento.class, fetch=FetchType.EAGER)
    private Evento evento;
     */

    //getters y setters
    public long getidEmpleado(){

        return idEmpleado;
    }

    public String getNombreEmpleado(){
        return nombreEmpleado;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setNombreEmpleado(String nombreEmpleado){
        this.nombreEmpleado=nombreEmpleado;
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }


}
