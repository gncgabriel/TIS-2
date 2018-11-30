import java.io.Serializable;


import org.json.JSONObject;

public class Aula implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String monitorDaAula;
	private String alunoDaAula;
	private int notaAula;
	private int notaAluno;
	private int notaMonitor;
	private String local;
	private String canal;
	private String data;
	private int duracao;
	private double valor;
	private Curso curso;
	private String horaInicio;
	private String horaFim;
	private String codigo;
	private String descricao;
	// Status 0 = aguardando monitor, Status 1 = monitor aceitou aula
	// Status 2 = aula cancelada, Status 3 = aula iniciada, Status 4 = aula
	// finalizada
	private int status =0;

	public Aula(int id, String alunoDaAula, String local, int canal, String data, String descricao, int duracao, double valor,
			Curso curso, String horaInicio) {
		this.alunoDaAula = alunoDaAula;
		this.local = local;
		if (canal == 1) {
			this.canal = "Presencial";
		} else {
			this.canal = "Online";
		}
	

		this.data = data;
		this.duracao = duracao;
		this.valor = valor;
		this.curso = curso;
		this.horaInicio = horaInicio;
		this.id = id;
		this.setDescricao(descricao);
		this.codigo = RandomString.generate(10);
		this.monitorDaAula = "Aguardando Monitor";
	}
	
	public void addNotaAula(int nota){
		this.notaAula = nota;
	}
	public void addNotaMonitor(int nota){
		this.notaMonitor = nota;
	}
	public void addNotaAluno(int nota){
		this.notaAluno = nota;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMonitorDaAula() {
		return monitorDaAula;
	}

	public void setMonitorDaAula(String monitorDaAula) {
		this.monitorDaAula = monitorDaAula;
	}

	public int getNotaAula() {
		return this.notaAula;
	}

	public void setNotaAula(int nota) {
		this.notaAula = nota;
	}
	public int getNotaAluno() {
		return this.notaAula;
	}

	public void setNotaAluno(int nota) {
		this.notaAula = nota;
	}
	public int getNotaMonitor() {
		return this.notaAula;
	}

	public void setNotMonitor(int nota) {
		this.notaAula = nota;
	}

	public String getAlunoDaAula() {
		return alunoDaAula;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Id :"+id+" Monitor da aula: "+monitorDaAula+" Aluno da aula: "+alunoDaAula
				+" Local da aula: "+local+" Canal: "+canal+" Data: "+data+"Descricao: "+descricao+ "Duracao: "+duracao
				+" valor: "+valor+" Horario Inicio: "+horaInicio+" Horario Fim :"+horaFim
				+" Codigo da aula: "+codigo+" Status: "+status+"NotaAluno :"+notaAluno+"NotaAula :"+notaAula+"NotaMonitor: "+notaMonitor; 
	}
	
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("ID", id);
		obj.put("Monitor", monitorDaAula);
		obj.put("Aluno", alunoDaAula);
		obj.put("Local", local);
		obj.put("Canal", canal);
		obj.put("Data", data);
		obj.put("Descricao", descricao);
		obj.put("Duracao", duracao);
		obj.put("Hora_Inicio", horaInicio);
		obj.put("Valor",valor);
		obj.put("Codigo", codigo);
		obj.put("Status", status);
		obj.put("NotaAluno", notaAluno);
		obj.put("NotaAula", notaAula);
		obj.put("NotaMonitor", notaMonitor);
		return obj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
