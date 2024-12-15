package interfazVisual;

public class VerticeVista {

	private int id;
	private int x;
	private int y;
	private String nombreEspia;


	public VerticeVista(int id, int x, int y) {
		this.id=id;
		this.x=x;
		this.y=y;
		this.nombreEspia = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getNombreEspia() {
		return nombreEspia;
	}

	public void setNombreEspia(String nombreEspia) {
		this.nombreEspia = nombreEspia;
	}
}
