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
public class Direccion {
   int id;
   int idUsuario;
   String direccion;
   int horario;

    public Direccion() {
    }

    public Direccion(int id, int idUsuario, String direccion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.direccion = direccion;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


   
}
