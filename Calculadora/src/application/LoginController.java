package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.Action;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField textUsuario;	

	@FXML
	private TextField textPass;
	
	@FXML
	private Button btnAcceder;
	
	private String usuario = "";
	private String pass = "";
	
	public void leerDatosAcceso() {
		
		String nombre_fichero = "acceso.txt";
		File file = new File(nombre_fichero);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String linea = "";
			
			linea = br.readLine();	
			
			String[] accesos = linea.split(",");
			
			usuario = accesos[0];
			pass = accesos[1];
						
		}catch (Exception a) {
			// TODO: handle exception
		}
	}
	
	public void acceder(ActionEvent e) {
		
		leerDatosAcceso();
		
		String valorUsuario = textUsuario.getText();
		String valorPass = textPass.getText();
		
		if(valorUsuario.equalsIgnoreCase(usuario) && 
				valorPass.equalsIgnoreCase(pass)) {
			
			try {
				
				// cargamos el fxml
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("Calculadora.fxml"));
				
				// creamos la escena y asociamos el fxml
				Scene scene = new Scene(fxmlLoader.load());
				
				// creamos el stage
				Stage stage = new Stage();
				stage.setTitle("Calculadora");				
				
				// asociamos la escena al stage
				stage.setScene(scene);
				
				// abrimos la nueva ventana
				stage.show();
				
			}catch (Exception a) {
				// TODO: handle exception
			}
			
			
		}else{			
			JOptionPane.showMessageDialog(null, "ERROR de usuario y/o contraseña !!");			
		}
		
	}

}
