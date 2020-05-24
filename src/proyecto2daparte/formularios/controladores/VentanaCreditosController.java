package proyecto2daparte.formularios.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class VentanaCreditosController implements Initializable {

    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Salir(ActionEvent event) {
        Window stage = ((Node) (event.getSource())).getScene().getWindow();
        stage.hide();
       
    }

}
