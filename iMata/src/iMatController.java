import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;

import javax.imageio.stream.ImageInputStream;
import java.net.URL;
import java.util.*;

public class iMatController implements Initializable {

    @FXML private ImageView logo;
    @FXML private Label logoText;
    @FXML private Button minaSidorButton;
    @FXML private Button checkoutButton;
    @FXML private Button helpButton;
    @FXML private GridPane gridPromo;
    @FXML private ImageView grid1;
    @FXML private ImageView grid2;
    @FXML private ImageView grid3;
    @FXML private ImageView grid4;
    @FXML private ImageView rea;
    @FXML private ImageView rea1;
    @FXML private ImageView rea2;
    @FXML private ImageView rea3;
    @FXML private FlowPane productFlow;



    private final IMatDataHandler dh = IMatDataHandler.getInstance();
    private ShoppingCart shoppingCart = dh.getShoppingCart();

    private final List<Product> productList = dh.getProducts();

    private Map<String,iMatItem> recipeListItemMap = new HashMap<String, iMatItem>();
    private iMatItem iMatItem;

    private final Image reaImage = new Image(getClass().getClassLoader().getResourceAsStream("resources/rea.png"));
    private ArrayList<Product> Promotions = new ArrayList<Product>();
    private ArrayList<ImageView> grid = new ArrayList<>();
    private int page = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        minaSidorButton.setText("Mina Sidor");
        checkoutButton.setText("....");
        helpButton.setText("Hjälp");

        generatePromotions();
        populateGridPromo();
        populateHashMap();
        updateRecipeList();
    }

    public void generatePromotions(){
        for (int i = 0; i<12; i++) {
            int rNumber = (int) (Math.random() * productList.size());
            Promotions.add(productList.get(rNumber));
        }

        grid.add(grid1);
        grid.add(grid2);
        grid.add(grid3);
        grid.add(grid4);
    }

    public void populateGridPromo() {

        int n = page * 4; //Go to current page, depends on click on buttons

        for(ImageView i : grid) {
            i.setImage(dh.getFXImage(Promotions.get(n)));
            n++;
        }

        rea.setImage(reaImage);
        rea1.setImage(reaImage);
        rea2.setImage(reaImage);
        rea3.setImage(reaImage);
    }

    public void onClick(Event event) {
        EventTarget target = event.getTarget();
        if (event.getTarget().equals(grid1)) {
            System.out.println("a");
        } else if (target.equals(grid2)) {
            System.out.println("b");
        } else if (target.equals(grid3)) {
            System.out.println("c");
        } else if (target.equals(grid4)) {
            System.out.println("d");
        }
    }

    public void nextPageGrid() {
        if (page<2) {
            page++;
            populateGridPromo();
        }
    }

    public void previousPageGrid() {
        if (page>0) {
            page--;
            populateGridPromo();
        }
    }

    private void updateRecipeList(){
        productFlow.getChildren().clear();
        for (Product p: productList) {
            productFlow.getChildren().add(recipeListItemMap.get(p.getName()));
        }
    }

    private void populateHashMap(){
        for (Product product : productList){
            iMatItem = new iMatItem(product, this);
            recipeListItemMap.put(product.getName(), iMatItem);
        }
    }
}
