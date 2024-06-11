package com.gonzao.red_social.dao;

import com.gonzao.red_social.model.Mensajes;
import database.DbConnection;

import java.sql.*;
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
            }

        }catch (SQLException e){
            System.out.println(cantListMessages);
            e.printStackTrace();
        }
        return list;
    }

    public static void borrarMensajeDB(int id_mensaje){
        Connection conexion = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = conexion.prepareStatement("DELETE FROM mensajes WHERE id_mensaje = ?")){
            ps.setInt(1,id_mensaje);
            ps.executeUpdate();
            System.out.println(deletedMessage);
        }catch (SQLException e){
            System.out.println(cantDeleteMessages);
            e.printStackTrace();
        }
    }

    public static void actualizarMensajeDB(Mensajes mensaje){
        Connection conexion = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = conexion.prepareStatement("UPDATE mensajes SET mensajes = ? WHERE id_mensaje = ?")) {
            ps.setString(1,mensaje.getMensaje());
            ps.setInt(2,mensaje.getId_mensaje());
            ps.executeUpdate();
            System.out.println("Mensaje actualizado correctamente");

        }catch (SQLException e){
            System.out.println("No se pudo actualizar el mensaje");
            e.printStackTrace();
        }
    }
}
