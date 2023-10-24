package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import consultas.ConsultasController;
import modelos.Coche;
import modelos.Persona;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
	private MenuBar menuBar;

	@FXML
	private TextField propietarioTxt;

	@FXML
	private Button editarBtn;

	@FXML
	private Button eliminarBtn;

	@FXML
	private Button limpiarTablaBtn;

	@FXML
	private MenuItem itemCerrar;

	@FXML
	private Button borrarCriteriosBtn;

	@FXML
	private Button buscarBtn;

	@FXML
	private TextField buscarColorTxt;

	private int posFilaSeleccionada = -1;

	// Buscador por fechas

	@FXML
	private Button borrarCriteriosFechasBtn;

	@FXML
	private Button buscarFechasBtn;

	@FXML
	private TextField fechaDesdeTxt;

	@FXML
	private TextField fechaHastaTxt;

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

		if (verificarCamposVacios() == false) {
			boolean formatoMatricula = ConsultasController.verificarFormatoMatricula(matriculaTxt.getText());
			if (formatoMatricula == true) {

				String matricula = matriculaTxt.getText();

				if (ConsultasController.verificarMatricula(matricula) == false) {

					String color = colorTxt.getText();
					String modelo = modeloTxt.getText();
					String anyoFab = anyoFabTxt.getText();
					String propietario = propietarioTxt.getText();

					int anyoFabNum = Integer.parseInt(anyoFab);
					GregorianCalendar fechaActual = new GregorianCalendar();

					if (anyoFabNum <= fechaActual.get(Calendar.YEAR)) {
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
					} else {
						JOptionPane.showMessageDialog(null,
								"El año de fabricación no puede ser mayor que el año actual. Revise por favor !!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "La matrícula ya existe. Revise por favor !!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "El formato de la matrícula no es correcto. Revise por favor !!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Existen campos vacíos. Revise por favor !!");
		}

	}

	@FXML
	void editar(ActionEvent event) {

		if (posFilaSeleccionada >= 0) {
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

			posFilaSeleccionada = -1;
		} else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar una fila para poder editar. Revise por favor !!");
		}

	}

	@FXML
	void eliminar(ActionEvent event) {

		if (posFilaSeleccionada >= 0) {
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

			posFilaSeleccionada = -1;
		} else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar una fila para poder eliminar. Revise por favor !!");
		}

	}

	@FXML
	void limpiar(ActionEvent event) {
		ConsultasController.borrarFichero();
		ArrayList<Coche> coches_nuevos = ConsultasController.listarCoches();
		ObservableList<Coche> data = FXCollections.observableArrayList(coches_nuevos);
		tableCoches.setItems(data);
	}

	@FXML
	void cerrarAplicacion(ActionEvent event) {
		Platform.exit();
	}

	public boolean verificarCamposVacios() {
		boolean vacio = false;

		if (matriculaTxt.getText().equalsIgnoreCase("") || colorTxt.getText().equalsIgnoreCase("")
				|| modeloTxt.getText().equalsIgnoreCase("") || anyoFabTxt.getText().equalsIgnoreCase("")
				|| propietarioTxt.getText().equalsIgnoreCase("")) {

			vacio = true;

		}

		return vacio;
	}

	@FXML
	void borrarCriterios(ActionEvent event) {
		buscarColorTxt.setText("");
		ArrayList<Coche> coches = ConsultasController.listarCoches();
		ObservableList<Coche> data = FXCollections.observableArrayList(coches);
		tableCoches.setItems(data);
	}

	@FXML
	void buscarPorColor(ActionEvent event) {

		if (!buscarColorTxt.getText().equalsIgnoreCase("")) {

			String color = buscarColorTxt.getText();

			ArrayList<Coche> coches = ConsultasController.listaCochesBusqueda(color);

			if (coches.size() > 0) {

				ObservableList<Coche> data = FXCollections.observableArrayList(coches);
				tableCoches.setItems(data);

			} else {
				coches = new ArrayList<>();
				JOptionPane.showMessageDialog(null,
						"No existen coches con el color: " + color + ". Revise por favor !!");
				ObservableList<Coche> data = FXCollections.observableArrayList(coches);
				tableCoches.setItems(data);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Para buscar debes escribir un criterio. Revise por favor !!");
		}

	}

	@FXML
	void borrarCriteriosFechas(ActionEvent event) {
		ArrayList<Coche> coches = ConsultasController.listarCoches();
		ObservableList<Coche> data = FXCollections.observableArrayList(coches);
		tableCoches.setItems(data);
		
		fechaDesdeTxt.setText("");
		fechaHastaTxt.setText("");
	}

	@FXML
	void buscarPorFechas(ActionEvent event) {

		if(!fechaDesdeTxt.getText().equalsIgnoreCase("") && 
				!fechaHastaTxt.getText().equalsIgnoreCase("")) {
			
			int fechaDesde = Integer.parseInt(fechaDesdeTxt.getText());
			int fechaHasta = Integer.parseInt(fechaHastaTxt.getText());
			
			if(fechaDesde < fechaHasta) {
				
				ArrayList<Coche> coches = ConsultasController.listaCochesFecha(fechaDesde, fechaHasta);
				ObservableList<Coche> data = FXCollections.observableArrayList(coches);
				tableCoches.setItems(data);
				
				fechaDesdeTxt.setText("");
				fechaHastaTxt.setText("");
				
			}else {
				JOptionPane.showMessageDialog(null, "La Fecha Desde debe ser menor que la Fecha Hasta. Revise por favor !!");
			}
			
		}else {
			ArrayList<Coche> coches = ConsultasController.listarCoches();
			ObservableList<Coche> data = FXCollections.observableArrayList(coches);
			tableCoches.setItems(data);
		}
		
	}
	
	@FXML
    void exportar(ActionEvent event) {
		// acción de leer un valor de un JOption Pane				
		String nombre_fichero = JOptionPane.showInputDialog(null, "Entre el nombre del fichero a guardar");
				
		// Lista de coches de la tabla actual
		ArrayList<Coche> coches = new ArrayList<>(tableCoches.getItems());
		
		// Invocar al método de guardar en  el fichero
		ConsultasController.guardarCochesFicheroCriterios(nombre_fichero, coches);
		
		JOptionPane.showMessageDialog(null, "Coches exportados con éxito!!");
    }

}