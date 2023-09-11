
package Universidad.AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/";
    private static final String DB = "universidad";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    //Metodo constructor
    private Conexion(){}
    
    public static Connection getConexion(){
        if(connection == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                //Levantar conexion con la BD
                connection = DriverManager.getConnection(URL + DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC" + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectar a la BD " + e.getMessage());
            } catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "Error al cargar Driver JDBC " + e.getMessage());
            }
        }
        return connection;
    }
}
