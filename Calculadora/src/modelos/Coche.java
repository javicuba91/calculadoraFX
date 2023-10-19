package modelos;

import javafx.beans.property.SimpleStringProperty;

public class Coche {

	private final SimpleStringProperty matricula;
	private final SimpleStringProperty color;
	private final SimpleStringProperty modelo;
	private final SimpleStringProperty añoFabricacion;
	private final SimpleStringProperty nombreDueño;

	public Coche(String matricula, String color, String modelo, String añoFabricacion, String nombreDueño) {
		this.matricula = new SimpleStringProperty(matricula);
		this.color = new SimpleStringProperty(color);
		this.modelo = new SimpleStringProperty(modelo);
		this.añoFabricacion = new SimpleStringProperty(añoFabricacion);
		this.nombreDueño = new SimpleStringProperty(nombreDueño);
	}

	public String getMatricula() {
		return matricula.get();
	}

	public void setMatricula(String n) {
		matricula.set(n);
	}

	public String getColor() {
		return color.get();
	}

	public void setColor(String n) {
		color.set(n);
	}

	public String getModelo() {
		return modelo.get();
	}

	public void setModelo(String n) {
		modelo.set(n);
	}

	public String getAñoFabricacion() {
		return añoFabricacion.get();
	}

	public void setAñoFabricacion(String n) {
		añoFabricacion.set(n);
	}

	public String getNombreDueño() {
		return nombreDueño.get();
	}

	public void setNombreDueño(String n) {
		nombreDueño.set(n);
	}

	@Override
	public String toString() {
		return "Concesionario [Matricula=" + matricula.get() + ", color=" + color.get() + ", modelo=" + modelo.get() + ", añoFabricacion="
				+ añoFabricacion.get() + ", nombreDueño=" + nombreDueño.get() + "]";
	}

}
