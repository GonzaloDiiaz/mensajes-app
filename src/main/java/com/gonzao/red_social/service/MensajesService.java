package com.gonzao.red_social.service;

import com.gonzao.red_social.model.Mensajes;
import com.gonzao.red_social.dao.MensajeDAO;
import com.gonzao.red_social.model.User;

import java.util.Scanner;

public class MensajesService {

    public static void crearMensaje(User user, Scanner sc){
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();

        if (!mensaje.isEmpty()){
            Mensajes registro = new Mensajes(mensaje, user.getId_user());
            registro.setMensaje(mensaje);
            registro.setAutor_mensaje(user.getFullname());

            MensajeDAO.crearMensajeDB(registro);
        }else {
            System.out.println("No puede haber campos vacios");
        }
    }

    public static void listarMensaje(){
        for (Mensajes m : MensajeDAO.leerMensajesDB()){
            System.out.println("");
            System.out.println("ID: " + m.getId_mensaje());
            System.out.println("Mensaje: " + m.getMensaje());
            System.out.println("Autor: " + m.getAutor_mensaje());
            System.out.println("Fecha: " + m.getFecha_mensaje());
            System.out.println("");
        }
    }

    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el ID del mensaje a borrar");
        int id_mensaje = sc.nextInt();
        sc.nextLine();
        MensajeDAO.borrarMensajeDB(id_mensaje);
    }

    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe tu nuevo mensaje");
        String mensajeNuevo = sc.nextLine().trim();

        System.out.println("Indica el ID del mensaje a editar");
        int id_mensaje = sc.nextInt();
        sc.nextLine();

        if (!mensajeNuevo.isEmpty()) {
            Mensajes actualizado = new Mensajes();
            actualizado.setId_mensaje(id_mensaje);
            actualizado.setMensaje(mensajeNuevo);

            MensajeDAO.actualizarMensajeDB(actualizado);
        } else {
            System.out.println("El nuevo mensaje no puede estar vacío");
        }
    }
}
