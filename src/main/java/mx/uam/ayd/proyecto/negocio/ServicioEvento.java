package mx.uam.ayd.proyecto.negocio;

import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.RepositorioEvento;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;

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
}
