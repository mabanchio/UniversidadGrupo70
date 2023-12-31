/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidad.Vistas;

import Universidad.AccesoADatos.AlumnoData;
import Universidad.AccesoADatos.InscripcionData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matias
 */
public class Inscripcion extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo = new DefaultTableModel() {
        //No permitir edicion de columnas
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    public Inscripcion() {
        initComponents();
        cargarCboAlumnos();
        armarCabecera();
        bgMaterias.add(jrbInscriptas);
        bgMaterias.add(jrbNoInscriptas);
        jbInscribir.setEnabled(false);
        jbAnularInscripcion.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMaterias = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbAlumnos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jrbInscriptas = new javax.swing.JRadioButton();
        jrbNoInscriptas = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMaterias = new javax.swing.JTable();
        jbInscribir = new javax.swing.JButton();
        jbAnularInscripcion = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("Inscripciones");
        setMinimumSize(new java.awt.Dimension(400, 500));
        setName("Inscripciones"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Formulario de Inscripción");

        jLabel2.setText("Seleccione un alumno:");

        jcbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlumnosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Listado de Materias");

        jrbInscriptas.setText("Materias Inscriptas");
        jrbInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbInscriptasActionPerformed(evt);
            }
        });

        jrbNoInscriptas.setText("Materias NO Inscriptas");
        jrbNoInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNoInscriptasActionPerformed(evt);
            }
        });

        jtMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Año"
            }
        ));
        jScrollPane1.setViewportView(jtMaterias);

        jbInscribir.setText("Inscribir");
        jbInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInscribirActionPerformed(evt);
            }
        });

        jbAnularInscripcion.setText("Anular Inscripción");
        jbAnularInscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularInscripcionActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSalir)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jbInscribir)
                        .addGap(18, 18, 18)
                        .addComponent(jbAnularInscripcion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jrbInscriptas)
                                .addGap(27, 27, 27)
                                .addComponent(jrbNoInscriptas)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbInscriptas)
                    .addComponent(jrbNoInscriptas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbInscribir)
                    .addComponent(jbAnularInscripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSalir)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jrbInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbInscriptasActionPerformed
        jbInscribir.setEnabled(false);
        jbAnularInscripcion.setEnabled(true);
        try {
            InscripcionData inscripcionConsulta = new InscripcionData();
            List<Entidades.Inscripcion> inscripciones = new ArrayList<>();
            String alumno = (String) jcbAlumnos.getSelectedItem();
            String[] parts = alumno.split(",");
            inscripciones = inscripcionConsulta.obtenerInscripcionesPorAlumno((Integer.parseInt(parts[0].trim())));
            borrarFilas();
            for (Entidades.Inscripcion registros : inscripciones) {
                modelo.addRow(new Object[]{
                    registros.getMateria().getIdMateria(),
                    registros.getMateria().getNombre(),
                    registros.getMateria().getAño()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error! " + e.getMessage());
        }

    }//GEN-LAST:event_jrbInscriptasActionPerformed

    private void jrbNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNoInscriptasActionPerformed
        jbInscribir.setEnabled(true);
        jbAnularInscripcion.setEnabled(false);
        try {
            InscripcionData inscripcionConsulta = new InscripcionData();
            List<Entidades.Materia> noInscriptas = new ArrayList<>();
            String alumno = (String) jcbAlumnos.getSelectedItem();
            String[] parts = alumno.split(",");
            noInscriptas = inscripcionConsulta.materiasNoCursadas(Integer.parseInt(parts[0].trim()));
            borrarFilas();
            for (Entidades.Materia registros : noInscriptas) {
                modelo.addRow(new Object[]{
                    registros.getIdMateria(),
                    registros.getNombre(),
                    registros.getAño()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error! " + e.getMessage());
        }

    }//GEN-LAST:event_jrbNoInscriptasActionPerformed

    private void jbInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInscribirActionPerformed
        try {
            InscripcionData inscribir = new InscripcionData();
            Entidades.Inscripcion inscripcion = new Entidades.Inscripcion();
            Entidades.Alumno alumno = new Entidades.Alumno();
            Entidades.Materia materia = new Entidades.Materia();
            String cboAlumno = (String) jcbAlumnos.getSelectedItem();
            String[] parts = cboAlumno.split(",");

            //Extraer datos de la tabla para crear los objetos Alumno y Materia
            materia.setIdMateria((int) (modelo.getValueAt(jtMaterias.getSelectedRow(), 0)));
            materia.setNombre((String) modelo.getValueAt(jtMaterias.getSelectedRow(), 1));
            materia.setAño((int) (modelo.getValueAt(jtMaterias.getSelectedRow(), 2)));
            materia.setEstado(true);

            alumno.setIdAlumno(Integer.parseInt(parts[0].trim()));
            alumno.setDni(Integer.parseInt(parts[1].trim()));
            alumno.setApellido(parts[2].trim());
            alumno.setNombre(parts[3].trim());
            alumno.setFechaNacimiento(Date.valueOf(parts[4].trim()).toLocalDate());
            alumno.setEstado(true);

            //Creacion de la inscripcion
            inscripcion.setNota(0.0);
            inscripcion.setAlumno(alumno);
            inscripcion.setMateria(materia);

            //Efectivizar la inscripción
            inscribir.guardarInscripcion(inscripcion);
            
            //Actualizar la vista
            jrbNoInscriptas.doClick();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al realizar inscripción! " + e.getMessage());
        }
    }//GEN-LAST:event_jbInscribirActionPerformed

    private void jbAnularInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularInscripcionActionPerformed
        try {
            InscripcionData borrarInscripcion = new InscripcionData();
            String cboAlumno = (String) jcbAlumnos.getSelectedItem();
            String[] parts = cboAlumno.split(",");
            int idAlumno = Integer.parseInt(parts[0].trim());
            int idMateria = (int) (modelo.getValueAt(jtMaterias.getSelectedRow(), 0));
            borrarInscripcion.borrarInscripcionMateriaAlumno(idAlumno, idMateria);
            jrbInscriptas.doClick();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar inscripción! " + e.getMessage());
        }
    }//GEN-LAST:event_jbAnularInscripcionActionPerformed

    private void jcbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlumnosActionPerformed
        if(jrbInscriptas.isSelected()){
            jrbInscriptas.doClick();
        } else if(jrbNoInscriptas.isSelected()){
            jrbNoInscriptas.doClick();
        }
    }//GEN-LAST:event_jcbAlumnosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgMaterias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbAnularInscripcion;
    private javax.swing.JButton jbInscribir;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<String> jcbAlumnos;
    private javax.swing.JRadioButton jrbInscriptas;
    private javax.swing.JRadioButton jrbNoInscriptas;
    private javax.swing.JTable jtMaterias;
    // End of variables declaration//GEN-END:variables
public void cargarCboAlumnos() {
        AlumnoData consultaAlumnos = new AlumnoData();
        List<Entidades.Alumno> alumnos = new ArrayList<>();
        alumnos = consultaAlumnos.listarAlumnos();
        jcbAlumnos.removeAllItems();
        for (Entidades.Alumno registros : alumnos) {
            jcbAlumnos.addItem(registros.toString());
        }
    }

    public void armarCabecera() {
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Año");
        jtMaterias.setModel(modelo);
    }

    private void borrarFilas() {
        int f = jtMaterias.getRowCount() - 1;
        for (; f >= 0; f--) {
            modelo.removeRow(f);
        }
    }
}
