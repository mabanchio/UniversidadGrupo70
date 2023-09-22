package AccesoADatos;

import Universidad.Entidades.Alumno;
import Universidad.Entidades.Inscripcion;
import Universidad.Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InscripcionData {

    Connection con;
    MateriaData matData;
    AlumnoData aluData;

    public InscripcionData(Connection con, MateriaData matData, AlumnoData aluData) {
        con = Conexion.getConexion();
        this.matData = matData;
        this.aluData = aluData;
    }

    public void guardarInscripcion(Inscripcion insc) {
        String SQL = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(SQL);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            int resultado = ps.executeUpdate();
            if (resultado != 0) {
                JOptionPane.showMessageDialog(null, "inscripcion agregada exitosamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM alumno a, materia m , inscripcion i WHERE i.idMateria = m.idMateria AND i.idAlumno = a.idAlumno";

        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno alumno = new Alumno();
                Materia materia = new Materia();
                alumno.setIdAlumno(rs.getInt(1)); // idAlumno
                alumno.setDni(rs.getInt(2));
                alumno.setApellido(rs.getString(3));
                alumno.setNombre(rs.getString(4));
                alumno.setFechaNacimiento(rs.getDate(5).toLocalDate());
                alumno.setEstado(rs.getBoolean(6));
                materia.setIdMateria(rs.getInt(7));
                materia.setNombre(rs.getString(8));
                materia.setAño(rs.getInt(9));
                materia.setEstado(rs.getBoolean(10));
                inscripcion.setIdInscripcion(rs.getInt(11));
                inscripcion.setNota(rs.getDouble(12));
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripciones.add(inscripcion);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscrpcionesPorAlumno(int id) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM alumno a, materia m , inscripcion i WHERE i.idMateria = m.idMateria AND i.idAlumno = a.idAlumno AND a.idAlumno = ?";

        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno alumno = new Alumno();
                Materia materia = new Materia();
                alumno.setIdAlumno(rs.getInt(1)); // idAlumno
                alumno.setDni(rs.getInt(2));
                alumno.setApellido(rs.getString(3));
                alumno.setNombre(rs.getString(4));
                alumno.setFechaNacimiento(rs.getDate(5).toLocalDate());
                alumno.setEstado(rs.getBoolean(6));
                materia.setIdMateria(rs.getInt(7));
                materia.setNombre(rs.getString(8));
                materia.setAño(rs.getInt(9));
                materia.setEstado(rs.getBoolean(10));
                inscripcion.setIdInscripcion(rs.getInt(11));
                inscripcion.setNota(rs.getDouble(12));
                inscripcion.setAlumno(alumno);
                inscripcion.setMateria(materia);
                inscripciones.add(inscripcion);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
        return inscripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        List<Materia> materias = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM  materia m , inscripcion i WHERE i.idMateria = m.idMateria AND i.idAlumno = ?";

        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Materia materia = new Materia();
            while (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAño(rs.getInt(3));
                materia.setEstado(rs.getBoolean(4));
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
        return materias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int id) {

        List<Materia> materias = new ArrayList<>();
        //String SQL = "SELECT m.* FROM materia m LEFT JOIN (SELECT DISTINCT idMateria FROM inscripcion WHERE idAlumno = ?) inscrito ON m.idMateria = inscrito.idMateria WHERE inscrito.idMateria IS NULL AND m.estado = 1;";
        String SQL2 = " SELECT * FROM materia m WHERE m.idMateria NOT IN (SELECT i.idMateria FROM inscripcion i WHERE i.idAlumno = ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL2);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Materia materia = new Materia();
            while (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAño(rs.getInt(3));
                materia.setEstado(rs.getBoolean(4));
                materias.add(materia);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
        }
        return materias;
    }
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        String SQL = "DELETE FROM inscripciones WHERE idAlumno = ? AND idMateria = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int registro = ps.executeUpdate();
            if(registro !=0){
                JOptionPane.showMessageDialog(null, "se elimino la inscripcion");
            }else{
                JOptionPane.showMessageDialog(null, "no se elimino la inscripcion" );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar la tabla de inscripciones" + ex.getMessage());
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota){
        String SQL = "UPDATE inscripcion SET nota= ? WHERE idAlumno = ? AND idMateria = ? ";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL);
             ps.setDouble(1,nota);
             ps.setInt(2, idAlumno);
             ps.setInt(3, idMateria);
              int registro = ps.executeUpdate();
            if(registro !=0){
                JOptionPane.showMessageDialog(null, "se modifico la nota");
            }else{
                JOptionPane.showMessageDialog(null, "no se pudo modificar la nota" );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar la tabla de inscripciones" + ex.getMessage());
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
    }
    
    public List<Alumno> obtenerAlumnos(int idMateria){
        List<Alumno> alumnos = new ArrayList<>();
        String SQL = "SELECT alumno.* FROM alumno JOIN inscripcion ON alumno.idAlumno = inscripcion.idAlumno WHERE inscripcion.idMateria = ? ";
        PreparedStatement ps = null;
        ResultSet rs  =null;
        Alumno alumno = new Alumno();
        
        try {
            ps= con.prepareStatement(SQL);
            ps.setInt(1, idMateria);
            rs = ps.executeQuery();
            while(rs.next()){
                alumno.setIdAlumno(rs.getInt(1)); // idAlumno
                alumno.setDni(rs.getInt(2));
                alumno.setApellido(rs.getString(3));
                alumno.setNombre(rs.getString(4));
                alumno.setFechaNacimiento(rs.getDate(5).toLocalDate());
                alumno.setEstado(rs.getBoolean(6));
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo establecer la conexion a la base de datos");
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "error al cerrar la conexion " + ex.getMessage());
            }
        }
        return alumnos;
}
     
}
