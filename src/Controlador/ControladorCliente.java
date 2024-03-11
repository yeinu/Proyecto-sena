package Controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.Cliente;
import Modelo.ClienteDAO;
import Vista.Interfaz;

public class ControladorCliente implements ActionListener {
    
    //variables de instancia
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    Interfaz vista = new Interfaz();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    //Variables globales
    private int Codigo;
    private String Nombre;
    private String Direccion;
    private int Telefono;
    
    public ControladorCliente(Interfaz vista) {
        this.vista = vista;
        
        //vista.setExtendedState(JFame.NORMAL);
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
        
    }
    
private void agregarEventos(){
    //Acciones cuando el boton sea oprimido
   vista.getBtnAgregar().addActionListener(this);
   vista.getBtnActualizar().addActionListener(this);
   vista.getBtnBorrar().addActionListener (this);
   vista.getBtnLimpiar().addActionListener(this);
   vista.gettblCliente().addMouseListener(new MouseAdapter(){
       //Creacion del metodo
       public void mouseClicked(MouseEvent e){
           llenarCampos(e);
       }
   });
   
}
 
private void listarTabla() {
    String[] titulos = new String[]{"Codigo","Nombre","Direccion","Telefono"};
    modeloTabla = new DefaultTableModel(titulos,0);
    List<Cliente> listaClientes = clienteDAO.listar();
    for (Cliente cliente : listaClientes){
        modeloTabla.addRow(new Object[] {
        cliente.getCodigo(),
        cliente.getNombre(),
        cliente.getDireccion(),
        cliente.getTelefono()
     });
}
    vista.gettblCliente().setModel(modeloTabla);
    vista.gettblCliente().setPreferredSize(new Dimension(350,modeloTabla.getRowCount()*16));
}    

    private void llenarCampos(MouseEvent e){
        JTable target = (JTable) e.getSource();
        vista.getTxtNombre()
                 .setText(vista.gettblCliente().getModel()
                         .getValueAt(target.getSelectedRow(), 1)
                         .toString());//Nombre
        vista.getTxtDireccion()
                 .setText(vista.gettblCliente().getModel()
                         .getValueAt(target.getSelectedRow(), 2)
                         .toString());//Direccion
        vista.getTxtTelefono()
                 .setText(vista.gettblCliente().getModel()
                         .getValueAt(target.getSelectedRow(), 3)
                         .toString());//Telefono
    }

//Validar Formulario
    private boolean validarDatos(){
        if ("".equals(vista.getTxtNombre().getText()) 
                ||"".equals(vista.getTxtDireccion().getText())
                ||"".equals(vista.getTxtTelefono().getText())){
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", 
                    "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cargarDatos() {
        try {
            Nombre = vista.getTxtNombre().getText();
            Direccion = vista.getTxtDireccion().getText();
            Telefono = Integer.parseInt(vista.getTxtTelefono().getText());
            return true;
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, 
                    "El campo telefono debe ser númerico", 
                    "error", JOptionPane.ERROR);
            System.out.print("Error al cargar los datos " + e);
          return false;
        }
    }
    
    private void limpiarCampos(){
        vista.getTxtNombre().setText("");
        vista.getTxtDireccion().setText("");
        vista.getTxtTelefono().setText("");
        Codigo = 0;
        Nombre = "";
        Direccion = "";
        Telefono = 0;
}

    private void agregarCliente(){
        try {
            if(validarDatos()){
                if (cargarDatos()){
                    Cliente cliente = new Cliente(Nombre, Direccion, Telefono);
                    clienteDAO.agregar(cliente);
                    JOptionPane.showMessageDialog(null, "Registro agregado con exito");
                    limpiarCampos();
                }
            }
            
        } catch (Exception e){
            System.out.println("Error al agregar (C)");
        }finally {
            listarTabla();
        }
    }
//----------------------------------------------------------------
    @Override
public void actionPerformed (ActionEvent ae) {
     if (ae.getSource() ==  vista.getBtnAgregar()){
         agregarCliente();
        } 
    }
}
