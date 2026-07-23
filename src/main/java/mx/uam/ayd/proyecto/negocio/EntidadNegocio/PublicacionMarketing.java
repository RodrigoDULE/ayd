package mx.uam.ayd.proyecto.negocio.EntidadNegocio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class PublicacionMarketing {

    //atributios 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPublicacion;

    private boolean estado;
    private enum Plataforma {Facebook, Instagram, Youtube, Tiktok};
    private Plataforma plataforma;
    private LocalDate fechaProgramada;

    


    //constructor
    public PublicacionMarketing(boolean estado, Plataforma plataforma, LocalDate fechaProgramada){

        this.estado=estado;
        this.plataforma=plataforma;
        this.fechaProgramada=fechaProgramada;
    }

    public PublicacionMarketing(){
    
    }





    //cardinalidad con producto
    @OneToMany 
    private List <Producto> produtos_publi;



    //getters y setters
    public long getIdPublicacion(){
        return idPublicacion;
    }

    public boolean getEstado(){
        return estado;
    }

    public Plataforma getPlataforma(){
        return plataforma;
    }

    public LocalDate getFechaProgramada(){
        return fechaProgramada;
    }

    public void setEstado(boolean estado){
        this.estado=estado;
    }

    public void setPlataforma(Plataforma plataforma){
        this.plataforma=plataforma;
    }

    public void setFechaProgramada(LocalDate fechaProgramada){
        this.fechaProgramada=fechaProgramada;
    }

    public void setIdPublicacion(long idPublicacion){
        this.idPublicacion=idPublicacion;
    }
}
