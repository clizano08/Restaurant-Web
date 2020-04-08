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

@Named(value = "detallesCuenta")
@SessionScoped
public class DetallesCuentaBean implements Serializable {

    String nombre;
    String telefono;
    String direccionEntrega;

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    String tipoUsuario;
    Usuario user;
    String mensaje;

    public DetallesCuentaBean() throws SNMPExceptions, SQLException {
        user= usuarioLogueado();
        nombre = user.getNombre();
        telefono = user.getTelefono();
        tipoUsuario = user.getTipoUsuario();
    }

    public String getNombre() {
        return nombre;
    }

  

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

 
//---------------------------------------------------------------------------------------------------------------------------
    public Usuario usuarioLogueado() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map session = context.getSessionMap();
        final Usuario user = (Usuario) session.get("Usuario");
        return user;
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    
    

}
