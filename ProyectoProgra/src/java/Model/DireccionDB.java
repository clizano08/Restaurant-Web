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
public class DireccionDB {
  private AccesoDatos accesoDatos;
  private Connection conn;

    public DireccionDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
            public void InsertarDireccion(Direccion pDirreccion) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
             Direccion dir = new Direccion();
        dir=pDirreccion;
        
            strSQL = 
            "exec SP_InsertarDireccion "
               + dir.getId() + "," 
               + dir.getIdUsuario()+ ","
               + dir.getDireccion();
                         
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
   public void ActualizarDireccion(Direccion pDirreccion) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
             Direccion dir = new Direccion();
        dir=pDirreccion;
        
            strSQL = 
            "exec SP_ActualizarDireccion "
               + dir.getId() + "," 
               + dir.getIdUsuario()+ ","
               + dir.getDireccion();
                          
   
                     
            accesoDatos.ejecutaSQL(strSQL);


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
  //---------------------------------------------------------------------------------------------------------------------------------
    public void EliminarDireccion(int id) 
                throws SNMPExceptions, SQLException {
        String strSQL = "";

         
         try {
        
          
        
            strSQL = 
            "exec SP_EliminarDireccionPorID "
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
    public Direccion SeleccionarDireccionPorID(int Id) 
            throws SNMPExceptions, SQLException{
           
    
        String select="";
         try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
            
            select="exec SP_SeleccionarDireccionPorID "+Id;
                    
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            if(rsPA.next()){
              Direccion dir= new Direccion();
              dir.id = rsPA.getInt("ID");
              dir.idUsuario = rsPA.getInt("IDUsuario");
              dir.direccion = rsPA.getString("Direccion");
             
              return  dir;
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
    public LinkedList<Direccion> SeleccionarTodosDireccion() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Direccion> listaDir= new LinkedList<Direccion>();
        
        try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
           
            select=
                    "exec SP_SeleccionarTodosDireccion";
           
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                Direccion dir= new Direccion(); 
                dir.id = rsPA.getInt("ID");
              dir.idUsuario = rsPA.getInt("IDUsuario");
              dir.direccion = rsPA.getString("Direccion");  
              
               
                
                listaDir.add(dir);
            }
            rsPA.close();
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaDir;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Direccion> SeleccionarTodosDireccionPorUsuarioID(int idUsuario) throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Direccion> listaDir= new LinkedList<Direccion>();
        
        try{
            
            AccesoDatos accesoDatos= new AccesoDatos();
            
           
            select=
                    "exec SP_SeleccionarTodosDireccionPorUsuarioID"+ idUsuario;
           
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                Direccion dir= new Direccion(); 
              dir.id = rsPA.getInt("ID");
              dir.idUsuario = rsPA.getInt("IDUsuario");
              dir.direccion = rsPA.getString("Direccion");  
               
              
                
                listaDir.add(dir);
            }
            rsPA.close();
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaDir;
    }

}
