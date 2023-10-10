package application;

import consultas.ConsultasController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.Persona;

public class ListadosController {

	@FXML
	private TableView<Persona> tablaPersonas;

	@FXML
	private void initialize() {

		// crear las columnas de la tabla según el tipo de dato e los atributos
		TableColumn<Persona, Integer> columnaID = new TableColumn<>("ID");
		columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Persona, String> columnaNombre = new TableColumn<>("Nombre");
		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		TableColumn<Persona, String> columnaCargo = new TableColumn<>("Cargo");
		columnaCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));

		TableColumn<Persona, Integer> columnaEdad = new TableColumn<>("Edad");
		columnaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

		// Añadir las columnas a la tabla
		tablaPersonas.getColumns().addAll(columnaID, columnaNombre, columnaCargo, columnaEdad);

		ObservableList<Persona> personList = FXCollections.observableArrayList(new Persona(1, "Alice", "Alumno", 25),
				new Persona(2, "Bob", "Alumno", 30), new Persona(3, "Charlie", "Alumno", 22));

		tablaPersonas.setItems(personList);

	}

}
