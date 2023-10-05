 package modelos;

public class Persona {

	private int id;
	private String nombre;
	private String cargo;
	private int edad;
	
	public Persona(int id, String nombre, String cargo, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.cargo = cargo;
		this.edad = edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", cargo=" + cargo + ", edad=" + edad + "]";
	}
		
}
