import java.io.Serializable;

import org.json.JSONObject;

public class Curso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoria;
	private String titulo;
	private String descricao;
	
	public Curso(String categoria, String titulo, String descricao) {
		this.categoria = categoria;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Categoria :"+categoria+" Titulo:"+titulo+" Descrição:"+descricao;
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("Categoria", categoria);
		obj.put("Titulo", titulo);
		obj.put("Descrição", descricao);
		return obj;
	}
}
