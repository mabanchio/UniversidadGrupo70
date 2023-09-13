
package AccesoADatos;

import Entidades.Materia;
import java.sql.Connection;


public class MateriaData {
    private Connection con;

    public MateriaData() {
        con = Conexion.getConexion();
    }
    public void guardarMateria(Materia materia){
        String SQL = "INSERT INTO materia (nombre, año, estado)VALUES(?,?,?) ";
    }
    
}
