package Universidad.AccesoADatos;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.*;

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
                insc.setIdInscripcion(rs.getInt("insert_id"));
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
            String sql = "SELECT i.*, a.idAlumno AS alumno_id, a.nombre AS alumno_nombre, m.idMateria AS materia_id, m.nombre AS materia_nombre "
                    + "FROM inscripcion i "
                    + "JOIN alumno a ON i.idAlumno = a.idAlumno "
                    + "JOIN materia m ON i.idMateria = m.idMateria";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));

                // Crear objeto Alumno
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("alumno_id"));
                alumno.setNombre(rs.getString("alumno_nombre"));
                inscripcion.setAlumno(alumno);

                // Crear objeto Materia
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("materia_id"));
                materia.setNombre(rs.getString("materia_nombre"));
                inscripcion.setMateria(materia);

                // Agregar inscripcion a la lista
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
                    + "FROM inscripcion i, alumno a, materia m "
                    + "WHERE i.idAlumno = a.idAlumno "
                    + "AND i.idMateria = m.idMateria "
                    + "AND a.idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt(1));
                inscripcion.setNota(rs.getDouble(2));

                // Crear objeto Alumno
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt(3));
                alumno.setDni(rs.getInt(6));
                alumno.setApellido(rs.getString(7));
                alumno.setNombre(rs.getString(8));
                alumno.setFechaNacimiento(rs.getDate(9).toLocalDate());
                alumno.setEstado(rs.getBoolean(10));
                inscripcion.setAlumno(alumno);

                // Crear objeto Materia
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt(11));
                materia.setNombre(rs.getString(12));
                materia.setAño(rs.getInt(13));
                materia.setEstado(rs.getBoolean(14));
                inscripcion.setMateria(materia);

                // Agregar inscripcion a la lista
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
            String sql = "SELECT m.* "
                    + "FROM materia m "
                    + "JOIN inscripcion i "
                    + "ON m.idMateria=i.idMateria "
                    + "WHERE i.idAlumno = ? ";
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

    public List<Materia> materiasNoCursadas(int id) {
        List<Materia> materiasNoCursadas = new ArrayList<>();
        try {
            String sql = "SELECT m.* "
                    + "FROM materia m "
                    + "LEFT JOIN (SELECT DISTINCT idMateria FROM inscripcion WHERE idAlumno = ?) inscrito "
                    + "ON m.idMateria = inscrito.idMateria "
                    + "WHERE inscrito.idMateria IS NULL "
                    + "AND m.estado = 1;";
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

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String sql = "DELETE "
                + "FROM inscripcion "
                + "WHERE idAlumno = ? "
                + "AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            //Crear variable de control de eliminación
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se eliminó la inscripción.");
            } else {
                JOptionPane.showMessageDialog(null, "La inscripción no se encontró.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, double nota) {
        String sql = "UPDATE inscripcion "
                + "SET nota = ? "
                + "WHERE idAlumno = ? "
                + "AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
            //Crear variable de control de eliminación
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Nota cargada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "La inscripción no se encontró.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
    }

    public List<Alumno> obtenerAlumnosXMateria(int idMateria) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT a.* "
                + "FROM alumno a "
                + "JOIN inscripcion i "
                + "ON a.idAlumno = i.idAlumno "
                + "WHERE i.idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion " + e.getMessage());
        }
        return alumnos;
    }
}
