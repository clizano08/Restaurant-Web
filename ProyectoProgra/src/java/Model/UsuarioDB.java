/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author carlo
 */
public class UsuarioDB {
  private AccesoDatos accesoDatos;
  private Connection conn;

    public UsuarioDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
              public void InsertarUsuario(Usuario pUsuario) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
             Usuario us = new Usuario();
        us=pUsuario;
        boolean activo = us.getTipoUsuario().equalsIgnoreCase("Administrador") ?  true :
                         us.getTipoUsuario().equalsIgnoreCase("Despachador")    ? true : false;
        
            strSQL = 
            "exec SP_InsertarUsuario "
               + us.getId() + "," 
               + us.getDireccion().getId()+ ","
               + "'" + us.getNombre() + "'" +","
               + "'" + us.getClave() + "'" +","
               + "'" + us.getTipoUsuario() + "'" +","
               + "'" + us.getTelefono() + "'" +","
               + activo;
                            
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
              public void ActualizarUsuario(Usuario pUsuario) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
             Usuario us = new Usuario();
        us=pUsuario;
        boolean activo = us.getTipoUsuario().equalsIgnoreCase("Administrador") ? true : false;
        
            strSQL = 
            "exec SP_ActualizarUsuario "
               + us.getId() + "," 
               + us.getDireccion().getId()+ ","
               + "'" + us.getNombre() + "'" +","
               + "'" + us.getClave() + "'" +","
               + "'" + us.getTipoUsuario() + "'" +","
               + "'" + us.getTelefono() + "'" +","
               + activo;
                            
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void EliminarUsuario(int id) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
          
        
            strSQL = 
            "exec SP_EliminarUsuarioPorID "
               + id; 
             
                            
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------

    public Usuario SeleccionarUsuarioPorID(int Id) 
            throws SNMPExceptions, SQLException{
           
        
        String select="";
         try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
            
            select="exec SP_SeleccionarUsuarioPorID "+Id;
                    
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            if(rsPA.next()){
              Usuario us= new Usuario();
              us.id= String.valueOf(rsPA.getInt("ID"));
              us.direccion = new DireccionDB().SeleccionarDireccionPorID(rsPA.getInt("IDDireccion"));
              us.nombre = rsPA.getString("Nombre");
              us.tipoUsuario= rsPA.getString("TipoUsuario");
              us.telefono= rsPA.getString("Telefono");              
              return  us;
            }
            
            rsPA.close();
      
            return null;
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Usuario> SeleccionarTodosUsuario() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Usuario> listaUsuario= new LinkedList<Usuario>();
        
        try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
           
            select=
                    "exec SP_SeleccionarTodosUsuario";
           
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                Usuario us= new Usuario(); 
              us.id= String.valueOf(rsPA.getInt("ID"));
              us.direccion = new DireccionDB().SeleccionarDireccionPorID(rsPA.getInt("IDDireccion"));
              us.nombre = rsPA.getString("Nombre");
              us.tipoUsuario= rsPA.getString("TipoUsuario");
              us.telefono= rsPA.getString("Telefono");              
               
               
                
                listaUsuario.add(us);
            }
            rsPA.close();
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaUsuario;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Usuario> SeleccionarTodosUsuarioActivo() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Usuario> listaUsuario= new LinkedList<Usuario>();
        
        try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
           
            select=
                    "exec SP_SeleccionarTodosUsuarioActivo";
           
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                Usuario us= new Usuario(); 
              us.id= String.valueOf(rsPA.getInt("ID"));
              us.direccion = new DireccionDB().SeleccionarDireccionPorID(rsPA.getInt("IDDireccion"));
              us.nombre = rsPA.getString("Nombre");
              us.tipoUsuario= rsPA.getString("TipoUsuario");              
              us.telefono= rsPA.getString("Telefono");              
               
               
                
                listaUsuario.add(us);
            }
            rsPA.close();
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaUsuario;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Usuario> SeleccionarTodosUsuarioInactivo() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Usuario> listaUsuario= new LinkedList<Usuario>();
        
        try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
           
            select=
                    "exec SP_SeleccionarTodosUsuarioInactivo";
           
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                Usuario us= new Usuario(); 
              us.id= String.valueOf(rsPA.getInt("ID"));
              us.direccion = new DireccionDB().SeleccionarDireccionPorID(rsPA.getInt("IDDireccion"));
              us.nombre = rsPA.getString("Nombre");
              us.tipoUsuario= rsPA.getString("TipoUsuario");
              us.telefono= rsPA.getString("Telefono");              
               
               
                
                listaUsuario.add(us);
            }
            rsPA.close();
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaUsuario;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void ActivarUsuarioPorID(int id) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
          
        
            strSQL = 
            "exec SP_ActivarUsuarioPorID "
               + id; 
             
                            
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    
}
