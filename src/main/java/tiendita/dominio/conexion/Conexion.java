package tiendita.dominio.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conection = null;
        var baseDatos = "peluche_store";
        var url = "jdbc:mysql://localhost:3306/"+ baseDatos;
        var usuario = "root";
        var password = "Logi_Tech2";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conection = DriverManager.getConnection(url,usuario,password);
        } catch (Exception e) {
            System.out.println("Error al conectarnos a la base de datos"+ e.getMessage());

            }
        return conection;
        }

    public static void main() {
        var conexion = Conexion.getConexion();
        if(conexion != null) System.out.println("Conexion exitosa: "+conexion);
        else System.out.println("error al conectarse");
    }

}



