/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2daparte.formularios.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author selitzia
 */
public class VentanaAyudaController implements Initializable {

    @FXML
    private ToggleGroup radGrupoHelp;
    @FXML
    private Label lblSelecionOpcion;
    @FXML
    private ImageView help_AñadirDatos;
    @FXML
    private Label lblHelpInfo;
    @FXML
    private ImageView help_Graficos1;
    @FXML
    private ImageView help_graficos2;
    @FXML
    private RadioButton radAdd;
    private RadioButton radGrafCant;
    @FXML
    private RadioButton radGrafFunc;
    @FXML
    private RadioButton radTable;
    @FXML
    private ImageView helpFuncionBotones;
    @FXML
    private Button btnOkayuda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblHelpInfo.setText("");
        lblSelecionOpcion.setVisible(true);
        
        btnOkayuda.setOnAction(mostrarAyudas -> mostrarayuda());

    }

    public void mostrarayuda() {
        lblSelecionOpcion.setVisible(false);
        if (radGrupoHelp.getSelectedToggle().equals(radAdd)) {
            
            help_AñadirDatos.setVisible(true);
            lblHelpInfo.setText("Los siguientes campos de texto permiten la entrada como 'string' del nombre de la region. \n "
                    + "Y para el resto de datos se recibe un entero para su agregado en la base de datos ");
            help_Graficos1.setVisible(false);
            help_graficos2.setVisible(false);
            helpFuncionBotones.setVisible(false);
        } else if (radGrupoHelp.getSelectedToggle().equals(radGrafCant)) {
            help_AñadirDatos.setVisible(false);
            help_Graficos1.setVisible(true);
            help_graficos2.setVisible(true);
            lblHelpInfo.setText("Para habilitar la generación de graficos debe hacer click en el check_box 'mostrar grafico' \n"
                    + "Lo cual habilitará el combo box para selecciónar la cantidad de graficos, se pueden mostrar hasta 3 graficos. Presione el boton de OK \n"
                    + "Seguidamente se habilitará el siguiente check box, donde seleccionará el tipo de grafico para cada uno de los que desea. \n"
                    + "Debe hacer click en el boton OK hasta 3 veces por grafico seleccionado.");
            helpFuncionBotones.setVisible(false);

        } else if (radGrupoHelp.getSelectedToggle().equals(radGrafFunc)) {
            
            helpFuncionBotones.setVisible(true);
            lblHelpInfo.setText("Los Botones Funcionan de la siguiente Manera: \n"
                    + "Actualizar se utiliza para actualizar los graficos (si es que habilitó la opcion). Hacer esto cada vez que agrege datos. \n"
                    + "Agregar funciona para agregar a la base de datos los elementos colocados en los campos de texto. \n"
                    + "Eliminar se usa para borrar un dato selecciónado en la tabla de datos. \n"
                    + "Editar retorna un dato seleccionado a los campos de texto para su edición, cuando finalize, haga click en agregar.");

            help_Graficos1.setVisible(false);
            help_graficos2.setVisible(false);
        } else if (radGrupoHelp.getSelectedToggle().equals(radTable)) {
            
            lblHelpInfo.setText("La tabla muestra los datos que se van agregando a la base de datos y se utilizan para la generación de los graficos.");

            help_Graficos1.setVisible(false);
            help_graficos2.setVisible(false);
            helpFuncionBotones.setVisible(false);
        }
    }
}
