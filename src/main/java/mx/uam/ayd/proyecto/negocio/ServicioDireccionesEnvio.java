package mx.uam.ayd.proyecto.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositorioDirecciones;
import mx.uam.ayd.proyecto.datos.repositorioCliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.Cliente;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio;
import mx.uam.ayd.proyecto.negocio.EntidadNegocio.DireccionEnvio.DatosDireccion;


@Service
public class ServicioDireccionesEnvio {

    private final RepositorioDirecciones repositorioDirecciones;
    private final repositorioCliente repositorioCliente;

    public ServicioDireccionesEnvio(RepositorioDirecciones repositorioDirecciones,
            repositorioCliente repositorioCliente) {
        this.repositorioDirecciones = repositorioDirecciones;
        this.repositorioCliente = repositorioCliente;
    }

    //Lista las direcciones activas del cliente 
    public List<DireccionEnvio> obtenerListaDirecciones(Cliente cliente) {
        return repositorioDirecciones.findByClienteAndActivaTrue(cliente);
    }

    //Registra una nueva dirección para el cliente: crea la entidad y la guarda.

    public DireccionEnvio registrarDireccion(DatosDireccion datos, Cliente cliente) {
        DireccionEnvio direccion = new DireccionEnvio(datos, cliente);
        return repositorioDirecciones.save(direccion);
    }

    //Marca una dirección como predeterminada.Como no hay un campo "predeterminada" en DireccionEnvio, 
    // lo que se actualiza es la referencia en Cliente (cliente.direccionPredeterminada) — así se sabe 
    // cuál lo es, comparando su id contra el de cada dirección en la lista.
    
    public boolean marcarComoPredeterminada(Long idDireccion, Cliente cliente) {
        Optional<DireccionEnvio> direccionOpt = repositorioDirecciones.findById(idDireccion);
        if (!direccionOpt.isPresent()) {
            return false;
        }

        cliente.setDireccionPredeterminada(direccionOpt.get());
        repositorioCliente.save(cliente);
        return true;
    }

    //Elimina (baja lógica) una dirección: no se borra de la base de datos, 
    // solo se marca activa=false para que ya no aparezca en la lista, sin afectar 
    // pedidos que ya la hayan usado.
    public boolean eliminarDireccion(Long idDireccion) {
        Optional<DireccionEnvio> direccionOpt = repositorioDirecciones.findById(idDireccion);
        if (!direccionOpt.isPresent()) {
            return false;
        }

        DireccionEnvio direccion = direccionOpt.get();
        direccion.setActiva(false);
        repositorioDirecciones.save(direccion);
        return true;
    }
}