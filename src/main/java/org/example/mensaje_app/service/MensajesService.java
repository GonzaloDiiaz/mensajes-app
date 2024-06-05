package org.example.mensaje_app.service;

import org.example.mensaje_app.dao.MensajeDAO;
import org.example.mensaje_app.model.Mensajes;

import java.util.Scanner;

public class MensajesService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();

        System.out.println("Ingrese su nombre");
        String nombre = sc.nextLine();

        Mensajes registro = new Mensajes();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);

        MensajeDAO.crearMensajeDB(registro);
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

    }

    public static void editarMensaje(){

    }
}
