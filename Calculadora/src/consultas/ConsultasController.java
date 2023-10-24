package consultas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import modelos.Coche;
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
	
	public static void eliminarPersonaFichero(String lineaBuscar) {
		
		String nombre_fichero = "personas.txt";
		File file = new File(nombre_fichero);
		
		FileReader fr = null;
		BufferedReader br = null;
		
		ArrayList<String> personas = new ArrayList<>();
		
		try {
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String linea = "";
			
			while((linea = br.readLine()) != null) {
				if(!linea.trim().equals(lineaBuscar)) {
					personas.add(linea);					
				}
			}
			
			br.close();
			fr.close();
			
		}catch (Exception a) {
			// TODO: handle exception
		}
		
		System.out.println(personas.size());
		
		
		try (FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			
			for (int i = 0; i < personas.size(); i++) {
				out.println(personas.get(i));
			}

			JOptionPane.showMessageDialog(null, "Persona eliminada con éxito");
			
			out.close();
			bw.close();

		} catch (IOException exception) {
			// exception handling left as an exercise for the reader
		}
	}
	
	public static ArrayList<Coche> listarCoches() {

		ArrayList<Coche> coches = new ArrayList<>();

		String nombre_fichero = "Coche.txt";
		File file = new File(nombre_fichero);
		FileReader fr = null;
		BufferedReader br = null;

		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String linea = "";
						
			while ((linea = br.readLine()) != null) {

				String[] cochesTxt = linea.split(",");

				Coche c = new Coche(cochesTxt[0], cochesTxt[1], cochesTxt[2], cochesTxt[3], cochesTxt[4]);
				//System.out.println(c.toString());
				coches.add(c);
			}
			
			br.close();
			fr.close();
			

		} catch (Exception a) {
			// TODO: handle exception
		}
		return coches;
	}
	
	public static void guardarCocheFichero(Coche coche) {
		String nombre_fichero = "Coche.txt";

		File file = new File(nombre_fichero);

		String linea = "";

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			linea = coche.getMatricula() + "," + coche.getColor() + "," + coche.getModelo() + "," + coche.getAñoFabricacion()+","+coche.getNombreDueño();
			out.println(linea);

			JOptionPane.showMessageDialog(null, "Coche guardado con éxito");

		} catch (IOException exception) {
			// exception handling left as an exercise for the reader
		}
	}
	
	public static void guardarCochesFichero(ArrayList<Coche> coches) {
		String nombre_fichero = "Coche.txt";

		File file = new File(nombre_fichero);

		String linea = "";

		try (FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			for (int i = 0; i < coches.size(); i++) {
				linea = coches.get(i).getMatricula() + "," + coches.get(i).getColor() + "," + coches.get(i).getModelo() + "," + coches.get(i).getAñoFabricacion()+","+coches.get(i).getNombreDueño();
				out.println(linea);
			}

		} catch (IOException exception) {
			// exception handling left as an exercise for the reader
		}
	}
	
	public static void borrarFichero() {
		String nombre_fichero = "Coche.txt";
		File file = new File(nombre_fichero);
		
		if(file.exists()){
			if(file.delete()){
				JOptionPane.showMessageDialog(null, "Tabla de Coches vacía");
			}else {
				JOptionPane.showMessageDialog(null, "No se puede borrar el fichero");
			}
		}else{
			JOptionPane.showMessageDialog(null, "No existe el fichero a eliminar");
		}
	}
	
	public static boolean verificarMatricula(String matricula) {
		boolean existe = false;
		
		ArrayList<Coche> coches = listarCoches();
		
		for (int i = 0; i < coches.size(); i++) {
			if(coches.get(i).getMatricula().equalsIgnoreCase(matricula)) {
				existe = true;
				break;
			}
		}
		
		return existe;
	}
	
	public static boolean verificarFormatoMatricula(String matricula) {
				
		boolean formatoOk = false;
		
		if(matricula.toUpperCase().matches("^[0-9]{4}[A-Z]{3}")) {
			formatoOk = true;
		}		
				
		return formatoOk;
	}
	
	public static ArrayList<Coche> listaCochesBusqueda(String color){
		ArrayList<Coche> coches = new ArrayList<>();
		ArrayList<Coche> coches_actuales = listarCoches();
		
		for (int i = 0; i < coches_actuales.size(); i++) {
			if(coches_actuales.get(i).getColor().equalsIgnoreCase(color)) {
				coches.add(coches_actuales.get(i));
			}
		}
		
		return coches;
	}
	
	public static ArrayList<Coche> listaCochesFecha(int fechaDesde, int fechaHasta){
		ArrayList<Coche> coches = new ArrayList<>();
		ArrayList<Coche> coches_actuales = listarCoches();
		
		for (int i = 0; i < coches_actuales.size(); i++) {
			int anyoFab = Integer.parseInt(coches_actuales.get(i).getAñoFabricacion());
			
			if(anyoFab >= fechaDesde && anyoFab <= fechaHasta) {
				coches.add(coches_actuales.get(i));
			}
			
		}
		
		return coches;
	}

	public static void guardarCochesFicheroCriterios(String fichero, ArrayList<Coche> coches) {
		String nombre_fichero = "filtros/"+fichero+".txt";

		File file = new File(nombre_fichero);

		String linea = "";

		try (FileWriter fw = new FileWriter(file,true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			for (int i = 0; i < coches.size(); i++) {
				linea = coches.get(i).getMatricula() + "," + coches.get(i).getColor() + "," + coches.get(i).getModelo() + "," + coches.get(i).getAñoFabricacion()+","+coches.get(i).getNombreDueño();
				out.println(linea);
			}

			out.close();
			bw.close();
			
		} catch (IOException exception) {
			// exception handling left as an exercise for the reader
		}
	}
	
}
