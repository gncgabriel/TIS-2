import java.io.Serializable;

public class AvaliacaoMonitor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int notaAula;
	private int notaMonitor;
	
	public AvaliacaoMonitor(int notaAula, int notaMonitor) {
		this.notaAula = notaAula;
		this.notaMonitor = notaMonitor;
	}
	public int getNotaAula() {
		return notaAula;
	}
	public void setNotaAula(int notaAula) {
		this.notaAula = notaAula;
	}
	public int getNotaMonitor() {
		return notaMonitor;
	}
	public void setNotaMonitor(int notaMonitor) {
		this.notaMonitor = notaMonitor;
	}
}
