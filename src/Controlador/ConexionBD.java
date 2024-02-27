
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {
    Connection con;
    String bd="db_imsreports";
    String url="jdbc:mysql://localhost:3307/" + bd + "?useUnicode=true&use" + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";
    String usuario="root";
    String pwd="";
    String driver="com.mysql.cj.jdbc.Driver";
    
    public Connection ConectarBaseDeDatos (){
        try{
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url,usuario,pwd);
            System.out.println("Conexion exitosa a la base de datos " + bd);     
      } catch (ClassNotFoundException | SQLException ex) {
          System.out.print("No se pudo conectar a la base de datos " + bd);
          Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
      }
        return con;
     }
    
    public static void main(String [] args) { 
        ConexionBD conexion = new ConexionBD();
        conexion.ConectarBaseDeDatos();
    
     }
    }
