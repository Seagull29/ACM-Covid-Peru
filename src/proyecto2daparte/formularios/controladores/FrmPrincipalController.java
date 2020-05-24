
package proyecto2daparte.formularios.controladores;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import proyecto2daparte.entidades.Fallecido;
import proyecto2daparte.entidades.FallecidoPK;
import proyecto2daparte.entidades.Region;
import proyecto2daparte.entidades.entidadesDAO.FallecidoDAO;
import proyecto2daparte.entidades.entidadesDAO.RegionDAO;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FrmPrincipalController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtRegion;
    @FXML
    private TextField txtPruebasMoleculares;
    @FXML
    private TextField txtPruebasRapidas;
    @FXML
    private TextField txtCasosTotales;
    @FXML
    private TextField txtLetalidad;
    @FXML
    private ComboBox<String> cb_GrafCant_DE;
    @FXML
    private ComboBox<String> cbGrafType_DE;
    @FXML
    private Button btnOKGraf_Cant_DE;
    @FXML
    private Button btnOKGrafType_DE;
    @FXML
    private Button btnAgregar_DE;
    @FXML
    private Button btnEliminar_DE;
    @FXML
    private Button btnEditar_DE;
    @FXML
    private Button btnAyuda_DE;
    @FXML
    private Button btnListar_DE;
    @FXML
    private CheckBox check_Graficos;
    @FXML
    private Label lblGrafCant;
    @FXML
    private TableView<Region> tvTDatos;
    @FXML
    private TableColumn<Region, String> tcFecha;
    @FXML
    private TableColumn<Region, String> tcRegion;
    @FXML
    private TableColumn<Region, Integer> tcPruebasM;
    @FXML
    private TableColumn<Region, Integer> tcPruebasR;
    @FXML
    private TableColumn<Region, Integer> tcCasosT;
    @FXML
    private TableColumn<Region, Double> tcLetalidad;

    @FXML
    private Button btnEncontrar_DE;
    @FXML
    private Button btnOKGrafType_DEFallecidos;
    @FXML
    private Button btnOKGraf_Cant_DEFallecidos;
    @FXML
    private CheckBox check_GraficosFallecidos;
    @FXML
    private ComboBox<?> cb_GrafCant_DEFallecidos;
    @FXML
    private ComboBox<?> cbGrafType_DEFallecidos;
    @FXML
    private Label lblGrafCant1;
    @FXML
    private Button btnListar_Fallecidos;
    @FXML
    private Button btnAgregar__Fallecidos;
    @FXML
    private Button btnEliminar__Fallecidos;
    @FXML
    private Button btnEditar__Fallecidos;
    @FXML
    private Button btnEncontrar__Fallecidos;
    @FXML
    private TableView<Fallecido> tvTDatos1;
    @FXML
    private Tab tpRegion;
    @FXML
    private Tab tpFallecidos;
    @FXML
    private ComboBox<String> cbSexo_Fallecidos;
    @FXML
    private ComboBox<String> cbRegion_Fallecidos;
    @FXML
    private ComboBox<String> cbEtapa_Fallecidos;
    @FXML
    private ComboBox<String> cbCategoria_Fallecidos;
    @FXML
    private TableColumn<Fallecido, String> tcCategoria;
    @FXML
    private TableColumn<Fallecido, String> tcEtapa;
    @FXML
    private TableColumn<Fallecido, String> tcSexo;
    @FXML
    private TableColumn<Fallecido, String> tcRegion_Fallecidos;
    @FXML
    private Tab tpGraficos;
    @FXML
    private BorderPane bpGrafico;
    @FXML
    private BorderPane bpGrafico2;
    private final RegionDAO regionDAO = new RegionDAO();
    private final FallecidoDAO fallecidoDAO = new FallecidoDAO();

    private ObservableList<Region> regiones;
    private ObservableList<Fallecido> fallecidos;
    
    private final Graficos grafico = new Graficos();

    private final Alert alerta = new Alert(Alert.AlertType.INFORMATION);
//    private int basisgraf = 1;
//    private int[] grafictypes = {0, 0, 0};
    @FXML
    private Tab tpGraficos2;
    @FXML
    private Tab tpGraficos3;
    @FXML
    private HBox hbGraficos;


    private String FechaActual(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        return formato.format(fecha);
    }
    
    private void setGrafico(Node nodo) {
    	bpGrafico.setCenter(nodo);
    	FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(nodo);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();  
    }
    
    private void setGraficoMuertos(Node nodo) {
    	bpGrafico2.setCenter(nodo);
        FadeTransition ft2 = new FadeTransition(Duration.millis(1500));
        ft2.setNode(nodo);
        ft2.setFromValue(0.1);
        ft2.setToValue(1);
        ft2.setCycleCount(1);
        ft2.setAutoReverse(false);
        ft2.play();
    }
    
    
    private void setOtrosGraficos(Node nodo, Node nodo2) {
    	
    	hbGraficos.getChildren().set(0, nodo);
    	hbGraficos.getChildren().set(1, nodo2);
    }
    

    private void LimpiarTextos() {
        txtRegion.clear();
        txtPruebasMoleculares.clear();
        txtPruebasRapidas.clear();
        txtCasosTotales.clear();
        txtLetalidad.clear();
    }

    private void LimpiarCB() {
        cbEtapa_Fallecidos.getSelectionModel().clearSelection();
        cbSexo_Fallecidos.getSelectionModel().clearSelection();
        cbRegion_Fallecidos.getSelectionModel().clearSelection();
        cbCategoria_Fallecidos.getSelectionModel().clearSelection();
    }

    private void LlenarTextos() {
        try {
            txtRegion.setText(tvTDatos.getSelectionModel().getSelectedItem().getNombre());
            txtPruebasMoleculares
                    .setText(String.valueOf(tvTDatos.getSelectionModel().getSelectedItem().getPruebasMoleculares()));
            txtPruebasRapidas
                    .setText(String.valueOf(tvTDatos.getSelectionModel().getSelectedItem().getPruebasRapidas()));
            txtCasosTotales.setText(String.valueOf(tvTDatos.getSelectionModel().getSelectedItem().getTotalCasos()));
            txtLetalidad.setText(String.valueOf(tvTDatos.getSelectionModel().getSelectedItem().getLetalidad()));
        } catch (NullPointerException nulo) {
            System.out.printf("Valor seleccionado: %s%n", nulo.getMessage());
        }
    }

    private void LlenarCB() {
        try {
            Fallecido nodo = tvTDatos1.getSelectionModel().getSelectedItem();
            cbEtapa_Fallecidos.setValue(nodo.getEtapa());
            cbSexo_Fallecidos.setValue(nodo.getSexo());
            cbRegion_Fallecidos.setValue(nodo.getRegion1().getNombre());
            cbCategoria_Fallecidos.setValue(nodo.getCategoria());
        } catch (NullPointerException nulo) {
            System.out.printf("Valor seleccionado: %s%n", nulo.getMessage());
        }
    }

    private void ListarTabla() {
        tvTDatos.setItems(regiones);
        tcRegion.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<String>(celda.getValue().getNombre()));
        tcPruebasM.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<Integer>(celda.getValue().getPruebasMoleculares()));
        tcPruebasR.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<Integer>(celda.getValue().getPruebasRapidas()));
        tcCasosT.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<Integer>(celda.getValue().getTotalCasos()));
        tcLetalidad.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<Double>(celda.getValue().getLetalidad()));
        tcFecha.setCellValueFactory(
                celda -> new ReadOnlyObjectWrapper<String>(FechaActual(celda.getValue().getFecha())));
    }

    private void ListarTablaF() {
        tvTDatos1.setItems(fallecidos);
        tcEtapa.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<String>(celda.getValue().getEtapa()));
        tcSexo.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<String>(celda.getValue().getSexo()));
        tcCategoria.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<String>(celda.getValue().getCategoria()));
        tcRegion_Fallecidos.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<String>(celda.getValue().getRegion1().getNombre()));
    }

    private ObservableList<String> NombreRegiones() {
        ObservableList<String> nombreRegiones = FXCollections.observableArrayList();
        for (Region region : regiones) {
            nombreRegiones.add(region.getNombre());
        }
        return nombreRegiones;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            regiones = FXCollections.observableArrayList(regionDAO.Listar());
            fallecidos = FXCollections.observableArrayList(fallecidoDAO.Listar());
            tvTDatos.setItems(regiones);
            tvTDatos1.setItems(fallecidos);
            ListarTabla();
            ListarTablaF();

            setGrafico(grafico.RegionCasos());
            setGraficoMuertos(grafico.RegionMuertos());
            hbGraficos.getChildren().addAll(grafico.MuertosSexo(), grafico.MuertoEtapa());

        } catch (Exception error) {
            System.out.print(error.getMessage());
        } finally {
            cb_GrafCant_DE.getItems().addAll("Un Grafico", "Dos Graficos", "Tres Graficos");
            cbGrafType_DE.getItems().addAll("Lineas", "Circular", "Barras");
//            check_Graficos.setOnAction(habilitar -> HabilitarNumGraficos());
//            btnOKGraf_Cant_DE.setOnAction(habilitar -> HabilitarTipoGrafico());
//            btnOKGrafType_DE.setOnAction(obtener -> SeleccionTipoGrafico(getCantGraficos()));
            btnAyuda_DE.setOnAction(abrirAyuda -> AbrirAyuda());
            TituloAnimacion(lblTitulo);

            cbRegion_Fallecidos.getItems().addAll(NombreRegiones());
            cbSexo_Fallecidos.getItems().addAll("Masculino", "Femenino");
            cbEtapa_Fallecidos.getItems().addAll("Adulto Mayor", "Adulto", "Joven", "Nino", "Adolescente");
            cbCategoria_Fallecidos.getItems().addAll("ESSALUD", "MINSA", "Domicilio/Alojamiento", "Clinica Privada", "PNP/FF.AA", "INPE");
            tvTDatos.setOnMouseClicked(evento -> LlenarTextos());
            tvTDatos1.setOnMouseClicked(evento -> LlenarCB());
          
        }

    }

    private void AbrirAyuda() {
        try {
            URL direccionAyuda = new File("src/proyecto2daparte/formularios/VentanaAyuda.fxml").toURI().toURL();
            Parent creditos = FXMLLoader.load(direccionAyuda);
            Scene escena = new Scene(creditos);
            Stage stage = new Stage();
            stage.centerOnScreen();
            stage.initStyle(StageStyle.DECORATED);
            stage.setScene(escena);
            stage.show();

        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

//    private void HabilitarTipoGrafico() {
//
//        cb_GrafCant_DE.setDisable(true);
//        btnOKGraf_Cant_DE.setDisable(true);
//
//        cbGrafType_DE.setDisable(false);
//        btnOKGrafType_DE.setDisable(false);
//    }

//    private int getCantGraficos() {
//        int cant = CantidadGraficos();
//        return cant;
//    }

//    private void SeleccionTipoGrafico(int grafico) {
//        String opt = cbGrafType_DE.getValue();
//        lblGrafCant.setText(basisgraf + " de: " + grafico);
//        switch (opt) {
//            case "Lineas":
//                basisgraf++;
//                System.out.println("seleccióno lineas");
//                if (basisgraf > grafico) {
//                    cbGrafType_DE.setDisable(true);
//                    btnOKGrafType_DE.setDisable(true);
//                }
//                grafictypes[0] = 1;
//                break;
//            case "Circular":
//                basisgraf++;
//                System.out.println("seleccióno circular");
//                if (basisgraf > grafico) {
//                    cbGrafType_DE.setDisable(true);
//                    btnOKGrafType_DE.setDisable(true);
//                }
//                grafictypes[1] = 1;
//                break;
//            case "Barras":
//                basisgraf++;
//                System.out.println("seleccióno barras");
//                if (basisgraf > grafico) {
//                    cbGrafType_DE.setDisable(true);
//                    btnOKGrafType_DE.setDisable(true);
//                }
//                grafictypes[2] = 1;
//                break;
//        }
//
//    }

//    private int CantidadGraficos() {
//        String type = cb_GrafCant_DE.getValue();
//
//        // Devuelve la cantidad de graficos que se quiere a un metodo.
//        switch (type) {
//            case "Un Grafico":
//                System.out.println("A");
//                return 1;
//            case "Dos Graficos":
//                System.out.println("B");
//                return 2;
//            case "Tres Graficos":
//                System.out.println("C");
//                return 3;
//            default:
//                return 0;
//        }
//
//    }

//    private void HabilitarNumGraficos() {
//        if (check_Graficos.isSelected()) {
//            cb_GrafCant_DE.setDisable(false);
//            btnOKGraf_Cant_DE.setDisable(false);
//        } else {
//            basisgraf = 1;
//            lblGrafCant.setText("");
//            cb_GrafCant_DE.setDisable(true);
//            btnOKGraf_Cant_DE.setDisable(true);
//        }
//
//    }

    private void TituloAnimacion(Label label) {
        label.setText("G");
        Timeline tm = new Timeline();
        tm.getKeyFrames().add(new KeyFrame(Duration.millis(100), (ActionEvent actionEvent) -> {
            switch (label.getText()) {
                case "G":
                    label.setText("Gr");
                    break;
                case "Gr":
                    label.setText("Gra");
                    break;
                case "Gra":
                    label.setText("Graf");
                    break;
                case "Graf":
                    label.setText("Grafi");
                    break;
                case "Grafi":
                    label.setText("Grafic");
                    break;
                case "Grafic":
                    label.setText("Grafico");
                    break;
                case "Grafico":
                    label.setText("Graficos");
                    break;
                case "Graficos":
                    label.setText("Graficos ");
                    break;
                case "Graficos ":
                    label.setText("Graficos E");
                    break;
                case "Graficos E":
                    label.setText("Graficos Es");
                    break;
                case "Graficos Es":
                    label.setText("Graficos Est");
                    break;
                case "Graficos Est":
                    label.setText("Graficos Esta");
                    break;
                case "Graficos Esta":
                    label.setText("Graficos Estad");
                    break;
                case "Graficos Estad":
                    label.setText("Graficos Estadi");
                    break;
                case "Graficos Estadi":
                    label.setText("Graficos Estadis");
                    break;
                case "Graficos Estadis":
                    label.setText("Graficos Estadist");
                    break;
                case "Graficos Estadist":
                    label.setText("Graficos Estadisti");
                    break;
                case "Graficos Estadisti":
                    label.setText("Graficos Estadistic");
                    break;
                case "Graficos Estadistic":
                    label.setText("Graficos Estadistico");
                    break;
                case "Graficos Estadistico":
                    label.setText("Graficos Estadisticos");
                    break;
                default:
                    break;
            }
        }));
        tm.setCycleCount(100);
        tm.play();
    }
    
    private void OperacionAgregar() throws Exception {
    	Date fechaHoy = new Date();
        Region nuevaRegion = new Region(txtRegion.getText());
        nuevaRegion.setPruebasMoleculares(Integer.parseInt(txtPruebasMoleculares.getText()));
        nuevaRegion.setPruebasRapidas(Integer.parseInt(txtPruebasRapidas.getText()));
        nuevaRegion.setTotalCasos(Integer.parseInt(txtCasosTotales.getText()));
        nuevaRegion.setLetalidad(Double.parseDouble(txtLetalidad.getText()));
        nuevaRegion.setFecha(fechaHoy);
        regionDAO.Agregar(nuevaRegion);
        regiones.add(nuevaRegion);
        cbRegion_Fallecidos.getItems().add(nuevaRegion.getNombre());
        alerta.setHeaderText(String.format(
                "Datos agregados:%nRegion %s%nPruebas Moleculares: %d%nPruebas Rapidas: %d%nCasos Totales: %d%n"
                + "Letalidad: %.2f%nFecha: %s",
                txtRegion.getText(), Integer.parseInt(txtPruebasMoleculares.getText()),
                Integer.parseInt(txtPruebasRapidas.getText()), Integer.parseInt(txtCasosTotales.getText()),
                Double.parseDouble(txtLetalidad.getText()), fechaHoy));
        LimpiarTextos();
        tvTDatos.getSelectionModel().clearSelection();
        setGrafico(grafico.RegionCasos());
    }

    @FXML
    private void AgregarEstadistica(ActionEvent event) throws Exception {
        if (tpRegion.isSelected()) {
            if (!txtRegion.getText().isEmpty() && !txtPruebasMoleculares.getText().isEmpty()
                    && !txtPruebasRapidas.getText().isEmpty() && !txtCasosTotales.getText().isEmpty()
                    && !txtLetalidad.getText().isEmpty()) {
            	if (tvTDatos.getSelectionModel().isEmpty()) {
            		OperacionAgregar();
            	} else if (!txtRegion.getText().equals(tvTDatos.getSelectionModel().getSelectedItem().getNombre())) {
            		OperacionAgregar();
            	} else {
            		alerta.setHeaderText("Agregue un departamento diferente o edite la estadistica de uno");
            	}
            } else {
                alerta.setHeaderText("Faltan valores por llenar");
            }

        } else if (tpFallecidos.isSelected()) {
            if (!cbEtapa_Fallecidos.getSelectionModel().isEmpty() && !cbSexo_Fallecidos.getSelectionModel().isEmpty()
                    && !cbRegion_Fallecidos.getSelectionModel().isEmpty() && !cbCategoria_Fallecidos.getSelectionModel().isEmpty()) {
                FallecidoPK clave = new FallecidoPK(0, cbRegion_Fallecidos.getValue());
                Fallecido nuevoFallecido = new Fallecido(clave, cbEtapa_Fallecidos.getValue(),
                        cbSexo_Fallecidos.getValue(), cbCategoria_Fallecidos.getValue());
                nuevoFallecido.setRegion1(regionDAO.Encontrar(cbRegion_Fallecidos.getValue()));
                fallecidoDAO.Agregar(nuevoFallecido);
                fallecidos.add(nuevoFallecido);
                alerta.setHeaderText(String.format("Datos agregados:%nRegion: %s%n- Etapa: %s%n- Sexo: %s%n- Categoria: %s", cbRegion_Fallecidos.getSelectionModel().getSelectedItem(),
                        cbEtapa_Fallecidos.getSelectionModel().getSelectedItem(), cbSexo_Fallecidos.getSelectionModel().getSelectedItem(),
                        cbCategoria_Fallecidos.getSelectionModel().getSelectedItem()));
                LimpiarCB();
                tvTDatos1.getSelectionModel().clearSelection();
                setGraficoMuertos(grafico.RegionMuertos());
                setOtrosGraficos(grafico.MuertosSexo(), grafico.MuertoEtapa());
            } else {
                alerta.setHeaderText("Faltan seleccionar valores");
            }
        }
        alerta.show();

    }

    @FXML
    private void EliminarEstadistica(ActionEvent event) throws Exception {
        if (tpRegion.isSelected()) {
            if (!tvTDatos.getSelectionModel().isEmpty()) {
                String nombre = tvTDatos.getSelectionModel().getSelectedItem().getNombre();
                regionDAO.Eliminar(tvTDatos.getSelectionModel().getSelectedItem().getNombre());
                regiones.remove(tvTDatos.getSelectionModel().getSelectedItem());
                alerta.setHeaderText(String.format("Se elimino la estadistica de la region %s", nombre));
                LimpiarTextos();
                tvTDatos.getSelectionModel().clearSelection();
                setGrafico(grafico.RegionCasos());
            } else {
                alerta.setHeaderText("Seleccione una estadistica de la lista");
            }
        } else if (tpFallecidos.isSelected()) {
            if (!tvTDatos1.getSelectionModel().isEmpty()) {
                int id = tvTDatos1.getSelectionModel().getSelectedItem().getFallecidoPK().getNrofallecido();
                fallecidoDAO.Eliminar(tvTDatos1.getSelectionModel().getSelectedItem().getFallecidoPK());
                fallecidos.remove(tvTDatos1.getSelectionModel().getSelectedItem());
                alerta.setHeaderText(String.format("Se elimino al fallecido con ID %d", id));
                LimpiarCB();
                tvTDatos1.getSelectionModel().clearSelection();
                setGraficoMuertos(grafico.RegionMuertos());
                setOtrosGraficos(grafico.MuertosSexo(), grafico.MuertoEtapa());
            } else {
                alerta.setHeaderText("Seleccione una estadistica de la lista");
            }
        }
        alerta.show();

    }

    @FXML
    private void EditarEstadistica(ActionEvent event) throws Exception {
        if (tpRegion.isSelected()) {
            if (!txtRegion.getText().isEmpty() && !tvTDatos.getSelectionModel().isEmpty()) {
                Region nuevaRegion = new Region(tvTDatos.getSelectionModel().getSelectedItem().getNombre());
                String region = tvTDatos.getSelectionModel().getSelectedItem().getNombre();
                int pruebasM = tvTDatos.getSelectionModel().getSelectedItem().getPruebasMoleculares();
                int pruebasR = tvTDatos.getSelectionModel().getSelectedItem().getPruebasRapidas();
                int totalC = tvTDatos.getSelectionModel().getSelectedItem().getTotalCasos();
                double letalidad = tvTDatos.getSelectionModel().getSelectedItem().getLetalidad();
                nuevaRegion.setPruebasMoleculares(Integer.parseInt(txtPruebasMoleculares.getText()));
                nuevaRegion.setPruebasRapidas(Integer.parseInt(txtPruebasRapidas.getText()));
                nuevaRegion.setTotalCasos(Integer.parseInt(txtCasosTotales.getText()));
                nuevaRegion.setLetalidad(Double.parseDouble(txtLetalidad.getText()));
                nuevaRegion.setFecha(tvTDatos.getSelectionModel().getSelectedItem().getFecha());
                regionDAO.Editar(nuevaRegion);
                regiones.set(tvTDatos.getSelectionModel().getSelectedIndex(), nuevaRegion);
                alerta.setHeaderText(String.format(
                        "Estadisticas de la Region %s modificada a:%n- Pruebas Moleculares: De %s a %s%n- Pruebas Rapidas: De %s a %s%n- Total Casos: De %s a %s%n- Letalidad: De %s a %s%n",
                        region, pruebasM, txtPruebasMoleculares.getText(), pruebasR, txtPruebasRapidas.getText(),
                        totalC, txtCasosTotales.getText(), letalidad, txtLetalidad.getText()));
                LimpiarTextos();
                tvTDatos.getSelectionModel().clearSelection();
                setGrafico(grafico.RegionCasos());
            } else {
                alerta.setHeaderText("Faltan valores por rellenar o no esta seleccionado ninguna estadistica");
            }
        } else if (tpFallecidos.isSelected()) {
            if (!cbRegion_Fallecidos.getSelectionModel().isEmpty() && !tvTDatos1.getSelectionModel().isEmpty()) {

                int id = tvTDatos1.getSelectionModel().getSelectedItem().getFallecidoPK().getNrofallecido();
                String sexo = tvTDatos1.getSelectionModel().getSelectedItem().getSexo();
                String categoria = tvTDatos1.getSelectionModel().getSelectedItem().getCategoria();
                String etapa = tvTDatos1.getSelectionModel().getSelectedItem().getEtapa();
                String region = tvTDatos1.getSelectionModel().getSelectedItem().getRegion1().getNombre();
                if (region.equals(cbRegion_Fallecidos.getSelectionModel().getSelectedItem())) {
                    Fallecido nuevoFallecido = new Fallecido(tvTDatos1.getSelectionModel().getSelectedItem().getFallecidoPK());
                    nuevoFallecido.setSexo(cbSexo_Fallecidos.getSelectionModel().getSelectedItem());
                    nuevoFallecido.setCategoria(cbCategoria_Fallecidos.getSelectionModel().getSelectedItem());
                    nuevoFallecido.setRegion1(regionDAO.Encontrar(cbRegion_Fallecidos.getSelectionModel().getSelectedItem()));
                    nuevoFallecido.setEtapa(cbEtapa_Fallecidos.getSelectionModel().getSelectedItem());
                    fallecidoDAO.Editar(nuevoFallecido);
                    fallecidos.set(tvTDatos1.getSelectionModel().getSelectedIndex(), nuevoFallecido);
                    alerta.setHeaderText(String.format("Datos del fallecido con ID %d de la region %s modificados a:%n- Etapa: De %s a %s%n"
                            + "- Sexo: De %s a %s%n- Categoria: De %s a %s", id, region, etapa, cbEtapa_Fallecidos.getSelectionModel().getSelectedItem(),
                            sexo, cbSexo_Fallecidos.getSelectionModel().getSelectedItem(), categoria, cbCategoria_Fallecidos.getSelectionModel().getSelectedItem()));
                    LimpiarCB();
                    tvTDatos1.getSelectionModel().clearSelection();
                    setGraficoMuertos(grafico.RegionMuertos());
                    setOtrosGraficos(grafico.MuertosSexo(), grafico.MuertoEtapa());
                } else {
                    alerta.setHeaderText("Region cambiada");
                }

            } else {
                alerta.setHeaderText("Faltan valores por escoger o no esta seleccionado ningun dato");
            }
        }
        alerta.show();

    }

    @FXML
    private void ListarEstadisticas(ActionEvent event) throws Exception {
        if (tpRegion.isSelected()) {
            regiones = FXCollections.observableArrayList(regionDAO.Listar());
            LimpiarTextos();
            ListarTabla();
        } else if (tpFallecidos.isSelected()) {
            fallecidos = FXCollections.observableArrayList(fallecidoDAO.Listar());
            LimpiarCB();
            ListarTablaF();
        }
    }

    @FXML
    private void SeleccionarEstadistica(ActionEvent event) throws Exception {
        if (tpRegion.isSelected()) {
            if (!txtRegion.getText().isEmpty()) {
                String nombre = tvTDatos.getSelectionModel().getSelectedItem().getNombre();
                regiones.removeAll(regiones);
                regiones.add(regionDAO.Encontrar(nombre));
                ListarTabla();
                LimpiarTextos();
            }
        } else if (tpFallecidos.isSelected()) {
            if (!tvTDatos1.getSelectionModel().isEmpty()) {
                FallecidoPK clave = tvTDatos1.getSelectionModel().getSelectedItem().getFallecidoPK();
                fallecidos.removeAll(fallecidos);
                fallecidos.add(fallecidoDAO.Encontrar(clave));
                ListarTablaF();
                LimpiarCB();
            }
        }

    }

}
