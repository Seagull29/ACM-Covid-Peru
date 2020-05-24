package proyecto2daparte.formularios.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import proyecto2daparte.entidades.Fallecido;
import proyecto2daparte.entidades.Region;
import proyecto2daparte.entidades.entidadesDAO.FallecidoDAO;
import proyecto2daparte.entidades.entidadesDAO.RegionDAO;

public class Graficos {

	private final RegionDAO regionDAO = new RegionDAO();
	private final FallecidoDAO fallecidoDAO = new FallecidoDAO();
	
	public Graficos() {
		
	}
	
	private int CantidadMuertos(String region) throws Exception {
		int cantidad = 0;
		for (Fallecido fallecido : fallecidoDAO.Listar()) {
			if (fallecido.getFallecidoPK().getRegion().equals(region)) {
				cantidad += 1;
			}
 		}
		return cantidad;
	}
	
	private int [] CantidadporSexo() throws Exception {
		int [] cantidad = {0, 0};
		for (Fallecido fallecido : fallecidoDAO.Listar()) {
			if (fallecido.getSexo().equals("Masculino")) {
				cantidad[0] += 1;
			} else if (fallecido.getSexo().equals("Femenino")) {
				cantidad[1] += 1;
			}
 		}
		return cantidad;
	}
	
	private int [] CantidadporEtapa() throws Exception {
		int [] cantidad = {0, 0, 0, 0, 0};
		for (Fallecido fallecido : fallecidoDAO.Listar()) {
			if (fallecido.getEtapa().equals("Adulto Mayor")) {
				cantidad[0] += 1;
			} else if (fallecido.getEtapa().equals("Adulto")) {
				cantidad[1] += 1;
			} else if (fallecido.getEtapa().equals("Joven")) {
				cantidad[2] += 1;
			} else if (fallecido.getEtapa().equals("Nino")) {
				cantidad[3] += 1;
			} else if (fallecido.getEtapa().equals("Asolescente")) {
				cantidad[4] += 1;
			}
 		}
		return cantidad;
	}
	
	private void ToolTip(PieChart grafico) {
		double total = 0;
		
		for (PieChart.Data valor : grafico.getData()) {
			total += valor.getPieValue();
		}
		
		for (PieChart.Data valor : grafico.getData()) {
			int valorPedazo =  (int)valor.getPieValue();
			double porcentaje = (valorPedazo / total) * 100;
			String mensaje = String.format("%d (%.2f", valorPedazo, porcentaje) + "%)";
			Tooltip tip = new Tooltip(mensaje);
			Tooltip.install((Node)valor.getNode(), tip);
		}
	}
	
	
	public Node RegionCasos() throws Exception {
		NumberAxis ejeY = new NumberAxis();
		CategoryAxis ejeX = new CategoryAxis();
		ejeY.setLabel("Cantidad de infectados");
		ejeX.setLabel("Regiones");
		XYChart.Series<String, Number> serieInfec = new XYChart.Series<>();
		serieInfec.setName("Total de Casos");
		for (Region region : regionDAO.Listar()) {
			serieInfec.getData().add(new XYChart.Data<>(region.getNombre(), region.getTotalCasos()));
 		}
		ObservableList<XYChart.Series<String, Number>> datos = FXCollections.observableArrayList();
		datos.add(serieInfec);
		
		BarChart<String, Number> bcBarras = new BarChart<String, Number>(ejeX, ejeY, datos);
		bcBarras.setAnimated(true);
		bcBarras.setTitle("Cantidad de casos confirmados por region");
		bcBarras.setPrefHeight(600);
		bcBarras.setPrefWidth(1000);
		return new Group(bcBarras);
	}
	
	public Node RegionMuertos() throws Exception {
		NumberAxis ejeY = new NumberAxis();
		CategoryAxis ejeX = new CategoryAxis();
		ejeY.setLabel("Cantidad de muertos");
		ejeX.setLabel("Regiones");
		XYChart.Series<String, Number> seriesMuertos = new XYChart.Series<>();
		seriesMuertos.setName("Total de muertos");
		for (Region region : regionDAO.Listar()) {
			seriesMuertos.getData().add(new XYChart.Data<>(region.getNombre(), CantidadMuertos(region.getNombre())));
 		}
		ObservableList<XYChart.Series<String, Number>> datos = FXCollections.observableArrayList();
		datos.add(seriesMuertos);
		
		BarChart<String, Number> bcBarras = new BarChart<String, Number>(ejeX, ejeY, datos);
		bcBarras.setAnimated(true);
		bcBarras.setTitle("Cantidad de muertos por region");
		bcBarras.setPrefHeight(600);
		bcBarras.setPrefWidth(1000);
		return new Group(bcBarras);
	}
	
	public Node MuertosSexo() throws Exception {
		int [] cantidad = CantidadporSexo();
		ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
		datos.addAll(new PieChart.Data("Masculino", cantidad[0]), new PieChart.Data("Femenino", cantidad[1]));
		PieChart pcMuertosSexo = new PieChart(datos);
		pcMuertosSexo.setTitle("Fallecidos segun Sexo");
		pcMuertosSexo.setLegendSide(Side.LEFT);
		pcMuertosSexo.setLabelLineLength(20);
		pcMuertosSexo.setPrefSize(500, 500);
        ToolTip(pcMuertosSexo);
		return new Group(pcMuertosSexo);
	}
	
	public Node MuertoEtapa() throws Exception {
		int [] cantidad = CantidadporEtapa();
		ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
		datos.addAll(new PieChart.Data("Adulto Mayor", cantidad[0]), new PieChart.Data("Adulto", cantidad[1]), 
				new PieChart.Data("Joven", cantidad[2]), new PieChart.Data("Nino", cantidad[3]), 
						new PieChart.Data("Adolescente", cantidad[4]));
		PieChart pcMuertosEtapa = new PieChart(datos);
		pcMuertosEtapa.setTitle("Fallecidos segun Etapa");
		pcMuertosEtapa.setLegendSide(Side.LEFT);
		pcMuertosEtapa.setLabelLineLength(20);
		pcMuertosEtapa.setPrefSize(550, 550);
		ToolTip(pcMuertosEtapa);
		return new Group(pcMuertosEtapa);
	}
 	
}
