package application;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import consultas.ConsultasController;
import modelos.Coche;
import modelos.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CochesController {

	@FXML
	private TableView<Coche> tableCoches;

	@FXML
	private TextField anyoFabTxt;

	@FXML
	private TextField colorTxt;

	@FXML
	private Button insertarBtn;

	@FXML
	private TextField matriculaTxt;

	@FXML
	private TextField modeloTxt;

	@FXML
	private TextField propietarioTxt;

	@FXML
	private Button editarBtn;

	@FXML
	private Button eliminarBtn;

	private int posFilaSeleccionada = 0;

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
		tableCoches.getColumns().addAll(columnaMatricula, columnaColor, columnaModelo, columnaAñoFabricacion,
				columnaNombreDueño);
		ObservableList<Coche> cocheList = FXCollections.observableArrayList(ConsultasController.listarCoches());
		tableCoches.setItems(cocheList);

		tableCoches.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// JOptionPane.showMessageDialog(null, newSelection.toString() );
				editarBtn.setVisible(true);
				eliminarBtn.setVisible(true);

				TablePosition<?, ?> filaSeleccionada = tableCoches.getSelectionModel().getSelectedCells().get(0);
				int posFila = filaSeleccionada.getRow();

				posFilaSeleccionada = posFila;

				matriculaTxt.setText(newSelection.getMatricula());
				colorTxt.setText(newSelection.getColor());
				modeloTxt.setText(newSelection.getModelo());
				anyoFabTxt.setText(newSelection.getAñoFabricacion());
				propietarioTxt.setText(newSelection.getNombreDueño());

			}
		});
	}

	@FXML
	public void insertarCoche(ActionEvent event) {

		String matricula = matriculaTxt.getText();
		String color = colorTxt.getText();
		String modelo = modeloTxt.getText();
		String anyoFab = anyoFabTxt.getText();
		String propietario = propietarioTxt.getText();

		Coche coche = new Coche(matricula, color, modelo, anyoFab, propietario);

		ConsultasController.guardarCocheFichero(coche);

		ArrayList<Coche> coches = ConsultasController.listarCoches();

		ObservableList<Coche> data = FXCollections.observableArrayList(coches);

		tableCoches.setItems(data);

		matriculaTxt.setText("");
		colorTxt.setText("");
		modeloTxt.setText("");
		anyoFabTxt.setText("");
		propietarioTxt.setText("");
	}

	@FXML
	void editar(ActionEvent event) {

		String matricula = matriculaTxt.getText();
		String color = colorTxt.getText();
		String modelo = modeloTxt.getText();
		String anyoFab = anyoFabTxt.getText();
		String propietario = propietarioTxt.getText();

		Coche coche = new Coche(matricula, color, modelo, anyoFab, propietario);

		ObservableList<Coche> filas = tableCoches.getItems();
		filas.set(posFilaSeleccionada, coche);

		// guardamos la nueva lista conn la fila elimianda
		ArrayList<Coche> coches = new ArrayList<>(tableCoches.getItems());
		ConsultasController.guardarCochesFichero(coches);

		// cargamos de nuevo la lista de coches
		ArrayList<Coche> coches_nuevos = ConsultasController.listarCoches();

		ObservableList<Coche> data = FXCollections.observableArrayList(coches_nuevos);

		tableCoches.setItems(data);
		
		matriculaTxt.setText("");
		colorTxt.setText("");
		modeloTxt.setText("");
		anyoFabTxt.setText("");
		propietarioTxt.setText("");
	}

	@FXML
	void eliminar(ActionEvent event) {

		// eliminar la fila seleccionada
		ObservableList<Coche> filas = tableCoches.getItems();

		if (posFilaSeleccionada < filas.size()) {
			filas.remove(posFilaSeleccionada);
		}

		// guardamos la nueva lista conn la fila elimianda
		ArrayList<Coche> coches = new ArrayList<>(tableCoches.getItems());
		ConsultasController.guardarCochesFichero(coches);

		// cargamos de nuevo la lista de coches
		ArrayList<Coche> coches_nuevos = ConsultasController.listarCoches();

		ObservableList<Coche> data = FXCollections.observableArrayList(coches_nuevos);

		tableCoches.setItems(data);
		
		matriculaTxt.setText("");
		colorTxt.setText("");
		modeloTxt.setText("");
		anyoFabTxt.setText("");
		propietarioTxt.setText("");

	}
}