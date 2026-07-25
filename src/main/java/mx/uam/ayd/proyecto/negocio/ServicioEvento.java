package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.RepositorioEvento;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
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
            String lugar, String notas, int noAsistentes, int comision, LocalDate notificacion) {

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
        evento.setNoAsistentes(noAsistentes);
        evento.setComision(comision);
        evento.setNotificacion(notificacion);

        repoEvento.save(evento);
    }

    // Regla de negocio
    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        for (Evento evento : repoEvento.findAll()) {
            if (!fecha.equals(evento.getFechaE())) {
                continue; // distinto día, no compite
            }

            // 1. Choque directo (solapamiento)
            boolean seSolapan = horaInicio.isBefore(evento.getHoraFin()) &&
                    horaFin.isAfter(evento.getHoraIn());
            if (seSolapan) {
                return false;
            }

            // 2. Diferencia mínima de 6 horas
            Duration diferencia;
            if (horaInicio.isAfter(evento.getHoraFin()) || horaInicio.equals(evento.getHoraFin())) {
                // tu evento empieza después del existente
                diferencia = Duration.between(evento.getHoraFin(), horaInicio);
            } else {
                // tu evento termina antes de que empiece el existente
                diferencia = Duration.between(horaFin, evento.getHoraIn());
            }

            if (diferencia.toHours() < 6) {
                return false;
            }
        }
        return true;
    }

    // Regla de negocio
    public LocalDate calcularUnaSemanaAntes(LocalDate fecha) {
        return fecha.minusWeeks(1);
    }

}
