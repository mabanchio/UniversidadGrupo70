package AccesoADatos;

import Entidades.Alumno;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoData {

    private Connection con;

    public AlumnoData() {
        con = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String SQL = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES(?, ?, ?, ?, ? )";

        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
}
