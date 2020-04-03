package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

@Named(value = "mantenimientoUsuariosBean")
@SessionScoped
public class MantenimientoUsuariosBean implements Serializable {

    String usuario;
    String contrasena;
    String telefono;
    String direccion;
    String tipoUsuarioSeleccionado;

    public MantenimientoUsuariosBean() {

    }

    public LinkedList<SelectItem> TiposUsuarios() {
        LinkedList<SelectItem> tiposDeUsuario = new LinkedList<>();
        tiposDeUsuario.add(new SelectItem("Administrador","Administrador"));
        tiposDeUsuario.add(new SelectItem("Despachador","Despachador"));
        tiposDeUsuario.add(new SelectItem("Cliente","Cliente"));
        return tiposDeUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getTipoUsuarioSeleccionado() {
        return tipoUsuarioSeleccionado;
    }

    public void setTipoUsuarioSeleccionado(String tipoUsuarioSeleccionado) {
        this.tipoUsuarioSeleccionado = tipoUsuarioSeleccionado;
    }

}
