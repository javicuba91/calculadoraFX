package modelos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Persona {

	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nombre;
	private final SimpleStringProperty cargo;
	private final SimpleIntegerProperty edad;
		

	public Persona(int id, String nombre, String cargo, int edad) {
		this.id = new SimpleIntegerProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.cargo = new SimpleStringProperty(cargo);
		this.edad = new SimpleIntegerProperty(edad);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int n) {
		id.set(n);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String n) {
		nombre.set(n);
	}

	public String getCargo() {
		return cargo.get();
	}

	public void setCargo(String n) {
		cargo.set(n);
	}

	public int getEdad() {
		return edad.get();
	}

	public void setEdad(int n) {
		edad.set(n);
	}
	

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", cargo=" + cargo + ", edad=" + edad + "]";
	}

}
