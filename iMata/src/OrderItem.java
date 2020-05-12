import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class OrderItem extends AnchorPane {
    private iMatController parentController;
    private Order order;
    private IMatDataHandler dh = IMatDataHandler.getInstance();
    private double kostnad = 0;
    private int varor = 0;

    @FXML private Label datumLbl;
    @FXML private Label prisLbl;
    @FXML private Label varorLbl;
    @FXML private Label orderLbl;
    @FXML private Button orderBtn;

    public OrderItem(Order order, iMatController iMatController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        this.parentController = iMatController;

        for(ShoppingItem i: order.getItems()){
            kostnad += i.getTotal();
            varor ++;
        }

        datumLbl.setText(order.getDate().toString());
        prisLbl.setText(Double.toString(kostnad));
        varorLbl.setText(Integer.toString(varor));
        orderLbl.setText(Integer.toString(order.getOrderNumber()));



    }

    public void order_to_varukorg(){
        for(ShoppingItem s: order.getItems()){
            parentController.shoppingCart.addItem(s);
        }
        parentController.updateCart();
    }
}
