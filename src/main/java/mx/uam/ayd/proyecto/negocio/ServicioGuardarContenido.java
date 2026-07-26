package mx.uam.ayd.proyecto.negocio;
//leo D
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.FormularioMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.PublicacionMarketing;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.ArchivoReferencia;
import mx.uam.ayd.proyecto.datos.RepositorioFormularioMarketing;
import mx.uam.ayd.proyecto.datos.RepositorioPublicacionMarketing;
import mx.uam.ayd.proyecto.datos.RepositorioArchivoReferencia;
import mx.uam.ayd.proyecto.datos.RepositorioEvento;


@Service
public class ServicioGuardarContenido {

    
    //constructor 
    private RepositorioArchivoReferencia repoArchivoReferencia;
    private RepositorioFormularioMarketing repoFormularioMarketing;
    public ServicioGuardarContenido(RepositorioFormularioMarketing repoFormularioMarketing, RepositorioArchivoReferencia repoArchivoReferencia){
        this.repoFormularioMarketing = repoFormularioMarketing;
        this.repoArchivoReferencia = repoArchivoReferencia;

    }


    
    public void guardarFormulario(){


    }

    public void obtenerFormularios(){


    }

    public void buscarFormulario(){



    }

    public void actualizarFormulario(){


    }

    /*public void publicarFormulario  (Long idFormulario, Plataforma plataforma){

    FormularioMarketing formulario = repoFormularioMarketing.findById(idFormulario);

    if(formulario.getIdPublicacion()!=null){
        throw new Exception("El contenido ya fue publicado.");
    }

    }
*/





}
