package Controller;

import Model.Categoria;
import Model.DetPedido;
import Model.EncPedido;
import Model.Producto;
import Model.ProductoDB;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value = "pedidoBean")
@SessionScoped
public class PedidoBean implements Serializable {

    //Carrito
    EncPedido encPedido;
    //Menu
    String categoria;
    String mensajeError;
    LinkedList<Producto> productosFiltrados;

    public PedidoBean() {
        encPedido = new EncPedido();
        encPedido.setId(1); // Seleccionar todos los encFactura, obtenemos el conteo total, luego sumamos 1; Asi obtenemos un nuevo id ;v
        encPedido.setUsuario(usuarioLogueado());
        categoria = "Todos";
        productosFiltrados = filtrarProductosPorCategoria();
    }

    public LinkedList<SelectItem> categorias() {
        LinkedList<SelectItem> categorias = new LinkedList<>();
        for (Categoria categoria : Categoria.values()) {
            categorias.add(new SelectItem(categoria.toString(), categoria.toString()));
        }
        return categorias;
    }

    public LinkedList<Producto> seleccionarTodosProductos() {
        ProductoDB db = new ProductoDB();
        LinkedList<Producto> todos = new LinkedList<Producto>();
        try {
            todos = db.SeleccionarTodosProducto();
        } catch (Exception ex) {
            mensajeError = ex.getMessage();
        }
        return todos;
    }

    public LinkedList<Producto> filtrarProductosPorCategoria() {
        LinkedList<Producto> todos = seleccionarTodosProductos();
        LinkedList<Producto> productosFiltrados = new LinkedList<Producto>();
        if (categoria.equals("Todos")) {
            productosFiltrados = todos;
        } else {
            for (Producto producto : todos) {
                if (producto.getCategoria().equals(categoria)) {
                    productosFiltrados.add(producto);
                }
            }
        }
        return productosFiltrados;
    }

    public void filtrarProductos() {
        productosFiltrados = filtrarProductosPorCategoria();
    }

    public void agregarArticuloPedido(Producto producto) {

    }

    public void sumar1(DetPedido detalle) {

    }

    public void restar1(DetPedido detalle) {

    }

    public void eliminarArticuloPedido(DetPedido detalle) {

    }

    public LinkedList<DetPedido> obtenerDetallePedido() {
        LinkedList<DetPedido> detalles = new LinkedList<>();
        return detalles;
    }

    public double subTotal() {
        return 1;
    }

    public double iva() {
        return 1;
    }

    public double total() {
        return 1;
    }

    public Usuario usuarioLogueado() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map session = context.getSessionMap();
        final Usuario user = (Usuario) session.get("Usuario");
        return user;
    }

    public EncPedido getEncPedido() {
        return encPedido;
    }

    public void setEncPedido(EncPedido encPedido) {
        this.encPedido = encPedido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public LinkedList<Producto> getProductosFiltrados() {
        return productosFiltrados;
    }

    public void setProductosFiltrados(LinkedList<Producto> productosFiltrados) {
        this.productosFiltrados = productosFiltrados;
    }
}
