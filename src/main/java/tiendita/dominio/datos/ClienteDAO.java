package tiendita.dominio.datos;

import tiendita.dominio.Modelo.Cliente;

import java.util.List;

public class ClienteDAO implements IClienteDAO{

    // JALAMOS LOS METODOS CRUD DE LA INTERFACE IClienteDAO

    // Implementamos los metodos de IClienteDAO

    @Override
    public List<Cliente> listarClientes() {
        return List.of();
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }




 }
