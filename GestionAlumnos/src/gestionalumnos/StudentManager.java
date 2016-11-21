package gestionalumnos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class StudentManager {
    
    private ClassConnect clConnect;
    private Students classStudents;
    private ResultSet variableResultSet=null;
                                        
    public StudentManager(ClassConnect Connection) {
        try{
            clConnect=Connection;
            classStudents=new Students();
        }catch (Exception e){
        }
    }
    
    public Students showUser(String selectedRegistry) throws SQLException{
            Students StudentUser = new Students();
            String sql="SELECT * FROM alumnos WHERE registro="+selectedRegistry;
            clConnect.runQuery(sql);                                          
            variableResultSet=clConnect.getVariableResultSet();
            variableResultSet.next();
            StudentUser.setRegistry(variableResultSet.getString("registro"));
            StudentUser.setDni(variableResultSet.getString("dni"));
            StudentUser.setName(variableResultSet.getString("nombre"));
            StudentUser.setSurname1(variableResultSet.getString("apellido1"));
            StudentUser.setSurname2(variableResultSet.getString("apellido2"));
            return StudentUser;
    }
    
    public void modify(Students classStudents){
        try{
            String sql = "update alumnos set dni='" + classStudents.getDni() + "', nombre='" + classStudents.getName() + "', "
            + "apellido1='" + classStudents.getSurname1() + "', apellido2='" + classStudents.getSurname2() + "' "
            + "where registro=" + classStudents.getRegistry();
            if (clConnect.updateSQL(sql) > 0) {
                JOptionPane.showMessageDialog(null, "Modificación Correcta");
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha Habido un Error");
            }
        }catch(Exception e){
        }
    }
    
    public void deleteUser(Students classStudents) throws SQLException{
        String sql="delete from alumnos where registro=" + classStudents.getRegistry();
        if(clConnect.updateSQL(sql) > 0){
            JOptionPane.showMessageDialog(null, "Baja Correcta");
        }
        else{
            JOptionPane.showMessageDialog(null, "Ha Habido un Error");
        }
    }
    
    public void addUser(Students classStudents) throws SQLException{
        String sql="INSERT INTO alumnos(dni,nombre,apellido1,apellido2) " 
                 +"VALUES('"+classStudents.getDni()+"','"+classStudents.getName()+"','"
                 +classStudents.getSurname1()+"','"+classStudents.getSurname2()+"')";
        if(clConnect.updateSQL(sql) > 0){
            JOptionPane.showMessageDialog(null, "El usuario se ha dado de alta correctamente.");
        }
        else{
            JOptionPane.showMessageDialog(null, "Ha Habido un Error");
        }
    }
    
    public void queryAll(){
        try {
            clConnect.runQuery("select * from alumnos");
        } catch (SQLException ex) {
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}