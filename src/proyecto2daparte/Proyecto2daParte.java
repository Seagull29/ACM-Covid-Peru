
package proyecto2daparte;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class Proyecto2daParte extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        URL direccion = new File("src/proyecto2daparte/formularios/ventanapreloader.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(direccion);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
