package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Evento;

public interface RepositorioEvento extends CrudRepository<Evento, Long>{

    public Evento findByIdEvento(long idEvento);

}
