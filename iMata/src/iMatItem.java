import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class iMatItem extends AnchorPane {
    private iMatController parentController;
    private Product product;
    private IMatDataHandler dh = IMatDataHandler.getInstance();

    @FXML private Label productName;
    @FXML private Label productPrice;
    @FXML private ImageView productPicture;
    @FXML private Button addProduct;
    @FXML private AnchorPane productItem;

    @FXML
    public void onClick(Event event) {
        System.out.println("a");
    }

    public iMatItem(Product product, iMatController iMatController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imattestitem.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.parentController = iMatController;

        productPicture.setImage(dh.getFXImage(product));
        productPrice.setText(String.valueOf(product.getPrice()));
        productName.setText(product.getName());
    }

}
