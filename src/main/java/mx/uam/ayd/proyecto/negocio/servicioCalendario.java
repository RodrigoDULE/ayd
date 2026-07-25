package mx.uam.ayd.proyecto.negocio;
import java.time.LocalDate;
import java.util.List;

//leo D
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;
import mx.uam.ayd.proyecto.datos.RepositorioEvento;
import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Empleado;
import mx.uam.ayd.proyecto.datos.RepositorioEmpleado;




@Service
public class servicioCalendario {

    //constructor 
    private RepositorioEvento repoEvento;
    public servicioCalendario(RepositorioEvento repoEvento){
        this.repoEvento = repoEvento;
    }



    //metodo para recuperar eventos por fecha (del evento)
    
    public List<Evento> recuperaEventoporFecha(LocalDate fechaE){
        return (List<Evento>) repoEvento.findByFechaE(fechaE);
    }
    
    
    //recuperar eventos por notificacion
    public Evento recuperaPorNotificacion(LocalDate notificacion){
        return repoEvento.findByNotificacion(notificacion);
    }

}

