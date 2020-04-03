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
import java.util.LinkedList;

@Named(value = "usuario")
@SessionScoped
public class UsuarioBean implements Serializable {
   String id;
   String direccion;
   String nombre;
   String clave;
   String tipoUsuario;
   String telefono;
   String mensaje;
   LinkedList<Usuario> listaActivo;
   LinkedList<Usuario> listaInactivo;
    public UsuarioBean() throws SNMPExceptions, SQLException {
        listaActivo= seleccionarTodosUsuarioActivo();
        listaInactivo= seleccionarTodosUsuarioInactivo();
    }

    public String getId() {
        return id;
    }

    public LinkedList<Usuario> getListaActivo() {
        return listaActivo;
    }

    public void setListaActivo(LinkedList<Usuario> listaActivo) {
        this.listaActivo = listaActivo;
    }

    public LinkedList<Usuario> getListaInactivo() {
        return listaInactivo;
    }

    public void setListaInactivo(LinkedList<Usuario> listaInactivo) {
        this.listaInactivo = listaInactivo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
    //----------------------------------------------------------------------------------------------------------------------------------
    public void actualizarListas() throws SNMPExceptions, SQLException {
        listaActivo.clear();
        listaInactivo.clear();
        listaActivo=seleccionarTodosUsuarioActivo();
        listaInactivo=seleccionarTodosUsuarioInactivo();
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
        Usuario usuario= new Usuario(id, dir, nombre, clave, tipoUsuario, telefono);
        UsuarioDB uDB= new UsuarioDB();
            
            
        
        if(this.id.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || this. nombre.equalsIgnoreCase("") || this.clave.equalsIgnoreCase("")||this.tipoUsuario.equalsIgnoreCase("") || this.telefono.equalsIgnoreCase("") ){
            
            this.setMensaje("Error favor verificar la integridad de los datos");
            
        }else{
            
            if(uDB.SeleccionarUsuarioPorID(Integer.parseInt(id)) !=  null || dDB.SeleccionarDireccionPorID(idDireccion) != null){
                
                this.setMensaje("El usuario ya se encuentra en nuestro sistema!");
            }else{
                dDB.InsertarDireccion(dir);
                uDB.InsertarUsuario(usuario);
                actualizarListas();
             this.setMensaje("Usuario registrado correctamente!");
                
            }
            
        }
        
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
       public void actualizarUsuario() throws SNMPExceptions, SQLException{
       
        int idDireccion=  identificadorDireccion();
        Direccion dir =new Direccion(idDireccion,Integer.parseInt(id), direccion);
        DireccionDB dDB=new DireccionDB();
        Usuario usuario= new Usuario(id, dir, nombre, clave, tipoUsuario, telefono);
        UsuarioDB uDB= new UsuarioDB();
            
            
        
        if(this.id.equalsIgnoreCase("") || direccion.equalsIgnoreCase("") || this. nombre.equalsIgnoreCase("") || this.clave.equalsIgnoreCase("")||this.tipoUsuario.equalsIgnoreCase("") || this.telefono.equalsIgnoreCase("") ){
            
            this.setMensaje("Error, favor verificar la integridad de los datos");
            
        }else{
            
            if(uDB.SeleccionarUsuarioPorID(Integer.parseInt(id)) !=  null || dDB.SeleccionarDireccionPorID(idDireccion) != null){
                dDB.ActualizarDireccion(dir);
                uDB.ActualizarUsuario(usuario);
                actualizarListas();
                this.setMensaje("El Usuario se ha actualizado correctamnete!");
            }else{
               
                this.setMensaje("ha ocurrido un error al actualizar los datos!");
            }
            
        }
        
    }
  //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   public void eliminarUsuario(int id) throws SNMPExceptions, SQLException{  
       new UsuarioDB().EliminarUsuario(id);
       actualizarListas(); 
       this.mensaje="Usuario borrado satisfactoriamente!"; 
    }
   //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       public LinkedList<Usuario> seleccionarTodosUsuario() throws SNMPExceptions, SQLException{
         
        return new UsuarioDB().SeleccionarTodosUsuario();
    }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       public LinkedList<Usuario> seleccionarTodosUsuarioActivo() throws SNMPExceptions, SQLException{
         
        return new UsuarioDB().SeleccionarTodosUsuarioActivo();
    }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
         public LinkedList<Usuario> seleccionarTodosUsuarioInactivo() throws SNMPExceptions, SQLException{
         
        return new UsuarioDB().SeleccionarTodosUsuarioInactivo();
    }
 //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   public void activarUsuarioPorId(int id) throws SNMPExceptions, SQLException{
        new UsuarioDB().ActivarUsuarioPorID(id);
        actualizarListas();
        this.mensaje="El Usuario se ha activado!";
    }
     


    
}
