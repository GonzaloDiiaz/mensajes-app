package org.example.mensaje_app.dao;

import org.example.mensaje_app.DbConnection;
import org.example.mensaje_app.model.Mensajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MensajeDAO {

    static String cantCreateMessage = "\n no se pudo crear el mensaje \n";
    static String createdMessage = "\n mensaje creado \n";
    static String cantListMessages = "\n no se pudo listar los mensajes \n";
    static String deletedMessage = "\n mensaje eliminado \n";
    static String cantDeleteMessages = "\n no se pudo eliminar el mensaje \n";

    public static void crearMensajeDB(Mensajes mensaje){
        //OBtenemos la conexion a la base de datos
        Connection conexion = DbConnection.getInstance().getConnection();

        // Usamos try-with-resource para asegurar el cierre a la base
        try (PreparedStatement ps = conexion.prepareStatement("INSERT INTO mensajes(mensaje, autor_mensaje) values (?,?)")){
            ps.setString(1, mensaje.getMensaje());
            ps.setString(2,mensaje.getAutor_mensaje());
            ps.executeUpdate();
            System.out.println(createdMessage);
        }catch (SQLException e){
            System.out.println(cantCreateMessage);
            e.printStackTrace();
        }
    }

    public static List<Mensajes> leerMensajesDB(){
        Connection conexion = DbConnection.getInstance().getConnection();
        List<Mensajes> list = new ArrayList<>();

        try(PreparedStatement ps = conexion.prepareStatement("SELECT * FROM mensajes")){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Imprime el mensaje y luego se se llama en el sql
                Mensajes m = new Mensajes();
                m.setId_mensaje(rs.getInt(1));
                m.setMensaje(rs.getString(2));
                m.setAutor_mensaje(rs.getString(3));
                m.setFecha_mensaje(rs.getString(4));
                list.add(m);

                System.out.println("ID: " + rs.getInt("id_mensaje"));
                System.out.println("Mensaje: " + rs.getString("mensaje"));
                System.out.println("Autor: " + rs.getString("autor_mensaje"));
                System.out.println("Fecha: " + rs.getString("fecha_mensaje"));
                System.out.println("");
            }

        }catch (SQLException e){
            System.out.println(cantListMessages);
            e.printStackTrace();
        }
        return list;
    }

    public static void borrarMensajeDB(int id_mensaje){

    }

    public static void actualizarMensajeDB(Mensajes mensaje){

    }
}
