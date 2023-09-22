package AccesoADatos;


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

public class MateriaData {

    private Connection con;

    public MateriaData() {
        con = Conexion.getConexion();
    }

    public void guardarMateria(Materia materia) {
        PreparedStatement ps = null;

        String SQL = "INSERT INTO materia (nombre, año, estado)VALUES(?,?,?) ";

        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "materia agregada exitosamente");
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "error de conexion" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Materia buscarMateria(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia materia = new Materia();
        String SQL = "SELECT * FROM materia WHERE idMateria = ?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));

            } else {
                JOptionPane.showMessageDialog(null, "no se encontro la materia");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar materia " + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar la concexion" + ex.getMessage());
            }
        }
        return materia;
    }
    public void modificarMateria(Materia materia) {
        PreparedStatement ps = null;
        String SQL = " UPDATE materia SET nombre=?, año=? WHERE idMateria=? ";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setInt(3, materia.getIdMateria());
            int registro=ps.executeUpdate();
            if(registro>0){
                JOptionPane.showMessageDialog(null, "materia modificada");
            }else{
               JOptionPane.showMessageDialog(null, "no se pudo modificar la materia");  
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al actualizar materia" + ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al cerrar sesion" + ex.getMessage());
            }
        }

    }
    public void eliminarMateria(int id){
       PreparedStatement ps=null;
       String SQL= " UPDATE materia SET estado = 0 WHERE idMateria = ? ";
       
        try {
            ps= con.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error al borrar materia" +ex.getMessage());
        }finally{
           try {
               ps.close();
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "error al cerrar sesion"+ex.getMessage());
           }
        }
       
    }
    public List<Materia>listarMateria(){
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Materia> materias= new ArrayList<>();
        Materia materia= new Materia();
        String SQL= " SELECT * FROM materia WHERE estado = true ";
        try {
            ps= con.prepareStatement(SQL);
            rs = ps.executeQuery();
        while(rs.next()){
            materia.setIdMateria(rs.getInt("idMateria"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAño(rs.getInt("año"));
            materia.setEstado(rs.getBoolean("estado"));
            materias.add(materia);
        }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al listar materias"+ex.getMessage());
        }finally {
            try {
                ps.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "error al cerrar sesion"+ex.getMessage());
            }
        }
     return materias;  
    }
}
