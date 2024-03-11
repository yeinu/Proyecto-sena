package Modelo;



import Controlador.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ClienteDAO {
     ConexionBD conexion = new ConexionBD();
     Connection con;
     PreparedStatement ps;
     ResultSet rs; 

    public List listar(){
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
        con = conexion.ConectarBaseDeDatos();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next ()){ 
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt(1));
            cliente.setNombre(rs.getString(2));
            cliente.setDireccion(rs.getString(3));
            cliente.setTelefono(rs.getInt(4));
            lista.add(cliente);
            
            
        }
        } catch (SQLException e) {
            System.out.println("Error al listar" + e);
        }
        return lista;
    }//Fin metodo listar

// Metodo agregar
 public void agregar(Cliente cliente){
        String sql = "INSERT INTO Cliente (Nombre, Direccion, Telefono) VALUES (?,?,?)";
         try {
             con = conexion.ConectarBaseDeDatos();
             ps  = con.prepareStatement(sql);
             ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getDireccion());
             ps.setInt(3, cliente.getTelefono());
             ps.executeUpdate();
         } catch (Exception e) {
             System.out.println("Error en el metodo agregar");
         }   
      }//Fin metodo agregar
 
  }//Fin de la clase ClienteDao

 