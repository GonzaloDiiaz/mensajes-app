package com.gonzao.red_social.model;

public class User {
    private int id_user;
    private String email;
    private String password;
    private String fullname;

    public User(){

    }

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    //constructor para crear, editar usuario
    public User(int userId, String email, String password, String fullName) {
        this.id_user = userId;
        this.email = email;
        this.password = password;
        this.fullname = fullName;
    }

    //constructor para iniciar sesión
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
