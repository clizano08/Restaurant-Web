package Model;

public class ArticuloProvicionales {

    private String url;
    private String nombre;
    private String cantidad;
    private String subTotal;

    public ArticuloProvicionales(String url, String nombre, String cantidad, String subTotal) {
        this.url = url;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public ArticuloProvicionales() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

}
