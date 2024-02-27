
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
    String sql ="select * from cliente";
    List<Cliente> lista = new ArrayList<>();
    try {
        con = conexion.ConectarBaseDeDatos();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next ())
        { 
            Cliente cliente = new Cliente();
            cliente.setCodigo(rs.getInt(1));
            cliente.setNombre(rs.getString(2));
            cliente.setDireccion(rs.getString(3));
            cliente.setTelefono(rs.getInt(4));
        }
        
    } catch (SQLException e) {
        System.out.println("Error al listar" + e);
    }

    return lista;
 }//Fin metodo listar
}