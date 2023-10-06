package consultas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import modelos.Persona;

public class ConsultasController {
	
	public static ArrayList<Persona> listarPersonas(){
		
		ArrayList<Persona> personas = new ArrayList<>();
		
		String nombre_fichero = "personas.txt";
		File file = new File(nombre_fichero);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String linea = "";
			
			while((linea = br.readLine()) != null) {
				
				String[] personasTxt = linea.split(",");
				
				Persona p = new Persona(Integer.parseInt(personasTxt[0]), personasTxt[1], personasTxt[2], Integer.parseInt(personasTxt[3]));
				
				personas.add(p);
			}
			
						
		}catch (Exception a) {
			// TODO: handle exception
		}
		
		
		return personas;
	}

}
