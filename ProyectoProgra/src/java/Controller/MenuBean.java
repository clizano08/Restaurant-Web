package Controller;

import Model.Categoria;
import Model.Producto;
import Model.ProductoDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

@Named(value = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    String categoria;
    String mensajeError;
    LinkedList<Producto> productosFiltrados;

    public MenuBean() {
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

    public void AnadirProductoPedido(Producto productoSeleccionado) {
        mensajeError = "2";
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
