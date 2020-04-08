package Controller;

import Model.ArticuloProvicionales;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    public CartBean() {
    }

    public int cantidadArticulosCarro() {
        return 5;
    }

    public ArrayList<ArticuloProvicionales> listaArticulosProvicionales() {
        ArrayList<ArticuloProvicionales> lista = new ArrayList<>();
        lista.add(new ArticuloProvicionales("https://images.unsplash.com/photo-1552895638-f7fe08d2f7d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1267&q=80", "Mc Donald", "7", "3500"));
        lista.add(new ArticuloProvicionales("https://images.unsplash.com/photo-1542488246-1390a9a99a30?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80", "KFC", "14", "17000"));
        lista.add(new ArticuloProvicionales("https://images.unsplash.com/photo-1482173074468-5b323335debe?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80", "Wrapping Paper", "2", "100000"));
        lista.add(new ArticuloProvicionales("https://images.unsplash.com/photo-1551151603-59926f9333df?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80", "Fat Burguer", "123", "400"));
        return lista;
    }

    public int total() {
        return 25;
    }
}
