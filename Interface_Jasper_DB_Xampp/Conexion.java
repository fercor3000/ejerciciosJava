
package informesjasper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class Conexion {
    Connection connect = null;
    String user = "root";//Usuario BBDD
    String password = "root";//Contraseña BBDD
    String bd = "bdalumnos";//Nombre BBDD
    String ip = "localhost";//Ip BBDD
    String puerto = "3316";//Puerto BBDD
    
    String url ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public Connection establecerConexion(){
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");//Ruta del DRIVER de MySQL
                connect = DriverManager.getConnection(url, this.user, this.password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e);
        }
        
        return connect;
    }
    
    public  ArrayList<ArrayList<String>> selectBdAlumnos() throws SQLException{       
        ArrayList<ArrayList<String>> datosAlumnos = new ArrayList<>();
        try {
            ps = this.connect.prepareStatement("SELECT alumnos.id, alumnos.nombre, alumnos.apellido1, alumnos.apellido2, alumnos.nota FROM bdalumnos.alumnos;");
            rs = ps.executeQuery();

            int i = 0;
            while(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido1 = rs.getString(3);
                String apellido2 = rs.getString(4);
                int nota = rs.getInt(5);

                datosAlumnos.add(new ArrayList<String>());
                datosAlumnos.get(i).add(id+"");
                datosAlumnos.get(i).add(nombre);
                datosAlumnos.get(i).add(apellido1);
                datosAlumnos.get(i).add(apellido2);
                datosAlumnos.get(i).add(nota+"");

                i++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return datosAlumnos;
    }
    
    
    public void insertBdAlumnos(String id, String name, String ape1, String ape2, String score){
        try {
            Statement miStat = connect.createStatement();
            int cont = miStat.executeUpdate("INSERT INTO bdalumnos.alumnos (nombre, apellido1, apellido2, nota) "
                    + "VALUES(" + "'" + name + "', '" + ape1 + "', '" + ape2 + "', '" + score + "'" + ")");
            
            JOptionPane.showMessageDialog(null, "Nuevo Alumnos Metido");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR: DATOS MAL INTRODUCIDOS");
        }
    }
    
    public void updateBdAlumnos(String nuevoDato, String campoSeleccionado, String idActual){
        String specialChars = "/~='¿¡%·ºª¨´`;:!@#$%^&()\"{}_[]|\\?/<>,.+-abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        
        try {
            if(campoSeleccionado.equalsIgnoreCase("nota")){
                boolean verCharacters = false;
                String[] arrayNuevoDato = nuevoDato.split("");
                String[] spclChars = specialChars.split("");
                int count = 0;
                for(int i = 0; i < spclChars.length; i++){
                    if(arrayNuevoDato[count].equals(spclChars[i])){
                        verCharacters = true;
                        i = spclChars.length;
                    }
                    if(count != arrayNuevoDato.length - 1){
                        count++;
                    } else {
                        count = 0;
                    }
                }
                if (verCharacters) { //Evitar que meta algo que no sea solo numeros
                    JOptionPane.showMessageDialog(null, "SOLO NUMEROS EN CAMPO NOTAS");
                } else {
                    Statement miStat = connect.createStatement();
                    miStat.executeUpdate("UPDATE bdalumnos.alumnos SET bdalumnos.alumnos." + campoSeleccionado + " = " + "'" + nuevoDato + "'" + " WHERE bdalumnos.alumnos.id = " + idActual + ";");

                    JOptionPane.showMessageDialog(null, "Alumno Actualizado");
                }
            } else {
                Statement miStat = connect.createStatement();
                miStat.executeUpdate("UPDATE bdalumnos.alumnos SET bdalumnos.alumnos." + campoSeleccionado + " = " + "'" + nuevoDato + "'" + " WHERE bdalumnos.alumnos.id = " + idActual + ";");

                JOptionPane.showMessageDialog(null, "Alumno Actualizado");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR: DATO NO ACTUALIZADO");
        }
    }
    
    public void deleteBdAlumnos(String id){
        try {
            Statement miStat = connect.createStatement();
            miStat.executeUpdate("DELETE FROM bdalumnos.alumnos WHERE bdalumnos.alumnos.id = " + id + ";");
            
            JOptionPane.showMessageDialog(null, "Alumno Eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR: DATOS MAL INTRODUCIDOS");
        }
    }
    
    public void crearInforme(){
        try {
            
            JasperReport jr = JasperCompileManager.compileReport("./src/reports/reportBdAlumnos.jrxml");
            JasperPrint jprint = JasperFillManager.fillReport(jr, null, connect);
            JasperExportManager.exportReportToPdfFile(jprint, "./src/reports/reportBdAlumnos.pdf");
            File path = new File ("./src/reports/reportBdAlumnos.pdf");
            Desktop.getDesktop().open(path); //ABRIR EL PDF
        } catch (JRException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
