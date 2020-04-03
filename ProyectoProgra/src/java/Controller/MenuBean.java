package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

@Named(value = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    String categoria;

    public MenuBean() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<SelectItem> Categorias() {
        ArrayList<SelectItem> categorias = new ArrayList<>();
        categorias.add(new SelectItem("Todos", "Todos"));
        categorias.add(new SelectItem("Comida Rapida", "Comida Rapida"));
        categorias.add(new SelectItem("Comida Costarricense", "Comida Costarricense"));
        categorias.add(new SelectItem("Comida Nicaraguense", "Comida Nicaraguense"));
        categorias.add(new SelectItem("Comida Salvadorena", "Comida Salvadorena"));
        categorias.add(new SelectItem("Refrescos Naturales", "Refrescos Naturales"));
        categorias.add(new SelectItem("Gaseosa", "Gaseosa"));
        return categorias;
    }

}
