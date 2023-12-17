package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBD {

    //Atributos de conexión
    private Connection conectar = null;

    private final String usuario = "root";

    private final String contrasena = "admin";

    private final String db = "ejercicio3";

    private final String ip = "localhost";

    private final String puerto = "3306";

    private final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;

    public Connection establecerConeccion() {
        try {

            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se generó la conexión" + e);
        }
        return conectar;
    }  
}
