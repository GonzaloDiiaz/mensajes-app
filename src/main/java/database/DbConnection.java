package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection instance;

    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/mensajes_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private DbConnection(){
        try {
            //Creamos la conexion a la base
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Método público estático para obtener la instancia única de la clase
    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    // Método para obtener la conexión a la base de datos
    public Connection getConnection() {
        return connection;
    }
}
