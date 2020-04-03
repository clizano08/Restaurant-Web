package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductoDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;

@Named(value = "mantenimientoProductosBean")
@SessionScoped
public class MantenimientoProductosBean implements Serializable {
    int Id;
    String nombre;
    double precio;
    int cantidad;
    int cantidadMinimaVenta;
    String categoria;
    String mensaje;
    String link;
    LinkedList<Producto> productos;
  
   

    public MantenimientoProductosBean() throws SNMPExceptions, SQLException {
       productos= seleccionarTodosProducto();
    }

    public LinkedList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<Producto> productos) {
        this.productos = productos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinimaVenta() {
        return cantidadMinimaVenta;
    }

    public void setCantidadMinimaVenta(int cantidadMinimaVenta) {
        this.cantidadMinimaVenta = cantidadMinimaVenta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int identificadorProductos() throws SNMPExceptions, SQLException{
        return new ProductoDB().SeleccionarTodosProducto().isEmpty() ? 1: (new ProductoDB().SeleccionarTodosProducto().size()+1);
    }
   //------------------------------------------------------------------------------------------------------------------------------------
    public String edit(Producto prod ){
       prod.setEditable(true);
       return null;
            
    }
  
    
 //-------------------------------------------------------------------------------------------------------------------------------------
       public void insertarProducto() throws SNMPExceptions, SQLException{
       
        this.Id =identificadorProductos();
        Producto prod= new Producto(Id, nombre, precio, cantidad, cantidadMinimaVenta, link, categoria);
            ProductoDB db= new ProductoDB();
            
        
        if( this.Id <= 0|| this.nombre.equals("")|| this.precio <= 0|| this.cantidad <= 0|| this.cantidadMinimaVenta <= 0|| prod.getLink().equalsIgnoreCase("") || prod.getCategoria().equalsIgnoreCase("")){
            
            this.setMensaje("Algunos campo(os) poseen errores de digitación");
            
        }else{
            
            if(db.SeleccionarProductoPorID(Id)!= null){
                
                this.setMensaje("Producto ya registrado!");
            }else{
                db.InsertarProducto(prod);
                actualizarLista();
                this.setMensaje("Producto registrado correctamente!");
                 }
            
        }
        
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void actualizarLista() throws SNMPExceptions, SQLException {
        productos.clear();
        setMensaje("");
        productos=seleccionarTodosProducto();
    }
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------
              public String actualizarProducto(Producto pro) throws SNMPExceptions, SQLException{
      this.mensaje=""; 
       
        Producto prod =pro;
            ProductoDB db= new ProductoDB();
            
        
        if( prod.getId() <= 0|| prod.getNombre().equalsIgnoreCase("")|| prod.getPrecio() <= 0|| prod.getCantidad() <= 0|| prod.getCantidadMinimaVenta() <= 0 || prod.getLink().equalsIgnoreCase("") || prod.getCategoria().equalsIgnoreCase("")){
            
            setMensaje("Ha ocurrido un error, favor verificar la integridad de los campos");
            
        }else{
            
            if(db.SeleccionarProductoPorID(prod.getId())!= null){
                db.ActualizarProducto(prod);
                actualizarLista();
                this.setMensaje(String.valueOf(prod.getId()+prod.getNombre()+prod.getPrecio()+prod.getCantidad()+prod.getCantidadMinimaVenta()));
                
            }else{
               
                this.setMensaje("No se pudo actualizar el producto");
                 }
            
        }
        for (Producto p : productos){
			p.setEditable(false);
		}
                return null;
        
    }
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------      
   public void eliminarProducto(int id) throws SNMPExceptions, SQLException{
        this.mensaje="Producto borrado satisfactoriamente!";
        new ProductoDB().EliminarProducto(id);
        actualizarLista();
    }
   //--------------------------------------------------------------------------------------------------------------------------------------------------------------
    public LinkedList<Producto> seleccionarTodosProducto() throws SNMPExceptions, SQLException{
         
        return new ProductoDB().SeleccionarTodosProducto();
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    public Producto seleccionarProductoPorID() throws SNMPExceptions, SQLException{
        return new ProductoDB().SeleccionarProductoPorID(Id);
    }
    
   
}
