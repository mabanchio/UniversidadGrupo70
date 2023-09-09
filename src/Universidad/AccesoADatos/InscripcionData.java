package Universidad.AccesoADatos;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;

public class InscripcionData {

    private Connection con;
    private MateriaData matData;
    private AlumnoData aluData;

    public InscripcionData() {
        con = Conexion.getConexion();
        matData = new MateriaData();
        aluData = new AlumnoData();
    }

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) "
                + "VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());

            //Ejecutar la query para insert, update o delete
            ps.executeUpdate();

            //Recuperar la clave primaria del insert
            ResultSet rs = ps.getGeneratedKeys();

            //Evaluar exito al insertar alumno
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt("idInscripcion"));
                JOptionPane.showMessageDialog(null, "Inscripción añadida con éxito.");
            }

            //Cerrar el statment
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * "
                    + "FROM inscripcion";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Alumno alumno = new Alumno();
            Materia materia = new Materia();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                inscripcion.setAlumno(alumno);
                //inscripcion.setAlumno(rs.getInt("idAlumno"));
                materia.setIdMateria(rs.getInt("idMateria"));
                inscripcion.setMateria(materia);
                //inscripcion.setMateria(rs.getInt("idMateria"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * "
                    + "FROM inscripcion i, alumno a "
                    + "WHERE i.idAlumno = a.idAlumno "
                    + "AND a.idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Alumno alumno = new Alumno();
            Materia materia = new Materia();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                inscripcion.setAlumno(alumno);
                //inscripcion.setAlumno(rs.getInt("idAlumno"));
                materia.setIdMateria(rs.getInt("idMateria"));
                inscripcion.setMateria(materia);
                //inscripcion.setMateria(rs.getInt("idMateria"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
        return inscripciones;
    }
    public List<Materia> materiasCursadas(int id) {
        List<Materia> materiasCursadas = new ArrayList<>();
        try {
            String sql = "SELECT m.* FROM materia m JOIN inscripcion i on m.idMateria=i.idMateria WHERE i.idAlumno = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materiasCursadas.add(materia);
            }
          ps.close();
        } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
        return materiasCursadas;
    }
  public List<Materia> materiasNoCursadas(int id){
      List <Materia> materiasNoCursadas= new ArrayList<>();
      try {
            String sql = "SELECT m.* FROM materia m JOIN inscripcion i on m.idMateria=i.idMateria WHERE i.idAlumno = ? GROUP BY m.Materia ";
            "SELECT m.* FROM materia m INNER JOIN inscripcion i ON m.idMateria = i.idMateria WHERE i.idAlumno = 1 GROUP BY m.nombre";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materiasNoCursadas.add(materia);
            }
          ps.close();
        } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
        return materiasNoCursadas;
  }
}
