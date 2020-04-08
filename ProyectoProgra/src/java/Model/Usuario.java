/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author carlo
 */
public class Usuario {

    String id;
    Direccion direccion;
    String nombre;
    String clave;
    String tipoUsuario;
    String telefono;
    String editable;

    public Usuario() {
    }

    public Usuario(String id, Direccion direccion, String nombre, String clave, String tipoUsuario, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
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

    public boolean esAdministrador() { //Todo
        return this.tipoUsuario.equals("Administrador");
    }

    public boolean esDespachador() { //Home -- Despachos -- Cuenta
        return this.tipoUsuario.equals("Despachador") || this.tipoUsuario.equals("Administrador");
    }

    public boolean esCliente() { //Home -- Menu -- Cuenta
        return this.tipoUsuario.equals("Cliente") || this.tipoUsuario.equals("Administrador");
    }

}
