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
public class ProductoDB {

    private AccesoDatos accesoDatos;
    private Connection conn;

    public ProductoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void InsertarProducto(Producto pProducto)
            throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            Producto prod = new Producto();
            prod = pProducto;

            strSQL
                    = "exec SP_InsertarProducto "
                    + prod.getId() + ","
                    + "'" + prod.getNombre() + "'" + ","
                    + prod.getPrecio() + ","
                    + prod.getCantidad() + ","
                    + prod.getCantidadMinimaVenta() + ","
                    + "'" + prod.getLink() + "'" + ","
                    + "'" + prod.getCategoria() + "'";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------- 

    public void ActualizarProducto(Producto pProducto)
            throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            Producto prod = pProducto;

            strSQL
                    = "exec SP_ActualizarProducto "
                    + prod.getId() + ","
                    + "'" + prod.getNombre() + "'" + ","
                    + prod.getPrecio() + ","
                    + prod.getCantidad() + ","
                    + prod.getCantidadMinimaVenta() + ","
                    + "'" + prod.getLink() + "'" + ","
                    + "'" + prod.getCategoria() + "'";

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------

    public void EliminarProducto(int id)
            throws SNMPExceptions, SQLException {
        String strSQL = "";

        try {

            strSQL
                    = "exec SP_EliminarProductoPorID "
                    + id;

            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------     

    public Producto SeleccionarProductoPorID(int Id)
            throws SNMPExceptions, SQLException {

        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "exec SP_SeleccionarProductoPorID " + Id;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            if (rsPA.next()) {
                Producto prod = new Producto();
                prod.Id = rsPA.getInt("Id");
                prod.nombre = rsPA.getString("Nombre");
                prod.precio = rsPA.getDouble("Precio");
                prod.cantidad = rsPA.getInt("Cantidad");
                prod.cantidadMinimaVenta = rsPA.getInt("CantidadMinimaVenta");
                prod.link = rsPA.getString("Link");
                prod.categoria = rsPA.getString("Categoria");

//                Producto prod = new Producto();
//                prod.setId(rsPA.getInt("Id"));
//                prod.setNombre(rsPA.getString("Nombre"));
//                prod.setPrecio(rsPA.getDouble("Precio"));
//                prod.setCantidad(rsPA.getInt("Cantidad"));
//                prod.setCantidadMinimaVenta(rsPA.getInt("CantidadMinimaVenta"));
//                prod.setLink(rsPA.getString("Link"));
//                prod.setCategoria(rsPA.getString("Categoria"));
                return prod;
            }

            rsPA.close();

            return null;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }

    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Producto> SeleccionarTodosProducto() throws SNMPExceptions, SQLException {
        String select = "";
        LinkedList<Producto> listaProd = new LinkedList<Producto>();

        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select
                    = "exec SP_SeleccionarTodosProducto";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {
                Producto prod = new Producto();
                prod.Id = rsPA.getInt("Id");
                prod.nombre = rsPA.getString("Nombre");
                prod.precio = rsPA.getDouble("Precio");
                prod.cantidad = rsPA.getInt("Cantidad");
                prod.cantidadMinimaVenta = rsPA.getInt("CantidadMinimaVenta");
                prod.link = rsPA.getString("Link");
                prod.categoria = rsPA.getString("Categoria");

//                Producto prod = new Producto();
//                prod.setId(rsPA.getInt("Id"));
//                prod.setNombre(rsPA.getString("Nombre"));
//                prod.setPrecio(rsPA.getDouble("Precio"));
//                prod.setCantidad(rsPA.getInt("Cantidad"));
//                prod.setCantidadMinimaVenta(rsPA.getInt("CantidadMinimaVenta"));
//                prod.setLink(rsPA.getString("Link"));
//                prod.setCategoria(rsPA.getString("Categoria"));

                listaProd.add(prod);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listaProd;
    }

}
