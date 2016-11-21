package gestionalumnos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Events implements ActionListener, MouseListener {

    private FormMain form;
    private StudentManager ClassStudentManager;
    private ClassConnect Connection;
    
    public Events(){
        Connection = new ClassConnect();
        ClassStudentManager = new StudentManager(Connection);
        ClassStudentManager.queryAll();
        form = new FormMain(Connection.getVariableResultSet(),this);
        form.setVisible(true);                                                                                               
    }                                                                   

    @Override
    public void actionPerformed(ActionEvent e) { 
        if(e.getSource() == form.getButtonDeleteUser()){
            try{
                Students classStudents=new Students();
                classStudents.setRegistry(form.getTxtRegistry().getText());
                ClassStudentManager.deleteUser(classStudents);
                update();
            } catch (SQLException ex) {
                Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == form.getButtonExit()){
            System.exit(EXIT_ON_CLOSE);
        }
        
        if(e.getSource() == form.getButtonModify()){   
            Students classStudents=new Students();  
            classStudents.setDni(form.getTxtDNI().getText());  
            classStudents.setRegistry(form.getTxtRegistry().getText());
            classStudents.setName(form.getTxtName().getText());
            classStudents.setSurname1(form.getTxtSurname1().getText());
            classStudents.setSurname2(form.getTxtSurname2().getText());
            ClassStudentManager.modify(classStudents);
            update();
        }
        
        if(e.getSource() == form.getButtonNewUser()){ 
            try{                                    
                Students classStudents=new Students();
                classStudents.setDni(form.getTxtDNI().getText());
                classStudents.setRegistry(form.getTxtRegistry().getText());
                classStudents.setName(form.getTxtName().getText());
                classStudents.setSurname1(form.getTxtSurname1().getText());
                classStudents.setSurname2(form.getTxtSurname2().getText());
                ClassStudentManager.addUser(classStudents);
                update();
            } catch (SQLException ex) {
                Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    } 
        
    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            Students selectedStudentUser = new Students();
            
            String selectedRegistry="";
            int selectedRow=0;
            
            selectedRow=form.getStudentTable().getSelectedRow();
            selectedRegistry=String.valueOf(form.getStudentTable().getValueAt(selectedRow, 0));
            
            selectedStudentUser=ClassStudentManager.showUser(selectedRegistry);
            form.getTxtDNI().setText(selectedStudentUser.getDni());
            form.getTxtRegistry().setText(selectedStudentUser.getRegistry());
            form.getTxtName().setText(selectedStudentUser.getName());
            form.getTxtSurname1().setText(selectedStudentUser.getSurname1());
            form.getTxtSurname2().setText(selectedStudentUser.getSurname2());
        } catch (SQLException ex) {
            Logger.getLogger(Events.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private void update() {
        TableView tableview;
        
        ClassStudentManager.queryAll();
        tableview = new TableView(Connection.getVariableResultSet());
        form.getStudentTable().setModel(tableview);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Metodos Abstractos de MouseListener">
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    //</editor-fold>
}