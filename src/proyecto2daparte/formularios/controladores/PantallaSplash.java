
package proyecto2daparte.formularios.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author selitzia
 */
public class PantallaSplash extends Thread {
    
    
     private void Cerrar() throws IOException {
        URL direccionCreditos = new File("src/proyecto2daparte/formularios/VentanaCreditos.fxml").toURI().toURL();
        Parent creditos = FXMLLoader.load(direccionCreditos);
        Scene escena = new Scene(creditos);
        Stage stage = new Stage();
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(escena);
        stage.show();
    }
    
    
    
    @Override
    public void run() {
        try {
            Thread.sleep(6000);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Parent raiz = null;
                    try {
                        URL direccion = new File("src/proyecto2daparte/formularios/frmPrincipal.fxml").toURI().toURL();
                     
                        raiz = FXMLLoader.load(direccion);

                    } catch (IOException io) {
                        io.getMessage();
                    }
                    Scene escena = new Scene(raiz);
                    Stage stage = new Stage();
                    stage.setScene(escena);
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Graficos estadisticos");
                    stage.show();

                    stage.setOnCloseRequest(evento -> {
                        try {
                            Cerrar();
                        } catch (IOException error) {
                        }
                    });

                    VentanapreloaderController.getPreload().getScene().getWindow().hide();
 
                }
            });
        } catch (InterruptedException error) {

        }
    }
}
