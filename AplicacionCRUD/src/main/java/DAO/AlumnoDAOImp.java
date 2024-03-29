package DAO;

import Entity.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AlumnoDAOImp implements AlumnoDAO {

    private ConexionBD conexion;

    @Override
    public void agregar(Alumno alumno) {
        try {            
            String sql = "INSERT INTO alumnos(nombre, apellido,nota) values(?, ?, ?)";
            
            ConexionBD con = new ConexionBD();
            Connection conexion = con.establecerConeccion();
            
            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setString(1, alumno.getNombre());
            consulta.setString(2, alumno.getApellido());
            consulta.setDouble(3, alumno.getNota());
            
            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se agregó alumno con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se agregó alumno", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
            consulta.close();            
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }        
    }

    @Override
    public void modificar(Alumno alumno) {
        try {
            String sql = "UPDATE alumnos SET  nombre = ?, apellido = ?, nota = ? WHERE id = ?";

            ConexionBD con = new ConexionBD();
            Connection conexion = con.establecerConeccion();

            int idSeleccionado = alumno.getId();

            PreparedStatement consulta = conexion.prepareStatement(sql);

            consulta.setString(1, alumno.getNombre());
            consulta.setString(2, alumno.getApellido());
            consulta.setDouble(3, alumno.getNota());

            consulta.setInt(4, idSeleccionado);
            
            int filasAfectadas = consulta.executeUpdate();

            if (filasAfectadas > 0) {                
                JOptionPane.showMessageDialog(null, "Se modificó alumno con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se modificó alumno", "Advertencia",  JOptionPane.WARNING_MESSAGE);
            }
            
            consulta.close();            
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void eliminar(int id) {
        try {                                                            
            String sql = "DELETE from alumnos WHERE id = " + id;
            ConexionBD con = new ConexionBD();

            Connection conexion = con.establecerConeccion();

            PreparedStatement consulta = conexion.prepareStatement(sql);            
            
            int filasAfectadas = consulta.executeUpdate(sql);

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se eliminó alumno con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se eliminó alumno", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            consulta.close();
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public DefaultTableModel obtenerTodos() {
        //Creamos un modelo para almacenar los registros dentro de la tabla
        DefaultTableModel model = new DefaultTableModel();
        
        try {            
            String sql = "SELECT * FROM alumnos";           
            ConexionBD con = new ConexionBD();
            
            Connection conexion = con.establecerConeccion();                                  
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery(sql);
            
            ResultSetMetaData metaData = rs.getMetaData();

            int numColumnas = metaData.getColumnCount();

            for (int column = 1; column <= numColumnas; column++) {
                model.addColumn(metaData.getColumnName(column));
            }

            //Agregamos las filas al modelo de la tabla
            while (rs.next()) {
                Object[] rowData = new Object[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }
                        
            st.close();
            rs.close();            
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
        return model;
    }

    @Override
    public int totalAlumnos() {
        int totalRegistros=0;
        try {            
            String sql = "SELECT COUNT(*) FROM alumnos";           
            ConexionBD con = new ConexionBD();           
            Connection conexion = con.establecerConeccion();
                                  
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                totalRegistros = rs.getInt(1);                
            }
                        
            st.close();
            rs.close();            
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
        return totalRegistros;
    }

    @Override
    public double promedioNotas() {                        
        double promedio = 0;
        try {            
            String sql = "SELECT AVG(nota) FROM alumnos";           
            ConexionBD con = new ConexionBD();           
            Connection conexion = con.establecerConeccion();
            
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                promedio = rs.getDouble(1);
            }
                        
            st.close();
            rs.close();            
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Se ha producido un error", JOptionPane.ERROR_MESSAGE);
        }
        return promedio;
    }

}
