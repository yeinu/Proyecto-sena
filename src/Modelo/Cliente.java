
package Modelo;


public class Cliente {
    private int Codigo;
    private String Nombre; 
    private String Direccion;       
    private int Telefono;
    
    public Cliente() {
        
 }
    //Constructor Agregar

    public Cliente(String Nombre, String Direccion, int Telefono) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }
    
    //cosntructor actualizar

    public Cliente(int Codigo, String Nombre, String Direccion, int Telefono) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
    }
 
    //getter y setter modificaores de acceso

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }
    
}
