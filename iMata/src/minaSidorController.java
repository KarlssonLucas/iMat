import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class minaSidorController implements Initializable, ShoppingCartListener {

    @FXML private Button minaSidorButton;
    @FXML private Button checkoutButton;
    @FXML private Button helpButton;
    @FXML private Button butikButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // NAVIGATION

    public void butiken(ActionEvent event) throws IOException {

        Parent butikenParent = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        Scene butikenScene = new Scene(butikenParent);

        Stage butikenStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        butikenStage.setScene(butikenScene);
        butikenStage.show();

    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

    }
}
