import java.io.Serializable;

public class AvaliacaoAluno implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nota;

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
}
