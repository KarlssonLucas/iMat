import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;


public class iMat extends Application {

    iMatController im = new iMatController();

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        Scene scene = new Scene(root, 1175, 840);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(950);
        stage.setMinWidth(1190);
        stage.setMaxHeight(950);
        stage.setMaxWidth(1190);

        root.requestFocus();

        String iconPath = "./resources/programLogo.png";
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
        stage.getIcons().add(icon);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        IMatDataHandler.getInstance().shutDown();
    }
}
