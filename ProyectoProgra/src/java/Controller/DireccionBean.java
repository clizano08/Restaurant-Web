/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Direccion;
import Model.DireccionDB;
import Model.Usuario;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlo
 */
@Named(value = "direccionBean")
@SessionScoped
public class DireccionBean implements Serializable {

   int id;
   int idUsuario;
   String direccion;
   String direccionEntrega;
   String mensaje;
   Usuario user;
   LinkedList<Direccion> direcciones;
    public DireccionBean() throws SNMPExceptions, SQLException {
      user=usuarioLogueado();
      direcciones= seleccionarTodosDireccionPorUsuarioID(Integer.parseInt(user.getId()));
    }

    public int getId() {
        return id;
    }

    public LinkedList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
 //--------------------------------------------------------------------------------------------------------------------------------------------------
           public int identificadorDireccion() throws SNMPExceptions, SQLException{
        return new DireccionDB().SeleccionarTodosDireccion().isEmpty() ? 1: (new DireccionDB().SeleccionarTodosDireccion().size()+1);
    }
//-------------------------------------------------------------------------------------------------------------------------------------
       public void insertarDireccion() throws SNMPExceptions, SQLException{
       
        this.id =identificadorDireccion();
        Usuario u = usuarioLogueado();
        Direccion dir= new Direccion(id,Integer.parseInt(u.getId()) , direccionEntrega);
         DireccionDB db= new DireccionDB();
            
        
        if( this.direccionEntrega.equalsIgnoreCase("") ){
            
            this.setMensaje("Campo de direccion vacio");
            
        }else{
            
            if(db.SeleccionarDireccionPorID(this.id)!= null){
                
                this.setMensaje("Direccion ya registrada!");
            }else{
                db.InsertarDireccion(dir);
                actualizarLista();
                setDireccionEntrega("");
                this.setMensaje("Direccion registrada correctamente!");
                 }
            
        }
        
    }

 //--------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Direccion> seleccionarTodosDireccionPorUsuarioID(int idUsuario) throws SNMPExceptions, SQLException{
         
        return new DireccionDB().SeleccionarTodosDireccionPorUsuarioID(idUsuario);
    }

 //--------------------------------------------------------------------------------------------------------------------------------------------------
       public void eliminarDireccion(int id) throws SNMPExceptions, SQLException{  
       new DireccionDB().EliminarDireccion(id);
       actualizarLista(); 
       this.mensaje="Direccion borrada satisfactoriamente!"; 
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------
    private void actualizarLista() throws SNMPExceptions, SQLException {
      direcciones.clear();
      direcciones = seleccionarTodosDireccionPorUsuarioID(Integer.parseInt(user.getId()));
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------------
        public Usuario usuarioLogueado() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map session = context.getSessionMap();
        final Usuario user = (Usuario) session.get("Usuario");
        return user;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
