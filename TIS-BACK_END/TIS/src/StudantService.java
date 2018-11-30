import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

public class StudantService {
	private Arquivo a = new Arquivo();;
	static String endereco = "studant.dat";
	private Studant studant = a.lerArquivo(endereco);

	public StudantService() {
		if (studant.getUsuario("caio@gmail.com") == null) {
			contasFake();
		}

	}

	// Contas pr� criadas para teste
	public void contasFake() {
		studant.addUsuario("Mia", "Kahlifa", "kah@gmail.com", 18, "11-54256984", "S�o Paulo", "S�o Paulo", "Centro",
				"senha", false);
		studant.addUsuario("Jorge", "Amado", "jorge@gmail.com", 18, "21-3456789", "Rio de Janeiro", "Resende", "Centro",
				"senha", true);
		studant.addUsuario("Caio", "Ribeiro", "caio@gmail.com", 18, "3178459632", "Minas Gerais", "Belo Horizonte",
				"Centro", "senha", true);
		studant.addCursoAoMonitor(studant.getUsuario("caio@gmail.com").getId(), "Matematica");
		studant.addCursoAoMonitor(studant.getUsuario("jorge@gmail.com").getId(), "Programacao");

		studant.addAula(studant.getUsuario("kah@gmail.com").getId(), "Pampulha, rua portugal, 571", 1, "10/02/2019",
				"Aula de Geometria analitica", 1, "Matematica", "12:00");
		studant.addCoin(studant.getUsuario("kah@gmail.com").getId(), 2000.00);

		a.gravarEmArquivo(studant, endereco);
	}

	public String criarConta(Request request) {
		String nome;
		String sobrenome;
		String email;
		int idade;
		String telefone;
		String estado;
		String cidade;
		String bairro;
		String senha;
		String eMonitor;

		Query query = request.getQuery();
		nome = query.get("nome");
		sobrenome = query.get("sobrenome");
		email = query.get("email");
		idade = query.getInteger("idade");
		telefone = query.get("telefone");
		estado = query.get("estado");
		cidade = query.get("cidade");
		bairro = query.get("bairro");
		senha = query.get("senha");
		eMonitor = query.get("eMonitor");
		if (!studant.emailEValido(email)) {
			return null;
		}
		if (eMonitor.equals("1")) {
			String retorno = studant
					.addUsuario(nome, sobrenome, email, idade, telefone, estado, cidade, bairro, senha, true).toJson()
					.toString();
			a.gravarEmArquivo(studant, endereco);
			return retorno;
		} else {
			String retorno = studant
					.addUsuario(nome, sobrenome, email, idade, telefone, estado, cidade, bairro, senha, false).toJson()
					.toString();
			System.out.println(retorno);
			a.gravarEmArquivo(studant, endereco);
			return retorno;
		}
	}

	public String logar(Request request) {
		Query query = request.getQuery();
		String email = (String) query.get("email");
		String senha = (String) query.get("senha");
		if (studant.consultarUsuario(email, senha) == null) {
			return null;
		}
		return studant.consultarUsuario(email, senha).toJson().toString();
	}

	public String criarAulas(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		String local = (String) query.get("Endereco");
		int canal = Integer.parseInt((String) query.get("Canal"));
		String data = (String) query.get("Data");
		String descricao = (String) query.get("Descricao");
		int duracao = Integer.parseInt((String) query.get("Duracao"));
		String curso = (String) query.get("Curso");
		String horaInicio = (String) query.get("HInicio");

		String retorno = studant.addAula(id, local, canal, data, descricao, duracao, curso, horaInicio);
		a.gravarEmArquivo(studant, StudantService.endereco);
		return retorno;
	}

	public String darAula(Request request) {
		Query query = request.getQuery();
		int idAula = Integer.parseInt((String) query.get("IdAula"));
		int idMonitor = Integer.parseInt((String) query.get("ID"));
		String retorno = studant.darAula(idMonitor, idAula);
		a.gravarEmArquivo(studant, endereco);
		return retorno;
	}

	public String iniciarAula(Request request) {
		Query query = request.getQuery();
		int idAula = Integer.parseInt((String) query.get("IdAula"));
		String codigo = (String) query.get("CodigoAula");
		String retorno = studant.iniciarAula(idAula, codigo);
		a.gravarEmArquivo(studant, endereco);
		return retorno;
	}

	public String cancelarAula(Request request) {
		Query query = request.getQuery();
		int idAula = Integer.parseInt((String) query.get("IdAula"));
		String retorno = studant.cancelarAula(idAula);
		a.gravarEmArquivo(studant, endereco);
		return retorno;
	}

	public String finalizarAula(Request request) {
		Query query = request.getQuery();
		int idAula = Integer.parseInt((String) query.get("IdAula"));
		String retorno = studant.finalizarAula(idAula);
		a.gravarEmArquivo(studant, endereco);
		return retorno;
	}

	public String getAula(Request request) {
		Query query = request.getQuery();
		int idAula = Integer.parseInt((String) query.get("ID"));

		return studant.getAula(idAula);
	}

	public String getMyAulasRecebidas(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));

		return studant.verAulasRecebidas(id);

	}

	public String getMyAulasDadas(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		return studant.verAulasDadas(id);
	}

	public String getAulasSemMonitor(Request request) {
		Query query = request.getQuery();
		String filtro = (String) query.get("Filtro");
		int id = Integer.parseInt((String) query.get("ID"));
		return studant.getAulasSemMonitor(filtro,id);
	}

	public String addNotaAula(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		int nota = Integer.parseInt((String) query.get("Nota"));
		a.gravarEmArquivo(studant, endereco);
		return studant.addNotaAula(id, nota);
	}

	public String addNotaAluno(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		int nota = Integer.parseInt((String) query.get("Nota"));
		a.gravarEmArquivo(studant, endereco);
		return studant.addNotaAluno(id, nota);
	}

	public String addNotaMonitor(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		int nota = Integer.parseInt((String) query.get("Nota"));
		a.gravarEmArquivo(studant, endereco);
		return studant.addNotaMonitor(id, nota);
	}
	
	public String addCursoAoMonitor(Request request){
		Query query = request.getQuery();
		int id = Integer.parseInt((String)query.get("ID"));
		String curso = (String) query.get("Curso");
		a.gravarEmArquivo(studant, endereco);
		return studant.addCursoAoMonitor(id, curso);
		
	}

	public String getCursos() {
		return studant.verCursos();
	}

	public String addCoin(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		int valor = Integer.parseInt((String) query.get("Valor"));
		a.gravarEmArquivo(studant, endereco);
		return (studant.addCoin(id, valor));

	}

	public String getCoin(Request request) {
		Query query = request.getQuery();
		int id = Integer.parseInt((String) query.get("ID"));
		return studant.getUsuario(id).toJson().toString();
	}

}
