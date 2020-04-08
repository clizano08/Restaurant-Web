package Model;

import java.util.LinkedList;

public class EncPedido {

    private int id;
    private Usuario usuario;
    private LinkedList<DetPedido> detallesPedido;

    public EncPedido() {
        detallesPedido = new LinkedList<>();
        detallesPedido.add(new DetPedido(new Producto(1, "A", 100, 1, 1, "https://images.unsplash.com/photo-1461009312844-e80697a81cc7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1280&q=80", "Bebida"), 3));
        detallesPedido.add(new DetPedido(new Producto(1, "B", 250, 1, 1, "https://images.unsplash.com/photo-1557925923-cd4648e211a0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=633&q=80", "Bebida"), 3));
        detallesPedido.add(new DetPedido(new Producto(1, "C", 375, 1, 1, "https://images.unsplash.com/photo-1551879400-111a9087cd86?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80", "Bebida"), 3));
        detallesPedido.add(new DetPedido(new Producto(1, "D", 500, 1, 1, "https://images.unsplash.com/photo-1516054575922-f0b8eeadec1a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80", "Bebida"), 3));
        detallesPedido.add(new DetPedido(new Producto(1, "E", 185, 1, 1, "https://images.unsplash.com/photo-1562440499-64c9a111f713?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80", "Bebida"), 3));

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LinkedList<DetPedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(LinkedList<DetPedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public void anadirDetallePedido(Producto producto, int cantidad) {
        detallesPedido.add(new DetPedido(producto, cantidad));
    }

    public int cantidadArticulos() {
        return detallesPedido.size();
    }

    public double subTotal() {
        double subTotal = 0;
        for (DetPedido detPedido : detallesPedido) {
            subTotal += detPedido.subtotal();
        }
        return subTotal;
    }

    public double iva() {
        double iva = 0;
        for (DetPedido detPedido : detallesPedido) {
            iva += detPedido.iva();
        }
        return iva;
    }

    public double total() {
        return subTotal() + iva();
    }

}
