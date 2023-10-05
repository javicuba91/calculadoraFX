package application;

import javax.swing.Action;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField textUsuario;	

	@FXML
	private TextField textPass;
	
	@FXML
	private Button btnAcceder;
	
	private String usuario = "admin";
	private String pass = "dam2023";
	
	public void acceder(ActionEvent e) {
		
		String valorUsuario = textUsuario.getText();
		String valorPass = textPass.getText();
		
		if(valorUsuario.equalsIgnoreCase(usuario) && 
				valorPass.equalsIgnoreCase(pass)) {		
			
			
		}else{			
			JOptionPane.showMessageDialog(null, "ERROR de usuario y/o contraseña !!");			
		}
		
	}

}
