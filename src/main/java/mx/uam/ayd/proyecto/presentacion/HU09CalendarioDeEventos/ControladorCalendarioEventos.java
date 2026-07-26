package mx.uam.ayd.proyecto.presentacion.HU09CalendarioDeEventos;

import java.time.YearMonth;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mx.uam.ayd.proyecto.negocio.ServicioEvento;
import mx.uam.ayd.proyecto.negocio.servicioCalendario;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;


@Component
public class ControladorCalendarioEventos {

    private final VistaCalendarioEventos vistaCalendarioEventos;
    private final servicioCalendario servicioCalendario;

    //private YearMonth mesActual;
   
    public void iniciaVentanaCalendario(){
        vistaCalendarioEventos.muestraCalendario();
    }
    

    @Autowired
    public ControladorCalendarioEventos(VistaCalendarioEventos vistaCalendarioEventos, servicioCalendario servicioCalendario){
        this.vistaCalendarioEventos = vistaCalendarioEventos;
        this.servicioCalendario=servicioCalendario;
    }

    @PostConstruct
    private void inicializarControlador(){
        vistaCalendarioEventos.setControlador(this);
    }
    
    
}
