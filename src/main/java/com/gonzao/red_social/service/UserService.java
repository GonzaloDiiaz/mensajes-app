package com.gonzao.red_social.service;

import com.gonzao.red_social.dao.UserDAO;
import com.gonzao.red_social.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class UserService {

    static String emailMessage = "indica tu email";
    static String passwordMessage = "indica tu password";
    static String fullNameMessage = "indica tu nombre completo";

    public static void createUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println(fullNameMessage);
        String fullname = sc.nextLine().trim();

        System.out.println(passwordMessage);
        String password = sc.nextLine().trim();

        System.out.println(emailMessage);
        String email = sc.nextLine().trim();

        if (!fullname.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            User registro = new User();
            registro.setFullname(fullname);
            registro.setPassword(password);
            registro.setEmail(email);

            UserDAO.createUser(registro);
        }else {
            System.out.println("No puede haber campos vacios");
        }
    }

    public static void readUser(){
        for (User u : UserDAO.readUser()){
            System.out.println("");
            System.out.println("ID: " + u.getId_user());
            System.out.println("Fullname: " + u.getFullname());
            System.out.println("Email: " + u.getEmail());
            System.out.println("");
        }
    }

    public static void updateUser(User usuario, Scanner sc) {
        System.out.println(fullNameMessage);
        String fullName = sc.nextLine().trim();

        System.out.println(emailMessage);
        String email = sc.nextLine().trim();

        System.out.println(passwordMessage);
        String password = sc.nextLine().trim();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            System.out.println("Todos los campos son obligatorios.");
            return;
        }

        if (!isValidEmail(email)) {
            System.out.println("El correo electrónico no es válido.");
            return;
        }

        if (!isValidPassword(password)) {
            System.out.println("La contraseña no cumple con los requisitos de seguridad.");
            return;
        }

        String passwordHashed = getMd5Hash(password);
        User updatedUser = new User(usuario.getId_user(),email,passwordHashed,fullName);
        UserDAO.updateUser(updatedUser);
    }

    public static void deleteUser(Scanner sc){
        System.out.println("Indica el ID a borrar");
        int id_Mensaje = sc.nextInt();
        sc.nextLine();
        UserDAO.deleteUser(id_Mensaje);
    }

    public static User login(Scanner sc){
        System.out.println(emailMessage);
        String email = sc.next();
        System.out.println(passwordMessage);
        String password = sc.next();

        // Obtener el hash de la contraseña ingresada por el usuario
        String passwordHashed = getMd5Hash(password);

        // Imprimir el hash de la contraseña ingresada por el usuario
        System.out.println("Hash de la contraseña ingresada: " + passwordHashed);

        // Crear el objeto User con el correo electrónico y el hash de la contraseña
        User login = new User(email, passwordHashed);

        // Llamar al método loginDB para autenticar al usuario
        User result = UserDAO.loginDB(login);

        if (result != null && result.getId_user() > 0) {
            System.out.println("Inicio de sesión exitoso para el usuario con ID: " + result.getId_user());
            return result;
        } else {
            System.out.println("Inicio de sesión fallido. No se encontró ningún usuario con las credenciales proporcionadas.");
            return null;
        }
    }


    public static String getMd5Hash(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        // Ejemplo de validación de contraseña (mínimo 8 caracteres, al menos una letra y un número)
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(passwordRegex);
    }

}
