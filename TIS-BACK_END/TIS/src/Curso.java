import java.io.Serializable;

import org.json.JSONObject;

public class Curso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoria;

	
	public Curso(String categoria) {
		this.categoria = categoria;

	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Categoria :"+categoria;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("Categoria", categoria);

		return obj;
	}
}
