package com.gonzao.red_social;

import com.gonzao.red_social.model.User;
import com.gonzao.red_social.service.MensajesService;
import com.gonzao.red_social.service.UserService;

import java.util.Scanner;

public class Menu {

    public static void sesionMenu(User user, Scanner sc){
        int opcion = 0;

        String sessionMenuMessage = ("=================== \n"
                + "\n red social, hola "+user.getFullname()+" \n"
                + "1. escribir mensaje \n"
                + "2. leer mensajes \n"
                + "3. eliminar mensaje \n"
                + "4. editar perfil \n"
                + "5. ver usuarios \n"
                + "6. cerrar sesión \n");

        do {
            System.out.println(sessionMenuMessage);
            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    MensajesService.crearMensaje(user, sc);
                    break;
                case 2:
                    MensajesService.listarMensaje();
                    break;
                case 3:
                    MensajesService.borrarMensaje();
                    break;
                case 4:
                    MensajesService.editarMensaje();
                    break;
                case 5:
                    UserService.readUser();
                default:
                    break;
            }
        }while (opcion != 5);
    }
}
