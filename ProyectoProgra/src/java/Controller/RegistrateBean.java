package Controller;

import DAO.SNMPExceptions;
import Model.Direccion;
import Model.DireccionDB;
import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;

@Named(value = "registrateBean")
@SessionScoped
public class RegistrateBean implements Serializable {

    String id;
    String nombre;
    String clave;
    String telefono;
    String direccion;
    String mensaje;

    public RegistrateBean() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }
  //-----------------------------------------------------------------------------------------------------------------------------------
    
    
        public int identificadorDireccion() throws SNMPExceptions, SQLException{
        return new UsuarioDB().SeleccionarTodosUsuario().isEmpty() ? 1: (new UsuarioDB().SeleccionarTodosUsuario().size()+1);
    }

   //------------------------------------------------------------------------------------------------------------------------------------
       public void insertarUsuario() throws SNMPExceptions, SQLException{
       
        int idDireccion=  identificadorDireccion();
        Direccion dir =new Direccion(idDireccion,Integer.parseInt(id), direccion);
        DireccionDB dDB=new DireccionDB();
        Usuario usuario= new Usuario(id, dir, nombre, clave, "Cliente", telefono);
        UsuarioDB uDB= new UsuarioDB();
            
            
        
        if(this.id.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || this. nombre.equalsIgnoreCase("") || this.clave.equalsIgnoreCase("") || this.telefono.equalsIgnoreCase("") ){
            
            this.setMensaje("Error favor verificar la integridad de los datos");
            
        }else{
            
            if(uDB.SeleccionarUsuarioPorID(Integer.parseInt(id)) !=  null || dDB.SeleccionarDireccionPorID(idDireccion) != null){
                
                this.setMensaje("Ya formas parte de nuestro sistema! Intentaste dirigirte a la pagina de inicio de sesion?");
            }else{
                dDB.InsertarDireccion(dir);
                uDB.InsertarUsuario(usuario);
             this.setMensaje("Usuario registrado correctamente! Espera a que nuestros administradores de acepten en el sistema");
                
            }
            
        }
        
    }

}
