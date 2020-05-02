import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import javax.imageio.stream.ImageInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    private final IMatDataHandler dh = IMatDataHandler.getInstance();
    private final Image reaImage = new Image(getClass().getClassLoader().getResourceAsStream("resources/rea.png"));
    private final List<Product> productList = dh.getProducts();
    private ArrayList<Product> Promotions = new ArrayList<Product>();
    private int page = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        minaSidorButton.setText("Mina Sidor");
        checkoutButton.setText("....");
        helpButton.setText("Hjälp");

        generatePromotions();
        populateGridPromo();
    }

    public void generatePromotions(){
        for (int i = 0; i<12; i++) {
            int rNumber = (int) (Math.random() * productList.size());
            Promotions.add(productList.get(rNumber));
        }
    }

    public void populateGridPromo() {

        int n = page * 4; //Go to current page, depends on click on buttons

        grid1.setImage(dh.getFXImage(Promotions.get(n)));
        grid2.setImage(dh.getFXImage(Promotions.get(n+1)));
        grid3.setImage(dh.getFXImage(Promotions.get(n+2)));
        grid4.setImage(dh.getFXImage(Promotions.get(n+3)));

        rea.setImage(reaImage);
        rea1.setImage(reaImage);
        rea2.setImage(reaImage);
        rea3.setImage(reaImage);
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
}
