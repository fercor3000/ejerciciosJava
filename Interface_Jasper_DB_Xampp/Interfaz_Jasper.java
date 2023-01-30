
package informesjasper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Interfaz_Jasper extends javax.swing.JFrame{


    Conexion cn;
    JTable tablaAlumnos;
    JPanel groupButtons;
    JButton btnInsert;
    JButton btnDelete;
    JButton btnUpdate;
    JButton btnCrearInforme;
    JButton btnCerrar;
    JDialog ventanaInsertar;
    JDialog ventanaActualizar;
    
    int width_Jframe = 1920/2;
    int height_Jframe = 1680/2;
    
    //Constructor de ventana main
    public Interfaz_Jasper(){
        this.setTitle("Alumnos DB Jasper");
        this.setResizable(false);
        this.setSize(width_Jframe/2, height_Jframe/2 + 40); //DIMENSIONES ANCHO Y ALTO VENTANA
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //CERRAR LA APLICACION COMPLETAMENTE
        this.setLocationRelativeTo(null); //CENTRAR VENTANA JFRAME
        this.setLayout(null); //Por defecto los JFrame incluyen el BorderLayout(), para poder ajustar y cambiar los tamaños de los componentes que tendrá el JFrame es necesario indicar null en el método setLayout() con eso el JFrame no estaría manejando ningún tipo de Layout.
        
        
        hacerConexion();
        iniciarComponentes();
    }
    
    public void hacerConexion(){
        
        //Conexion a la base de datos de XAMPP
        try{
            cn = new Conexion();
            cn.establecerConexion();
            if (cn == null) {
                JOptionPane.showMessageDialog(null, "No Conectado");
            } else {
                JOptionPane.showMessageDialog(null, "Conectado");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Componentes dentro del JFrame main
    public void iniciarComponentes(){
        groupButtons = new JPanel();
        groupButtons.setBackground(Color.gray);
        groupButtons.setBounds(50, 20, (width_Jframe/2) - 110, (height_Jframe/2) - 300);
        groupButtons.setLayout(null);
        
        btnInsert = new JButton();
        btnDelete = new JButton();
        btnUpdate = new JButton();
        btnCrearInforme = new JButton();
        btnCerrar = new JButton();
        
        btnInsert.setBounds(60, 20, 100, 35);
        btnDelete.setBounds(200, 20, 100, 35);
        btnUpdate.setBounds(60, 60, 100, 35);
        btnCrearInforme.setBounds(200, 60, 100, 35);
        btnCerrar.setBounds(180, 370, 100, 35);
        btnInsert.setText("Insertar");
        btnDelete.setText("Eliminar");
        btnUpdate.setText("Actualizar");
        btnCrearInforme.setText("Crear Informe");
        btnCerrar.setText("CERRAR");
        
        groupButtons.add(btnInsert);
        groupButtons.add(btnDelete);
        groupButtons.add(btnUpdate);
        groupButtons.add(btnCrearInforme);
        
        eventosBotones();
        
        this.add(groupButtons);
        this.add(btnCerrar);
        iniciarTablaAlumnos();
    }

    public void eventosBotones(){
        //VENTANA BOTON INSERTAR
        btnInsert.addActionListener(new ActionListener(){
            
            JPanel panelNewDatos;
            JButton btnSalir;
            JButton btnInsertAlumno;
            JLabel textoId;
            JLabel textoNombre;
            JLabel textoApellido1;
            JLabel textoApellido2;
            JLabel textoNota;
            public JTextField campoId;
            public JTextField campoNombre;
            public JTextField campoApellido1;
            public JTextField campoApellido2;
            public JTextField campoNota;
            
            public void actionPerformed(ActionEvent e){
                //JDialog
                ventanaInsertar = new JDialog(); //Ventana Secundaria
                ventanaInsertar.setLayout(null); //Evitar que se redimensione
                ventanaInsertar.setTitle("Alumnos DB Jasper");
                ventanaInsertar.setResizable(false);
                ventanaInsertar.setSize(width_Jframe/2, height_Jframe/2 + 40); //Tamaño del JDialog
                ventanaInsertar.setVisible(true); // Poner visible el JDIALOG
                ventanaInsertar.setLocationRelativeTo(null); // Centrar el JDIALOG
                
                //JPanel
                panelNewDatos = new JPanel();
                panelNewDatos.setBackground(Color.gray); // Fondo del panel
                panelNewDatos.setBounds(50, 50, (width_Jframe / 2) - 110, (height_Jframe / 2) - 130);
                panelNewDatos.setLayout(null);
                
                textoId = new JLabel("ID");
                textoNombre = new JLabel("Nombre");
                textoApellido1 = new JLabel("1ºApellido");
                textoApellido2 = new JLabel("2ºApellido");
                textoNota = new JLabel("Nota");
                
                textoId.setBounds(100, 30, 100, 35);
                textoNombre.setBounds(100, 70, 100, 35);
                textoApellido1.setBounds(100, 110, 100, 35);
                textoApellido2.setBounds(100, 150, 100, 35);
                textoNota.setBounds(100, 190, 100, 35);
                
                //JTextField insertar datos
                campoId = new JTextField();
                campoNombre = new JTextField();
                campoApellido1 = new JTextField();
                campoApellido2 = new JTextField();
                campoNota = new JTextField();
                
                campoId.setBounds(180, 30, 100, 35);
                campoNombre.setBounds(180, 70, 100, 35);
                campoApellido1.setBounds(180, 110, 100, 35);
                campoApellido2.setBounds(180, 150, 100, 35);
                campoNota.setBounds(180, 190, 100, 35);
                
                //Boton insertar
                btnInsertAlumno = new JButton();
                btnInsertAlumno.setBounds(115, 240, 135, 35);
                btnInsertAlumno.setText("Insertar Alumno");
                
                //Boton salir
                btnSalir = new JButton();
                btnSalir.setBounds(190, 350, 85, 35);
                btnSalir.setText("Salir");
                
                ventanaInsertar.add(panelNewDatos);
                //Añadimos los Labels
                panelNewDatos.add(textoId);
                panelNewDatos.add(textoNombre);
                panelNewDatos.add(textoApellido1);
                panelNewDatos.add(textoApellido2);
                panelNewDatos.add(textoNota);
                //Añadimos los JTextField
                panelNewDatos.add(campoId);
                panelNewDatos.add(campoNombre);
                panelNewDatos.add(campoApellido1);
                panelNewDatos.add(campoApellido2);
                panelNewDatos.add(campoNota);
                //Añadimos el boton insertar alumno
                panelNewDatos.add(btnInsertAlumno);
                //Añadimos el boton de salir
                ventanaInsertar.add(btnSalir);
                
                btnInsertAlumno.addActionListener(new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent f){
                        llamarMetodoInsertar(campoId.getText(), campoNombre.getText(), campoApellido1.getText(), campoApellido2.getText(), campoNota.getText());
                    }
                });
                
                btnSalir.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent f){
                        ventanaInsertar.dispose();
                    }
                });
            }
        }); 
        
        //VENTANA BOTON ACTUALIZAR
        btnUpdate.addActionListener(new ActionListener(){
            
            JPanel panelNewDatos;
            JButton btnSalir;
            JButton btnUpdateAlumno;
            JComboBox elegirCampoAct;
            public JTextField newDato;
            
            public void actionPerformed(ActionEvent e){
                if(tablaAlumnos.getSelectedRow() != -1){
                    //JDialog
                    ventanaActualizar = new JDialog(); //Ventana Secundaria
                    ventanaActualizar.setLayout(null); //Evitar que se redimensione
                    ventanaActualizar.setTitle("Alumnos DB Jasper");
                    ventanaActualizar.setSize(width_Jframe / 2, height_Jframe / 2 + 40); //Tamaño del JDialog
                    ventanaActualizar.setVisible(true); // Poner visible el JDIALOG
                    ventanaActualizar.setLocationRelativeTo(null); // Centrar el JDIALOG

                    //JPanel
                    panelNewDatos = new JPanel();
                    panelNewDatos.setBackground(Color.gray); // Fondo del panel
                    panelNewDatos.setBounds(50, 50, (width_Jframe / 2) - 110, (height_Jframe / 2) - 130);
                    panelNewDatos.setLayout(null);

                    //JComboBox
                    elegirCampoAct = new JComboBox();
                    elegirCampoAct.setBounds(115, 55, 135, 35);
                    elegirCampoAct.addItem("Nombre");
                    elegirCampoAct.addItem("Apellido1");
                    elegirCampoAct.addItem("Apellido2");
                    elegirCampoAct.addItem("Nota");

                    //JTextField nuevo dato
                    newDato = new JTextField();
                    newDato.setBounds(120, 180, 120, 35);

                    //Boton actualizar
                    btnUpdateAlumno = new JButton();
                    btnUpdateAlumno.setBounds(110, 240, 145, 35);
                    btnUpdateAlumno.setText("Actualizar Alumno");

                    //Boton salir
                    btnSalir = new JButton();
                    btnSalir.setBounds(190, 350, 85, 35);
                    btnSalir.setText("Salir");

                    ventanaActualizar.add(panelNewDatos);
                    //Añadimos el JComboBox
                    panelNewDatos.add(elegirCampoAct);
                    //Añadimos el JTextField
                    panelNewDatos.add(newDato);
                    //Añadimos el boton insertar alumno
                    panelNewDatos.add(btnUpdateAlumno);
                    //Añadimos el boton de salir
                    ventanaActualizar.add(btnSalir);

                    btnUpdateAlumno.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent f) {
                            // elegirCampoAct.getSelectedItem().toString() obtengo el nombre de la opcion seleccionada
                            llamarMetodoActualizar(newDato.getText(), elegirCampoAct.getSelectedItem().toString(), (tablaAlumnos.getSelectedRow() + 1) + "");
                        }
                    });

                    btnSalir.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent f) {
                            ventanaActualizar.dispose();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA FILA");
                }
            }
        });
        
        //BOTON ELIMINAR
        btnDelete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(tablaAlumnos.getSelectedRow() != -1){
                   llamarMetodoEliminar((tablaAlumnos.getSelectedRow() + 1) + "");
               }else{
                   JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA FILA");
               }
            }
        });
        
        //BOTON INFORME
        btnCrearInforme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cn.crearInforme();
            }
        });
        
        btnCerrar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
    
    public void llamarMetodoInsertar(String campoId,String campoNombre,String campoApellido1,String campoApellido2,String campoNota) {
        cn.insertBdAlumnos(campoId, campoNombre, campoApellido1, campoApellido2, campoNota);
        
        TableModel model = tablaAlumnos.getModel();
        String[][] array = new String[model.getRowCount()][5];
        for (int i = 0; i < model.getRowCount(); i++) {
            array[i] = (new String[]{model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString(),model.getValueAt(i, 3).toString(),model.getValueAt(i, 4).toString()});
        }
        
        DefaultTableModel modeloTabla = new DefaultTableModel(array, new String[]{"ID","Nombre","Apellido1","Apellido2","Nota"});
        
        String[] nuevaFila = new String[5];
        nuevaFila[0] = campoId;
        nuevaFila[1] = campoNombre;
        nuevaFila[2] = campoApellido1;
        nuevaFila[3] = campoApellido2;
        nuevaFila[4] = campoNota;
       
        modeloTabla.addRow(nuevaFila);
        tablaAlumnos.setModel(modeloTabla);
    }
    
    public void llamarMetodoActualizar(String nuevoDato, String campoSeleccionado, String idActual) {
        cn.updateBdAlumnos(nuevoDato, campoSeleccionado, idActual);
        iniciarTablaAlumnos();
        
        TableModel model = tablaAlumnos.getModel();
        String[][] array = new String[model.getRowCount()][5];
        for (int i = 0; i < model.getRowCount(); i++) {
            array[i] = (new String[]{model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString(),model.getValueAt(i, 3).toString(),model.getValueAt(i, 4).toString()});
        }
        
        DefaultTableModel modeloTabla = new DefaultTableModel(array, new String[]{"ID","Nombre","Apellido1","Apellido2","Nota"});
       
        tablaAlumnos.setModel(modeloTabla);
    }
    
    public void llamarMetodoEliminar(String id) {
        cn.deleteBdAlumnos(id);
        iniciarTablaAlumnos();
        
        TableModel model = tablaAlumnos.getModel();
        String[][] array = new String[model.getRowCount()][5];
        for (int i = 0; i < model.getRowCount(); i++) {
            array[i] = (new String[]{model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString(),model.getValueAt(i, 3).toString(),model.getValueAt(i, 4).toString()});
        }
        
        DefaultTableModel modeloTabla = new DefaultTableModel(array, new String[]{"ID","Nombre","Apellido1","Apellido2","Nota"});
       
        tablaAlumnos.setModel(modeloTabla);
    }
    
    public void iniciarTablaAlumnos(){
        
        tablaAlumnos = null;
        
        String [] nombresColumnas = {"ID","Nombre","Apellido1","Apellido2","Nota"};
        Object[][] datosFila = null;
        
        try{
            
            datosFila = new Object[cn.selectBdAlumnos().size()][cn.selectBdAlumnos().get(0).size()];
            
            for(int i = 0; i < cn.selectBdAlumnos().size(); i++){
                for(int j = 0; j < cn.selectBdAlumnos().get(i).size(); j++){
                    datosFila[i][j] = cn.selectBdAlumnos().get(i).get(j);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        
        tablaAlumnos = new JTable(datosFila, nombresColumnas){
            //Quitar que la JTABLE sea editable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAlumnos.setBounds(50, 160, (width_Jframe/2) - 110, 200);
        
        this.add(tablaAlumnos);
    }
    
    
    //MAIN de esta clase main
    public static void main(String[] args) {
        new Interfaz_Jasper().setVisible(true);
    }
}
