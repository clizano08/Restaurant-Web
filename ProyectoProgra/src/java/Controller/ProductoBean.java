package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductoDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;

@Named(value = "producto")
@SessionScoped
public class ProductoBean implements Serializable {
   
    public ProductoBean() {
    }
 
}
