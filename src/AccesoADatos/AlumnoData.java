package AccesoADatos;


import Universidad.Entidades.Alumno;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlumnoData {

    private Connection con;

    public AlumnoData() {
        con = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String SQL = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES(?, ?, ?, ?, ? )";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
           int resultado=  ps.executeUpdate();
            if(resultado !=0){
                JOptionPane.showMessageDialog(null, "ALUMNO CARGADO EXITOSAMENTE");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CARGAR AL ALUMNO" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXION" + ex.getMessage());
            }
        }
    }

    public Alumno buscarAlumno(int id) {
        Alumno alumno = new Alumno();
        String SQL = "SELECT * FROM alumno WHERE idAlumno  = ? AND estado = 1 ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL ALUMNO" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXION" + ex.getMessage());
            }
        }
        return alumno;
    }

    public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = new Alumno();
        String SQL = "SELECT * FROM alumno WHERE dni  = ? AND estado = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni((dni));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL ALUMNO" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXION" + ex.getMessage());
            }
        }
        return alumno;
    }

    public List<Alumno> listarAlumno() {
        PreparedStatement ps = null;
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno = new Alumno();
        String SQL = "SELECT * FROM alumno WHERE estado = 1";

        try {
            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                alumnos.add(alumno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR ALUMNOS" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXION" + ex.getMessage());
            }
        }
        return alumnos;
    }

    public void modificarAlumno(Alumno alumno){
         PreparedStatement ps = null;
         
         String SQL  = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento= ?"
                 + " WHERE idAlumno= ? ";
        try {
            ps=con.prepareStatement(SQL);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacimiento()));
            ps.setInt(5, alumno.getIdAlumno());
           int resultado = ps.executeUpdate();
            if(resultado!= 0){
              JOptionPane.showMessageDialog(null," alumno eliminado exitosamente");
          }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," error al cambiar los datos del alumno"+ ex.getMessage());
        }finally{
             try {
                 ps.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "error al cerrar la conexion"+ex.getMessage());
             }
        }
         
    }
    public void eliminarAlumno(int id){
        PreparedStatement ps=null;
        String SQL = "UPDATE alumno set estado = 0 WHERE idAlumno= ? ";
        try{
          ps=con.prepareStatement(SQL);
          ps.setInt(1, id);
          int resultado = ps.executeUpdate();
          if(resultado!= 0){
              JOptionPane.showMessageDialog(null," alumno eliminado exitosamente");
          }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"error al actualizar alumno" +ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error al cerrar la conexion" +ex.getMessage());
            }
        }
    }
}
