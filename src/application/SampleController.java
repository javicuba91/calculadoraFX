package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private Label textoLabel;
	
	@FXML
	private DatePicker fecha;
	
	@FXML
	private TextField nombreTxt;
	
	@FXML
	private TextArea comentario;
	
	@FXML
	public void cambiarTexto(ActionEvent e) {
		String fec = fecha.getValue().toString();
		String nombre = nombreTxt.getText();
		String com = comentario.getText();
		textoLabel.setText("Nombre: " + nombre +", Fecha: " + fec +", Comentario: " + com);
		
		nombreTxt.setText("");
		comentario.setText("");
		
		Persona p = new Persona(nombre, fec, com);
		System.out.println(p.toString());
	}
	
	
}
