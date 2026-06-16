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

        PreparedStatement ps;
        ResultSet rs;

        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) { // next Pregunta si tenemos un registro para leer
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDescuento(rs.getInt("descuento"));
                return  true; // Quiere decir que si encontramos el registro
            }


        }catch (Exception e) {
            System.out.println("Error al recuperar cliente por id: " +e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e ){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return false;
    }



    @Override
    public boolean  agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        // ResultSet rs; solo sirve cuando recuperamos informacion

        String sql = "INSERT INTO cliente(nombre, apellido, descuento) "
                + " VALUES(?, ?, ?)"; // Parametros Posicionales (1°. 2°. 3°)
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getDescuento());
            ps.execute();

                return  true; // Quiere decir que si encontramos el registro

        }catch (Exception e) {
            System.out.println("Error al agregar cliente " +e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e ){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {

        PreparedStatement ps;
        Connection con = getConexion();
        // ResultSet rs; solo sirve cuando recuperamos informacion

        String sql = "UPDATE cliente SET nombre=?, apellido=?, descuento =?"// Parametros Posicionales (1°. 2°. 3°)
                + " WHERE id = ?"; // 4° Parametro

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre()); // Los valores lo obtenemos del objeto creado
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getDescuento());
            ps.setInt(4,cliente.getId()); // Valor del ID

            ps.execute(); // Ejecuta la sentencia

            return  true; // Quiere decir que si encontramos el registro

        }catch (Exception e) {
            System.out.println("Error al agregar cliente " +e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }


        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    static void main() {
        IClienteDAO clienteDao = new ClienteDAO();
        // LISTAR CLIENTES
        /*System.out.println("*** LISTAR CLIENTES ***");


        var clientes = clienteDao.listarClientes();
        clientes.forEach(System.out::println); // Imprime toda la base de datos*/

        // BUSCAR POR ID
       /*  var cliente1 = new Cliente(6);
        System.out.println("Cliente antes de la busqueda: " + cliente1);
       var encontrado = clienteDao.buscarClientePorId(cliente1);
        if (encontrado) {
            System.out.println("Cliente encontrado "+ cliente1);
        } else {
            System.out.println("No se encontro cliente " + cliente1.getId());
        }
        */
        // AGREGAR CLIENTE
         /*   var nuevoCliente = new Cliente("Danoi","Adonis",22); // Recordar que el descuento es Unico
         // Ya tenemos descuentos de 25-30-35-40 , si colocamos uno igual , no se agregará a la lista.
            var agregado = clienteDao.agregarCliente(nuevoCliente);

            if (agregado){
                System.out.println("Cliente Agregado: "+ nuevoCliente);
            }else {
                System.out.println("El cliente no se agrego correctamente " + nuevoCliente );
            }*/
        // Modificar Cliente
        var modificarCliente = new Cliente(8,"Eustaqui","Melendez",21);
        var modificado = clienteDao.modificarCliente(modificarCliente);
        if(modificado) System.out.println("Cliente Modificado" + modificarCliente);
        else System.out.println("No se modificó Cliente" + modificarCliente);

        // listando los clientes para saber si se agregó correctamente
        System.out.println("LISTA DE CLIENTES ");
            var clientes= clienteDao.listarClientes();
            clientes.forEach(System.out::println);

    }
}