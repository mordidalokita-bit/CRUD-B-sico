package tiendita.dominio.presentacion;

import tiendita.dominio.Modelo.Cliente;
import tiendita.dominio.datos.ClienteDAO;
import tiendita.dominio.datos.IClienteDAO;

import java.sql.SQLOutput;
import java.util.Scanner;

public class TiendaApp {

    public static void main(String[] args){
        tienditaApp();

    }

    private  static  void tienditaApp(){

        // definimos variables necesarias
        var salir  = false;
        var consola = new Scanner(System.in);

        // Crear Objetos de la clase clienteDao

        IClienteDAO clienteDao = new ClienteDAO();

        while (!salir) {
            try {
               var opcion =  mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion,clienteDao);

            } catch (Exception e) {
                System.out.println("Error al ejecutar las Opciones " + e.getMessage() );
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {

        System.out.println("""
                *** Tiendita Rockstar
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Clientes
                4. Modificar Clientes
                5. Eliminar Clientes 
                6. Salir
                Elije una cuate: \s""");
        return Integer.parseInt(consola.nextLine());

    }

// UTILIZAMOS LA INTERFACE DE LA CLASE PARA SOLO ACCEDER A LOS METODOS DE LA CLASE
 private static boolean ejecutarOpciones(Scanner consola,
                                         int opcion, IClienteDAO clienteDAO) {

     var salir = false;
     switch (opcion) {
         case 1 -> {// LISTAR CLIENTES
             System.out.println("--- LISTADO DE CLIENTES ---");
             var clientes = clienteDAO.listarClientes();
             clientes.forEach(System.out::println);
         }

         case 2 -> {//
             System.out.println("--- BUSCAR CLIENTE POR ID ---");
             System.out.println("Introduce el ID del cliente");

             var idClientes = Integer.parseInt(consola.nextLine());
             var cliente = new Cliente(idClientes);
             var encontrado = clienteDAO.buscarClientePorId(cliente);

             if(encontrado) System.out.println("Cliente encontrado: " + cliente);
             else {
                 System.out.println("Cliente no encontrado "+cliente);
             }
         }
         case 3 -> {//
             System.out.println("--- AGREGAR CLIENTES ---");
             System.out.println("Nombre: ");
             var nombre = consola.nextLine();
             System.out.println("Apellido: ");
             var apellido = consola.nextLine();
             System.out.println("Descuento: ");
             var descuento = Integer.parseInt(consola.nextLine());

             // OBJETO DEL CLIENTE AGREGADO
             var cliente= new Cliente(nombre,apellido,descuento);
            var agregado = clienteDAO.agregarCliente(cliente);
            if(agregado)
             System.out.println("Cliente agregado: " + cliente);
            else {
                System.out.println("No se agrego cliente: " +cliente);
            }
         }
         case 4 -> {
             System.out.println("--- MODIFICAR CLIENTES ---");

             System.out.println("ID de cliente: ");
             var id = Integer.parseInt(consola.nextLine()); // Porque es entero
             System.out.println("Nombre: ");
             var nombre = consola.nextLine();
             System.out.println("Apellido");
             var apellido = consola.nextLine();
             System.out.println("Descuento: ");
             var descuento = Integer.parseInt(consola.nextLine());

             var cliente =  new Cliente(id,nombre,apellido,descuento);
             var modificado = clienteDAO.modificarCliente(cliente);
             if (modificado){
                 System.out.println("Cliente Modificado "+cliente);
             } else {
                 System.out.println("Cliente no modificado "+cliente);
             }
         }
         case 5 -> {
             System.out.println("--- ELIMINAR  CLIENTES ---");

             System.out.println("ID Cliente: ");
             var idCliente = Integer.parseInt(consola.nextLine());

             var cliente = new Cliente(idCliente);

             var eliminado = clienteDAO.eliminarCliente(cliente);

             if (eliminado){
                 System.out.println("Cliente eliminado: "+ cliente);
             } else {
                 System.out.println("No se elimino Cliente: "+cliente);
             }

         }
         case 6 -> {
             System.out.println(" SALIENDO...");
             salir = true;
         }
         default -> System.out.println("Opcion no reconocida" + opcion);

     }
    return salir;

 }

}