package Controller;

import Model.Usuario;
import Model.UsuarioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@Named(value = "iniciaSesionBean")
@SessionScoped
public class IniciaSesionBean implements Serializable {

    String id;
    String contrasena;
    String mensajeError;

    public IniciaSesionBean() {
        id = "";
        contrasena = "";
//        usuarioLogueado = new Usuario(1, "Kevin", "Administrador", "0000", 1, "ASDASD");
    }

    public boolean hayAlgunInputVacio() {
        boolean resultado = false;
        if (id.isEmpty() || contrasena.isEmpty()) {
            resultado = true; //Si hay un campo Vacio , ahora cual sera? asi muestro un mensaje de error
            if (contrasena.isEmpty() && id.isEmpty()) {
                mensajeError = "Debes Ingresar tus credenciales";
            } else {
                if (id.isEmpty()) {
                    mensajeError = "Olvidaste ingresar un numero de Identificacion";
                } else {
                    mensajeError = "Olvidaste ingresar una contrasena";
                }
            }
        } else {
            if (!id.matches("\\d+")) {
                resultado = true;
                mensajeError = "Solo puedes utilizar caracteres numericos para ingresar tu numero de Cedula";
            }
        }
        return resultado;
    }

    public String intentoDeInicioSesion() {
        id = id.trim();
        contrasena = contrasena.trim();

        String paginaRedirigir = "index.xhtml";
        if (hayAlgunInputVacio()) {
            paginaRedirigir = null;
        } else if (!existeUsuario()) {
            paginaRedirigir = null;
        }
        return paginaRedirigir;

    }

    public boolean existeUsuario() {
        boolean encontreUnUsuario = false;
        this.mensajeError = "Haz ingresado un Numero de Identificacion y/o Contrasena Incorrectos";
        try {
            LinkedList<Usuario> usuarios = new UsuarioDB().SeleccionarTodosUsuarioActivo();
            for (Usuario usuario : usuarios) {
                if (usuario.getId().equals(id) && usuario.getClave().equals(contrasena)) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                    encontreUnUsuario = true;
                    this.mensajeError = "";
                    this.id = "";
                }
            }
        } catch (Exception e) {
            this.mensajeError = e.getMessage();
        }
        return encontreUnUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String cerrarSesion() {
        return "IniciaSesion.xhtml";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario usuarioLogueado() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map session = context.getSessionMap();
        final Usuario user = (Usuario) session.get("Usuario");
        return user;
    }

}
