package application;

import consultas.ConsultasController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.Persona;

public class ListadosController {

	@FXML
	private TableView<Persona> tablaPersonas;
	
	@FXML
	private Button btnAnyadir;

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

		ObservableList<Persona> personList = FXCollections.observableArrayList(ConsultasController.listarPersonas());

		tablaPersonas.setItems(personList);
		
		// evento click sobre filas de una tabla
		tablaPersonas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		       System.out.println(newSelection.getNombre());
		    }
		});
	}
	
	public void insertarPersona(ActionEvent e) {
		Persona p = new Persona(8, "Jonathan", "Profesor", 32);
		
		
		
	}

}
