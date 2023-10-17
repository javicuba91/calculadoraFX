package application;



import consultas.ConsultasController;
import modelos.Coche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CochesController {

	@FXML
	private TableView<Coche> tableCoches;

	@FXML
	private void initialize() {
		// crear las columnas de la tabla según el tipo de dato e los atributos
		TableColumn<Coche, String> columnaMatricula = new TableColumn<>("Matricula");
		columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

		TableColumn<Coche, String> columnaColor = new TableColumn<>("Color");
		columnaColor.setCellValueFactory(new PropertyValueFactory<>("color"));

		TableColumn<Coche, String> columnaModelo = new TableColumn<>("Modelo");
		columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

		TableColumn<Coche, String> columnaAñoFabricacion = new TableColumn<>("añoFabricacion");
		columnaAñoFabricacion.setCellValueFactory(new PropertyValueFactory<>("añoFabricacion"));

		TableColumn<Coche, String> columnaNombreDueño = new TableColumn<>("nombreDueño");
		columnaNombreDueño.setCellValueFactory(new PropertyValueFactory<>("nombreDueño"));

		// Añadir las columnas a la tabla
		tableCoches.getColumns().addAll(columnaMatricula, columnaColor, columnaModelo, columnaAñoFabricacion, columnaNombreDueño);
		ObservableList<Coche> cocheList = FXCollections.observableArrayList(ConsultasController.listarCoches());
		tableCoches.setItems(cocheList);

		tableCoches.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				System.out.println(newSelection.toString());
			}
		});
	}
}