import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class iMat extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        Scene scene = new Scene(root, 1175, 883);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(950);
        stage.setMinWidth(1190);

        root.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
