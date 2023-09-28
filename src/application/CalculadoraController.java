package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class CalculadoraController {

	@FXML
	private Button btn0;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private Button btn9;

	@FXML
	private TextArea resultado;

	@FXML
	private Button borrar;

	@FXML
	private Button btnIgual;

	@FXML
	private Button btnDivision;

	@FXML
	private Button btnMultiplicacion;

	@FXML
	private Button btnResta;

	@FXML
	private Button btnMas;

	private String operacion = "";

	private char operador = 'x';

	private double op1 = 0;

	private double op2 = 0;

	private double resFinal = 0;

	public void clickBtn0(ActionEvent e) {
		operacion += "" + 0;
		resultado.setText(""+operacion);
	}

	public void clickBtn1(ActionEvent e) {
		operacion += "" + 1;
		resultado.setText(""+operacion);
	}

	public void clickBtn2(ActionEvent e) {
		operacion += "" + 2;
		resultado.setText(""+operacion);
	}

	public void clickBtn3(ActionEvent e) {
		operacion += "" + 3;
		resultado.setText(""+operacion);
	}

	public void clickBtn4(ActionEvent e) {
		operacion += "" + 4;
		resultado.setText(""+operacion);
	}

	public void clickBtn5(ActionEvent e) {
		operacion += "" + 5;
		resultado.setText(""+operacion);
	}

	public void clickBtn6(ActionEvent e) {
		operacion += "" + 6;
		resultado.setText(""+operacion);
	}

	public void clickBtn7(ActionEvent e) {
		operacion += "" + 7;
		resultado.setText(""+operacion);
	}

	public void clickBtn8(ActionEvent e) {
		operacion += "" + 8;
		resultado.setText(""+operacion);
	}

	public void clickBtn9(ActionEvent e) {
		operacion += "" + 9;
		resultado.setText(""+operacion);
	}

	public void clickBtnDivision(ActionEvent e) {
		operacion += "/";
		resultado.setText(""+operacion);
	}

	public void clickBtnResta(ActionEvent e) {
		operacion += "-";
		resultado.setText(""+operacion);
	}

	public void clickBtnMas(ActionEvent e) {
		operacion += "+";
		resultado.setText(""+operacion);
	}

	public void clickBtnMultiplicacion(ActionEvent e) {
		operacion += "*";
		resultado.setText(""+operacion);
	}

	public void clickBtnIgual(ActionEvent e) {

		if (operacion.contains("+")) {
			operador = '+';
		} else if (operacion.contains("-")) {
			operador = '-';
		} else if (operacion.contains("*")) {
			operador = '*';
		} else if (operacion.contains("/")) {
			operador = '/';
		} else {
			System.out.println("Debe contener un operador !!!");
		}

		String[] operadores = operacion.split("\\"+operador);
		
		System.out.println(Arrays.toString(operadores));

		op1 = Double.parseDouble(operadores[0]);
		op2 = Double.parseDouble(operadores[1]);
		
		switch (operador) {
			case '+': {
				resFinal = op1 + op2;
				break;
			}
			case '-': {
				resFinal = op1 - op2;
				break;
			}
			case '*': {
				resFinal = op1 * op2;
				break;
			}
			case '/': {
				resFinal = op1 / op2;
				break;
			}
		}
		
		resultado.setText(""+resFinal);
		//op1 = resFinal;
		operacion = ""+resFinal;
	}

	public void clickBorrar(ActionEvent e) {
		resultado.setText("0");
		operacion = "";
	}
}
