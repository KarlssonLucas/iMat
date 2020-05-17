import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import java.io.IOException;
import java.util.regex.Pattern;

public class iMatCheckoutItem extends AnchorPane {
        private iMatController parentController;
        private Product product;
        private IMatDataHandler dh = IMatDataHandler.getInstance();

        @FXML private Label checkoutName;
        @FXML private Label checkoutPrice;
        @FXML private ImageView checkoutPicture;
        @FXML private AnchorPane checkoutItem;
        @FXML private Label checkoutUnit;
        @FXML private TextField checkoutAmount;
        @FXML private Button subtractCheckout;
        @FXML private Button addCheckout;

        @FXML
        private void subtract() {
            List<ShoppingItem> sc = parentController.shoppingCart.getItems();

            for(ShoppingItem s: sc){
                if(s.getProduct() == product){
                    if(s.getAmount() > 0){
                        s.setAmount(s.getAmount() - 1);
                    }
                    break;
                }
            }
            parentController.removeDup();
            parentController.populateCheckout();
            parentController.updateCheckout();
            parentController.updateCart();
        }

        @FXML
        private void addition() {
            List<ShoppingItem> sc = parentController.shoppingCart.getItems();

            for(ShoppingItem s: sc){
                if(s.getProduct() == product){
                    s.setAmount(s.getAmount() + 1);
                    break;
                }
            }

            parentController.removeDup();
            parentController.populateCheckout();
            parentController.updateCheckout();
            parentController.updateCart();
        }

        @FXML
        private void removeCheckoutItem() {
            List<ShoppingItem> sc = parentController.shoppingCart.getItems();
            sc.removeIf(i -> product.equals(i.getProduct()));

            parentController.populateCheckout();
            parentController.updateCheckout();
            parentController.updateCart();
        }

        private void setCheckoutAmount() {
            List<ShoppingItem> sc = parentController.shoppingCart.getItems();
            for (ShoppingItem r : sc) {
                if (product.equals(r.getProduct())) {
                    checkoutAmount.setText(r.getAmount() + " " + product.getUnitSuffix());
                }
            }
        }

        public iMatCheckoutItem(Product product, iMatController iMatController) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMatCheckoutItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
            this.product = product;
            this.parentController = iMatController;

            setCheckoutAmount();

            checkoutAmount.setPromptText("antal " + product.getUnitSuffix());
            checkoutUnit.setText(product.getUnit());
            checkoutPicture.setImage(dh.getFXImage(product));
            checkoutPrice.setText(String.valueOf(product.getPrice()));
            checkoutName.setText(product.getName());
        }
    }

