package mx.uam.ayd.proyecto.negocio;

import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.RepositorioEvento;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioEvento {
    // repo para tener acceso a la base de datos
    private final RepositorioEvento repoEvento;

    // Constructor para inyectar dependencias
    public ServicioEvento(RepositorioEvento repoEvento) {
        this.repoEvento = repoEvento;
    }

    // Metodo para obtener todos los eventos
    public List<Evento> obtenerEventos() {
        // creamos un arreglo para guardar los eventos
        List<Evento> listaEventos = new ArrayList<Evento>();

        // Recorremos la lista de eventos
        for (Evento evento : repoEvento.findAll()) {
            listaEventos.add(evento);
        }

        // Retornamos la lista de eventos
        return listaEventos;
    }

    public void agregarEvento(String nombre, String tipo, LocalDate fecha,
            String horaInicio, String horaFin, String acuerdo,
            String lugar, String notas) {

        LocalTime horaIn = LocalTime.parse(horaInicio);
        LocalTime horaFin_ = LocalTime.parse(horaFin);

        // Crear el evento usando setters (campos no capturados en FXML quedan en
        // default)
        Evento evento = new Evento();
        evento.setNombreEvento(nombre);
        evento.setTipoEvento(tipo);
        evento.setFechaE(fecha);
        evento.setHoraIn(horaIn);
        evento.setHoraFin(horaFin_);
        evento.setAcuerdoEconomico(acuerdo);
        evento.setLugar(lugar);
        evento.setNotasAdicionales(notas);

        repoEvento.save(evento);
    }
}
