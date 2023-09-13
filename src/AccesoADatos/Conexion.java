package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/universidad";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con;

    private Conexion() {
    }

    public static Connection getConexion() {
        if (con == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR EL DRIVER" + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS" + ex.getMessage());
            }
        }
        return con;
    }
}
