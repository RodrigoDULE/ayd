package mx.uam.ayd.proyecto.negocio.EntidadNegocio;
//Leo_D_Gar
import jakarta.persistence.Entity;

//imports para la fecha y hora de un evento
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity//entidad
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar id automaticamente xd

    //definimos atributos de nuestra entidad 
    private long idEvento;
    private String nombreEvento;
    private String Lugar; 
    private int Comision;
    private int NoAsistentes;
    private LocalDate fechaE;
    private LocalTime horaIn;
    private LocalTime horaFin;
    private LocalDate notificacion;

    public Evento( String nombreEvento, String Lugar, int Comision, int NoAsistentes, LocalDate fechaE, LocalTime horaIn, LocalTime horaFin, LocalDate notificacion){
        
        this.nombreEvento = nombreEvento;
        this.Lugar = Lugar;
        this.Comision = Comision;
        this.NoAsistentes = NoAsistentes;
        this.fechaE = fechaE;
        this.horaIn=horaIn;
        this.horaFin=horaFin;
        this.notificacion=notificacion;

    }

    //contrsuctor vacio 

    public Evento(){

    }

   
    //cardinalidad con empleado desde la perspectica de evento, cada evento tiene multiples empleados
    @OneToMany
    private List<Empleado> empleados_asig;

    //cardinalidad con producto 
    //desde la perspectica de evento, cada evento tiene varios porductos asignados
    
    @OneToMany
    private List <Producto> produtos_asig;


    //ponemos getters y setters

    public long getIdEvento(){
        return idEvento;
    }
    public String getNombreEvento(){

        return nombreEvento;
    }
    public String getLugar(){
        return Lugar;
    }
    public int getComision(){

        return Comision;
    }
    public int getNoAsistentes(){
        return NoAsistentes;
    }

    public LocalDate getFechaE(){

        return fechaE;
    }

    public LocalTime getHoraIn(){
        return horaIn;
    }

     public LocalTime getHoraFin(){
        return horaFin;
    }

     public LocalDate getNotificacion(){
        return notificacion;
    }

    public void setIdEvento(long idEvento){
        this.idEvento=idEvento;
    }
    public void setNombreEvento(String nombreEvento){
        this.nombreEvento=nombreEvento;
    }

    public void setLugar(String Lugar){
        this.Lugar=Lugar;
    }
    public void setComision(int Comision){
        this.Comision=Comision;
    }
    public void setNoAsistentes(int NoAsistentes){
        this.NoAsistentes=NoAsistentes;
    }
    public void setFechaE(LocalDate fechaE){
        this.fechaE=fechaE;
    }
    public void setHoraIn(LocalTime horaIn){
        this.horaIn=horaIn;
    }
    public void setHoraFin(LocalTime horaFin){
        this.horaFin=horaFin;
    }
    public void setNotificacion(LocalDate notificacion){
        this.notificacion=notificacion;
    }

}
