import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.regex.Pattern;

public class iMatItem extends AnchorPane {
    private iMatController parentController;
    private Product product;
    private IMatDataHandler dh = IMatDataHandler.getInstance();

    private char[] alphabet = {'a', 'b', 'c', 'd'};

    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private ImageView productPicture;
    @FXML private Button addProduct;
    @FXML private AnchorPane productItem;
    @FXML private Label productUnit;
    @FXML private TextField amountProduct;
    @FXML private Label productCategory;

    @FXML
    public void onClick(Event event) {
        System.out.println("a");
    }

    @FXML
    private void addCart() {
        if (amountProduct.getCharacters().length() != 0 && !Pattern.matches("[a-zA-Z]+", amountProduct.getCharacters())){
            int amount = Integer.parseInt(String.valueOf(amountProduct.getCharacters()));
            parentController.shoppingCart.addProduct(product, amount);
            parentController.updateCart();
            amountProduct.clear();
        }
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

        amountProduct.setPromptText("antal " + product.getUnitSuffix());
        productUnit.setText(product.getUnit());
        productPicture.setImage(dh.getFXImage(product));
        productPrice.setText(String.valueOf(product.getPrice()));
        productName.setText(product.getName());
    }
}
