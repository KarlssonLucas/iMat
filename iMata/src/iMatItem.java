import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.regex.Pattern;

public class iMatItem extends AnchorPane {
    private iMatController parentController;
    private Product product;
    private IMatDataHandler dh = IMatDataHandler.getInstance();

    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private ImageView productPicture;
    @FXML private AnchorPane productItem;
    @FXML private Label productUnit;
    @FXML private TextField amountProduct;
    @FXML private Label productCategory;
    @FXML public ImageView favoriteImage;
    @FXML public Button addtoCart;
    @FXML private ImageView reaImageItem;
    @FXML private Label ordinaryPrice;

    @FXML
    public void onClick(Event event) {
        if (dh.isFavorite(product)) {
            delFavorite();
        } else {
            setFavorite();
        }
    }

    @FXML
    private void addCart() {
        if (amountProduct.getCharacters().length() != 0 && !Pattern.matches("[a-zA-Z]+", amountProduct.getCharacters())){
            int amount = Integer.parseInt(String.valueOf(amountProduct.getCharacters()));
            parentController.shoppingCart.addProduct(product, amount);
            parentController.updateCart();
            amountProduct.clear();

            parentController.removeDup();
            parentController.populateCheckout();
            parentController.updateCart();
        }
    }

    @FXML
    private void subtract() {
        if (amountProduct.getCharacters().length() != 0 && Integer.parseInt(String.valueOf(amountProduct.getCharacters() )) >0) {
            int amount = Integer.parseInt(String.valueOf(amountProduct.getCharacters()));
            amount--;
            amountProduct.setText(String.valueOf(amount));
        }
    }

    @FXML
    private void addition() {
        if (amountProduct.getCharacters().length() != 0) {
            int amount = Integer.parseInt(String.valueOf(amountProduct.getCharacters()));
            amount++;
            amountProduct.setText(String.valueOf(amount));
        } else {
            amountProduct.setText("1");
        }
    }

    public void delFavorite() {
        favoriteImage.setImage(new Image("/resources/starUnfilled.png"));
        dh.removeFavorite(product);
    }

    public void setFavorite() {
        favoriteImage.setImage(new Image("/resources/starFilled.png"));
        dh.addFavorite(product);
    }

    public iMatItem(Product product, iMatController iMatController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imattestitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.parentController = iMatController;

        if (dh.isFavorite(product)) {
            setFavorite();
        }

        if (parentController.reaProducts.contains(product)) {
            productPrice.setStyle("-fx-text-fill: red");
            productUnit.setStyle("-fx-text-fill: red");
            ordinaryPrice.setVisible(true);
            ordinaryPrice.setText("Ordinarie pris " + product.getPrice()*2 + " " + product.getUnit());

            reaImageItem.setVisible(true);

        }

        amountProduct.setPromptText("antal " + product.getUnitSuffix());
        productUnit.setText(product.getUnit());
        productPicture.setImage(dh.getFXImage(product));
        productPrice.setText(String.valueOf(product.getPrice()));
        productName.setText(product.getName());
    }
}
