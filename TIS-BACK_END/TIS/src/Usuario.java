
import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class Usuario implements JsonFormatter, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int qtdeAulaRecebidas = 1;
	private int id;
	private String nome;
	private String sobrenome;
	private String email;
	private int idade;
	private String telefone;
	private String estado;
	private String cidade;
	private String bairro;
	private String senha;
	private double carteira;
	private double nota = 5.00;
	private boolean eMonitor;
	private Set<Aula> aulasRecebidas = new HashSet<Aula>();
	private Set<Aula> aulasDadas = new HashSet<Aula>();
	private Set<Curso> cursosDoMonitor = new HashSet<Curso>();

	

	public Usuario(String nome, String sobrenome, String email, int idade, String telefone, String estado,
			String cidade, String bairro, String senha, boolean eMonitor, int id) {

		this.eMonitor = eMonitor;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.idade = idade;
		this.telefone = telefone;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.senha = senha;
		this.id = id;

	}

	public Usuario(String nome, String sobrenome, String email, String senha, int id) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public double getCarteira() {
		return carteira;
	}

	public double getNota() {
		return nota;
	}

	public boolean eMonitor() {
		return eMonitor;
	}

	public void setMonitor() {
		if (!eMonitor) {
			this.eMonitor = true;
		}
	}

	public void setAulasRecebidas() {
		qtdeAulaRecebidas++;
	}

	public String getSenha() {
		return senha;
	}

	public boolean senha(String senha) {
		return senha == this.senha;
	}

	public void addAntCoin(double qtde) {
		carteira = carteira + qtde;
	}

	public void setNota(double nota) {
		this.nota = (this.nota + nota) / qtdeAulaRecebidas;
	}

	public Aula receberAula(Aula aula) {

		aulasRecebidas.add(aula);
		qtdeAulaRecebidas++;
		this.carteira = this.carteira-aula.getValor();
		return aula;
		
	}

	public String getAulasRecebidas() {
		JSONArray array = new JSONArray();
		for(Aula b : aulasRecebidas) {
			array.put(b.toJson());
		}
		return array.toString();
	}
	public String getAulasDadas() {
		JSONArray array = new JSONArray();
		for(Aula b : aulasDadas) {
			array.put(b);
		}
		return array.toString();
	}

	public void darAula(Aula aula) {
		aula.setMonitorDaAula(this.nome);
		aula.setStatus(1);
		aulasDadas.add(aula);
	}

	public int getId() {
		return id;
	}
	public String getCursosMonitor() {
		JSONArray array = new JSONArray();
		for(Curso c : cursosDoMonitor) {
			array.put(c.toJson());
		}
		return array.toString();
	}
	public void addCursoMonitor(Curso c) {
		cursosDoMonitor.add(c);
	}


	@Override
	public String toString() {
		return "ID: "+id +" Nome: " + nome+" Sobrenome: "+sobrenome+" Email: "+email+" Idade: "+idade+" Telefone: " + telefone
				+" Estado: "+estado+" Cidade: "+cidade+" Bairro: "+bairro+ " Sennha: "+senha+" �Monitor :"+eMonitor;
	}

	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("Nome", nome);
		obj.put("Sobrenome", sobrenome);
		obj.put("Email", email);
		obj.put("Idade", idade);
		obj.put("Telefone", telefone);
		obj.put("Estado", estado);
		obj.put("Cidade", cidade);
		obj.put("Bairro",bairro);
		obj.put("Senha", senha);
		obj.put("Carteira", carteira);
		obj.put("EMonitor", eMonitor);
		obj.put("ID", id);
		return obj;
	}

}
