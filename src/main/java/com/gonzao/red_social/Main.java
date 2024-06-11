package com.gonzao.red_social;

import com.gonzao.red_social.dao.UserDAO;
import com.gonzao.red_social.model.User;
import com.gonzao.red_social.service.MensajesService;
import com.gonzao.red_social.service.UserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

public class Main {

    static String mainMenuMessage = ("=================== \n"
            + "\n Mini red social \n"
            + "1. Registrarse \n"
            + "2. Iniciar sesión \n"
            + "3. salir \n");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        mainMenu(sc);
        sc.close();
    }

    public static void mainMenu(Scanner sc){
        int option = 0;

        do {
            System.out.println(mainMenuMessage);

            option = sc.nextInt();

            switch (option){
                case 1:
                    UserService.createUser();
                    break;
                case 2:
                    User resultado = UserService.login(sc);
                    if (resultado != null && resultado.getId_user() > 0){
                        Menu.sesionMenu(resultado, sc);
                    } else {
                        System.out.println("Inicio de sesión fallido. Inténtalo de nuevo.");
                    }
                    break;
                default:
                    break;
            }
        }while (option != 3);
    }
}
