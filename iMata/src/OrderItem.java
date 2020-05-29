import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;


public class OrderItem extends AnchorPane {
    private final iMatController parentController;
    private final Order order;
    private final IMatDataHandler dh = IMatDataHandler.getInstance();

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

        Locale locale = new Locale("sv", "SE");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(order.getDate());

        datumLbl.setText(date);

        prisLbl.setText(String.valueOf(parentController.getTotalGivenList(order.getItems())) + " kr");
        varorLbl.setText(String.valueOf(parentController.getAmountGivenList(order.getItems())) + " st");
        orderLbl.setText(Integer.toString(order.getOrderNumber()));

        if (dh.getOrders().indexOf(order)%2 == 0) {
            this.setStyle("-fx-background-color: #d9d9d9");
        }

    }

    public void order_to_varukorg(){
        for(ShoppingItem s: order.getItems()){
            parentController.shoppingCart.addItem(s);
            parentController.removeDup();
        }
        parentController.populateCheckout();
        parentController.updateCart();
    }
}
