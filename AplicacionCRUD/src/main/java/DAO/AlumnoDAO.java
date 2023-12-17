package DAO;

import Entity.Alumno;
import javax.swing.table.DefaultTableModel;

public interface AlumnoDAO {
    public void agregar(Alumno alumno);
    
    public void modificar(Alumno alumno);
    
    public void eliminar(int id);
    
    public DefaultTableModel obtenerTodos();
    
    public int totalAlumnos();
    
    public double promedioNotas();
}
