package es.infointernet.model;

public class Actor {
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Actor{" +
				"nombre='" + nombre + '\'' +
				'}';
	}
}
