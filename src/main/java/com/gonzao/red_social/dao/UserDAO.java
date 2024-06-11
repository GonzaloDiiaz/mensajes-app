package com.gonzao.red_social.dao;

import database.DbConnection;
import com.gonzao.red_social.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    static String cantCreateUser = "\n no se pudo crear el usuario \n";
    static String createdUser = "\n usuario creado \n";
    static String cantListUser = "\n no se pudo listar los usuario \n";
    static String deletedUser = "\n usuario eliminado \n";
    static String cantDeleteUser = "\n no se pudo eliminar el usuario \n";
    static String updateUser = "\n Usuario actualizado \n";
    static String cantUpdateUser = "\n no se pudo actualizar el usuario \n";


    public static void createUser(User user){
        //OBtenemos la conexion a la base de datos
        Connection conexion = DbConnection.getInstance().getConnection();

        // Usamos try-with-resource para asegurar el cierre a la base
        try (PreparedStatement ps = conexion.prepareStatement("INSERT INTO user(email, password, fullname) values (?,?,?)")){
            ps.setString(1, user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3, user.getFullname());
            ps.executeUpdate();
            System.out.println(createdUser);
        }catch (SQLException e){
            System.out.println(cantCreateUser);
            e.printStackTrace();
        }
    }

    public static List<User> readUser(){
        Connection conexion = DbConnection.getInstance().getConnection();
        List<User> list = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM user")){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Imprime el mensaje y luego se se llama en el sql
                User u = new User();
                u.setId_user(rs.getInt(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setFullname(rs.getString(4));
                list.add(u);
            }
        }catch (SQLException e){
            System.out.println(cantListUser);
            e.printStackTrace();
        }
        return list;
    }

    public static void updateUser(User user){
        Connection conexion = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = conexion.prepareStatement("UPDATE user SET email = ?, password = ?, fullname = ? where id_user = ?")){
            ps.setString(1, user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setInt(4,user.getId_user());
            ps.executeUpdate();
            System.out.println(updateUser);
        }catch (SQLException e){
            System.out.println(cantUpdateUser);
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId){
        Connection conexion = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = conexion.prepareStatement("DELETE FROM user WHERE id_user = ?")){
            ps.setInt(1,userId);
            ps.executeUpdate();
            System.out.println(deletedUser);
        }catch (SQLException e){
            System.out.println(cantDeleteUser);
            e.printStackTrace();
        }
    }

    public static User loginDB(User user){
        Connection conexion = DbConnection.getInstance().getConnection();

        try (PreparedStatement ps = conexion.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            // Añadir un mensaje de depuración para verificar la consulta SQL generada
            System.out.println("Consulta SQL ejecutada: " + ps.toString());

            ResultSet rs = ps.executeQuery();

            User login = new User(); // Aquí se crea un nuevo objeto User

            if(rs.next()){
                System.out.println("Inicio de sesión correcto!");
                login.setId_user(rs.getInt("id_user"));
                login.setEmail(rs.getString("email"));
                login.setFullname(rs.getString("fullname"));
            } else {
                System.out.println("Inicio de sesión fallido. No se encontró ningún usuario con las credenciales proporcionadas.");
            }
            return login; // Aquí se devuelve el objeto User creado, incluso si no se encontró ningún usuario
        } catch (SQLException e) {
            System.out.println("No se pudo autenticar con el servidor");
            e.printStackTrace();
        }
        return null;
    }

}
