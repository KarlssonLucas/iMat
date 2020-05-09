import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import se.chalmers.cse.dat216.project.*;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

import static se.chalmers.cse.dat216.project.ProductCategory.*;


public class iMatController implements Initializable, ShoppingCartListener {

    @FXML private AnchorPane paneTop;
    @FXML private ImageView logo;
    @FXML private Label logoText;
    @FXML private Button minaSidorButton;
    @FXML private Button checkoutButton;
    @FXML private Button helpButton;
    @FXML private StackPane background;
    @FXML private TextField searchField;

    @FXML private ImageView reaBanner;
    @FXML private StackPane gridPane;
    @FXML private Button reaButton;
    @FXML private AnchorPane bannerImage;
    @FXML private Label categoryBannerLabel;
    @FXML private ImageView categoryBannerImage;

    @FXML private FlowPane productFlow;
    @FXML private ScrollPane categoryScroll;
    @FXML private ScrollPane scrollItemView;
    @FXML private GridPane gridCategory;
    @FXML private Label cartTotal;

    @FXML public Button bbb0;
    @FXML public Button bbb1;
    @FXML public Button bbb2;
    @FXML public Button bbb3;
    @FXML public Button bbb4;
    @FXML public Button bbb5;
    @FXML public Button bbb6;
    @FXML public Button bbb7;
    @FXML public Button bbb8;
    @FXML public Button bbb9;
    @FXML public Button bbb10;





    private final IMatDataHandler dh = IMatDataHandler.getInstance();
    public ShoppingCart shoppingCart = dh.getShoppingCart();

    private final List<Product> productList = dh.getProducts();
    private List<Product> currentProductList = dh.getProducts();

    private Map<String,iMatItem> recipeListItemMap = new HashMap<String, iMatItem>();
    private iMatItem iMatItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        minaSidorButton.setText("Mina Sidor");
        checkoutButton.setText("....");
        helpButton.setText("Hjälp");
        reaButton.setText("Shoppa");

        all_category();
        updateCart();

        reaBanner.setPreserveRatio(true);
        scrollItemView.setFitToWidth(true);
        scrollItemView.setFitToHeight(true);
    }

    @FXML
    public void searchProduct() {
        String s = String.valueOf(searchField.getCharacters());
        currentProductList = dh.findProducts(s);
        populateHashMap();
        updateRecipeList();
    }

    //TODO i think??
    public void onClick(Event event) {
    }


    private void updateRecipeList(){
        productFlow.getChildren().clear();
        for (Product r: currentProductList) {
            productFlow.getChildren().add(recipeListItemMap.get(r.getName()));
        }
    }

    private void populateHashMap(){
        for (Product product : currentProductList){
            iMatItem = new iMatItem(product, this);
            recipeListItemMap.put(product.getName(), iMatItem);
        }
    }

    public void updateCart() {
        double roundOff = Math.round(shoppingCart.getTotal() * 100.0) / 100.0;
        cartTotal.setText(String.valueOf(roundOff + " kr"));
    }

    //Button method calls
    public void all_category(){
        bannerImage.setVisible(false);
        categoryBannerImage.setImage(null);
        categoryBannerLabel.setText("");

        currentProductList = dh.getProducts();
        populateHashMap();
        updateRecipeList();
    }

    public void favoriter_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Favoriter");
        categoryBannerImage.setImage(new Image("resources/favoriterBanner.png"));

        currentProductList = dh.favorites();
        populateHashMap();
        updateRecipeList();
    }
    public void mejeri_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Mejeri & Ägg");
        categoryBannerImage.setImage(new Image("resources/dairyBanner.png"));

        currentProductList = dh.getProducts(DAIRIES);
        populateHashMap();
        updateRecipeList();
    }
    public void kott_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Kött");
        categoryBannerImage.setImage(new Image("resources/meatBanner.png"));

        currentProductList = dh.getProducts(MEAT);
        populateHashMap();
        updateRecipeList();
    }
    public void fisk_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Fisk");
        categoryBannerImage.setImage(new Image("resources/fishBanner.png"));

        currentProductList = dh.getProducts(FISH);
        populateHashMap();
        updateRecipeList();
    }
    public void frukt_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Frukt & Grönt");
        categoryBannerImage.setImage(new Image("resources/fruitBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == FRUIT || p.getCategory() == VEGETABLE_FRUIT || p.getCategory() == CITRUS_FRUIT || p.getCategory() == EXOTIC_FRUIT ||
                    p.getCategory() == CABBAGE || p.getCategory() == MELONS || p.getCategory() == ROOT_VEGETABLE || p.getCategory() == POD){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void kryddor_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Kryddor & Bär");
        categoryBannerImage.setImage(new Image("resources/spiceBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == HERB || p.getCategory() == BERRY){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void skafferi_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Skafferi");
        categoryBannerImage.setImage(new Image("resources/pantryBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == PASTA || p.getCategory() == POTATO_RICE || p.getCategory() == FLOUR_SUGAR_SALT ){
                temp.add(p);
            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void bageri_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Bageri");
        categoryBannerImage.setImage(new Image("resources/bakeryBanner.png"));

        currentProductList  = dh.getProducts(BREAD);
        populateHashMap();
        updateRecipeList();
    }

    public void dryck_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Dryck");
        categoryBannerImage.setImage(new Image("resources/drinkBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == HOT_DRINKS || p.getCategory() == COLD_DRINKS ){

                temp.add(p);

            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    public void godsaker_category(){
        bannerImage.setVisible(true);
        categoryBannerLabel.setText("Godsaker");
        categoryBannerImage.setImage(new Image("resources/candyBanner.png"));

        ArrayList<Product> temp = new ArrayList<Product>();

        for(Product p: productList){
            if (p.getCategory() == SWEET || p.getCategory() == NUTS_AND_SEEDS ){

               temp.add(p);

            }
        }
        currentProductList = temp;
        populateHashMap();
        updateRecipeList();
    }

    //End of button method calls

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        cartEvent.getShoppingItem();
    }
}
