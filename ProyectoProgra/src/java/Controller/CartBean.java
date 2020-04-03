package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    public CartBean() {
    }
    
}
