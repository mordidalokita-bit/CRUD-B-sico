package tiendita.dominio.Modelo;

import java.util.Objects;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private int descuento;

    //Constructor Vacio

    public Cliente(){}

    public Cliente(int id){
        this.id = id;
    }

    public Cliente(String nombre, String apellido, int descuento){
        this.nombre = nombre;
        this.apellido = apellido;
        this.descuento = descuento;

    }
     // ahora el constructor con todos sus parametros

    public Cliente(int id,String nombre, String apellido, int descuento){
        this(nombre,apellido,descuento);
        this.id= id;

    }

    // METODOS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // METODO TO STRING

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", descuento=" + descuento +
                '}';
    }

    //METODOS IQUALS Y HASH CODE, FACILITA LA BUSQUEDA , ORDENAMIENTO Y CUALQUIER
    // OPERACION QUE SEA DE BUSQUEDA
    // REPRESENTA UN VALOR NUMERO PARA CADA OBJETO DE LA CLASE


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && descuento == cliente.descuento && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, descuento);
    }
}
