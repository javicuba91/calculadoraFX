package consultas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelos.Persona;

public class ConsultasController {

	public static ArrayList<Persona> listarPersonas() {

		ArrayList<Persona> personas = new ArrayList<>();

		String nombre_fichero = "personas.txt";
		File file = new File(nombre_fichero);
		FileReader fr = null;
		BufferedReader br = null;

		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String linea = "";

			while ((linea = br.readLine()) != null) {

				String[] personasTxt = linea.split(",");

				Persona p = new Persona(Integer.parseInt(personasTxt[0]), personasTxt[1], personasTxt[2],
						Integer.parseInt(personasTxt[3]));

				personas.add(p);
			}

		} catch (Exception a) {
			// TODO: handle exception
		}

		return personas;
	}

	public static void guardarPersonaFichero(Persona persona) {
		String nombre_fichero = "personas.txt";

		File file = new File(nombre_fichero);

		String linea = "";

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			linea = persona.getId() + "," + persona.getNombre() + "," + persona.getCargo() + "," + persona.getEdad();
			out.println(linea);

			JOptionPane.showMessageDialog(null, "Persona guardado con éxito");

		} catch (IOException exception) {
			// exception handling left as an exercise for the reader
		}
	}

}
