import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;

public class iMatController implements Initializable, ShoppingCartListener {

    @FXML private AnchorPane paneTop;
    @FXML private ImageView logo;
    @FXML private Label logoText;
    @FXML private Button minaSidorButton;
    @FXML private Button checkoutButton;
    @FXML private Button helpButton;
    @FXML private StackPane background;

    @FXML private ImageView reaBanner;
    @FXML private StackPane gridPane;
    @FXML private Button reaButton;

    @FXML private FlowPane productFlow;
    @FXML private ScrollPane categoryScroll;
    @FXML private ScrollPane scrollItemView;
    @FXML private GridPane gridCategory;
    @FXML private Label cartTotal;

    @FXML private Button bbb;



    private final IMatDataHandler dh = IMatDataHandler.getInstance();
    public ShoppingCart shoppingCart = dh.getShoppingCart();

    private final List<Product> productList = dh.getProducts();

    private Map<String,iMatItem> recipeListItemMap = new HashMap<String, iMatItem>();
    private iMatItem iMatItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        minaSidorButton.setText("Mina Sidor");
        checkoutButton.setText("....");
        helpButton.setText("Hjälp");
        reaButton.setText("Shoppa");

        populateHashMap();
        updateRecipeList();
        updateCart();

        reaBanner.setPreserveRatio(true);
        scrollItemView.setFitToWidth(true);
        scrollItemView.setFitToHeight(true);
    }


    public void onClick(Event event) {

    }


    private void updateRecipeList(){
        productFlow.getChildren().clear();
        for (Product r: productList) {
            productFlow.getChildren().add(recipeListItemMap.get(r.getName()));
        }
    }

    private void populateHashMap(){
        for (Product product : productList){

            iMatItem = new iMatItem(product, this);
            recipeListItemMap.put(product.getName(), iMatItem);
        }
    }

    public void updateCart() {
        double roundOff = Math.round(shoppingCart.getTotal() * 100.0) / 100.0;
        cartTotal.setText(String.valueOf(roundOff + " kr"));
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartEvent.getShoppingItem();
    }
}
