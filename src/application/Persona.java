package application;

public class Persona {

	private String nombre;
	private String fecha;
	private String comentario;

	public Persona(String nombre, String fecha, String comentario) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", fecha=" + fecha + ", comentario=" + comentario + "]";
	}

}
