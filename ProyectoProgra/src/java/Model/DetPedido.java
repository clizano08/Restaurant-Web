package Model;

public class DetPedido {

    private Producto producto;
    private int cantidad;

    public DetPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double subtotal() {
        return producto.getCantidad() * producto.precio;
    }

    public double iva() {
        return subtotal() * 0.13;
    }

    public double total() {
        return subtotal() + iva();
    }

}
