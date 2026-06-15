package tiendita.dominio.datos;

import tiendita.dominio.Modelo.Cliente;

import java.util.List;

public interface IClienteDAO {

    //AQUI CREAMOS LOS METODOS
    List<Cliente> listarClientes();
    boolean buscarClientePorId(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
