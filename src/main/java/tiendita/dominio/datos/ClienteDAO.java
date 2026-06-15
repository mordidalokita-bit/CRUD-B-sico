package tiendita.dominio.datos;

import tiendita.dominio.Modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static tiendita.dominio.conexion.Conexion.getConexion; //Metodo Estatico

public class ClienteDAO implements IClienteDAO {

    // JALAMOS LOS METODOS CRUD DE LA INTERFACE IClienteDAO

    // Implementamos los metodos de IClienteDAO

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDescuento(rs.getInt("descuento"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar Clientes: " + e.getMessage());
        } finally {
            try {
                con.close();



            } catch (Exception e) {
                System.out.println("Error al cerrar conexión ");
            }

        }
        return clientes;
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

    static void main() {
        // LISTAR CLIENTES
        System.out.println("*** LISTAR CLIENTES ***");

        IClienteDAO clienteDao = new ClienteDAO();
        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println);


    }
}