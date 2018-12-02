
import java.io.Serializable;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Studant implements JsonFormatter, Serializable {
	static Random ran = new Random();
	static Random ran2 = new Random();
	private static final long serialVersionUID = 1L;
	private static final int MAX = 500;
	private static int idUsr = ran.nextInt(10000) * ran2.nextInt(13);
	private int idAula = ran.nextInt(10000) * ran2.nextInt(13);
	private List<Usuario> users = new LinkedList<Usuario>();
	private Usuario[] usuarios = new Usuario[MAX];
	private List<Aula> aulas = new LinkedList<Aula>();
	private List<Curso> cursos = new LinkedList<Curso>();

	// --------------------------------------------------------------------------------------------------------------------
	public Usuario addUsuario(String nome, String sobrenome, String email, int idade, String telefone, String estado,
			String cidade, String bairro, String senha, boolean eMonitor) {
		if (!eMonitor) {
			idUsr++;
			Usuario usr = new Usuario(nome, sobrenome, email, idade, telefone, estado, cidade, bairro, senha, false,
					idUsr);
			users.add(usr);
			return usr;
		} else {
			idUsr++;
			Usuario usr = new Usuario(nome, sobrenome, email, idade, telefone, estado, cidade, bairro, senha, true,
					idUsr);
			users.add(usr);
			return usr;
		}
	}

	public Usuario addUsuario(Usuario usr) {
		users.add(usr);
		idUsr++;
		return usr;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public int getQtdeUsrs() {
		return idUsr;
	}

	public String getUsuarios() {
		JSONArray array = new JSONArray();
		for (Usuario u : users) {
			array.put(u.toJson());
		}
		return array.toString();
	}

	public Usuario getUsuario(int id) {
		for (Usuario u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public Usuario getUsuario(String email) {
		for (Usuario u : users) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public Usuario getUsuarioByNome(String nome) {
		for (Usuario u : users) {
			if (u.getNome().equals(nome)) {
				return u;
			}
		}
		return null;
	}

	public Usuario consultarUsuario(String email, String senha) {
		for (Usuario u : users) {
			if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
				return u;
			}
		}
		return null;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String virarMonitor(int id) {
		for (Usuario u : users) {
			if (u.getId() == id) {
				u.setMonitor();
				return u.toJson().toString();
			}
		}
		return null;

	}

	public String addCoin(int id, double valor) {
		for (Usuario u : users) {
			if (u.getId() == id) {
				u.addAntCoin(valor);
				return u.toJson().toString();
			}
		}
		return null;
	}

	// --------------------------------------------------------------------------------------------------------------------
	public String getAulas() {
		JSONArray array = new JSONArray();
		for (Aula a : aulas) {
			array.put(a.toJson());
		}
		return array.toString();
	}

	public String getAula(int idAula) {

		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String iniciarAula(int idAula, String codigoDaAula) {
		for (Aula a : aulas) {
			if (a.getId() == idAula && a.getCodigo().equals(codigoDaAula)) {
				a.setStatus(3);
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String finalizarAula(int idAula) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.setStatus(4);
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String cancelarAula(int idAula) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.setStatus(2);
				return a.toJson().toString();
			}
		}

		return null;
	}

	public String addAula(int idAluno, String local, int canal, String data, String descricao, int duracao,
			String curso, String horaInicio) {

		Curso aux = null;
		for (Curso c : cursos) {
			if (c.getCategoria().equals(curso)) {
				aux = c;
			}
		}

		int valor = 0;
		if (canal == 1) {
			valor = 100 * duracao;
		} else {
			valor = 80 * duracao;
		}

		Aula aula = new Aula(idAula, this.getUsuario(idAluno).getNome(), local, canal, data, descricao, duracao, valor,
				aux, horaInicio);
		if (this.getUsuario(idAluno).getCarteira() - valor < 0) {
			return "{\"erro\":\"Saldo insuficiente\"}";
		} else {
			this.getUsuario(idAluno).receberAula(aula);
			idAula++;
			aulas.add(aula);
			return aula.toJson().toString();
		}

	}

	public String verAulasRecebidas(int id) {
		for (Usuario u : users) {
			if (u.getId() == id) {
				return u.getAulasRecebidas();
			}
		}
		return null;
	}

	public String verAulasDadas(int id) {
		for (Usuario u : users) {
			if (u.getId() == id) {
				return u.getAulasDadas();
			}
		}
		return null;
	}

	public String darAula(int idMonitor, int idAula) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.setMonitorDaAula(this.getUsuario(idMonitor).getNome());
				a.setStatus(1);
				getUsuario(idMonitor).darAula(a);
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String addNotaAula(int idAula, int nota) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.addNotaAula(nota);
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String addNotaAluno(int idAula, int nota) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.addNotaAluno(nota);
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String addNotaMonitor(int idAula, int nota) {
		for (Aula a : aulas) {
			if (a.getId() == idAula) {
				a.addNotaMonitor(nota);
				this.getUsuarioByNome(a.getMonitorDaAula()).addAntCoin(a.getValor());
				return a.toJson().toString();
			}
		}
		return null;
	}

	public String getAulasSemMonitor() {
		JSONArray array = new JSONArray();
		for (Aula b : aulas) {
			if (b.getMonitorDaAula() == null) {
				array.put(b.toJson());
			}
		}
		return array.toString();
	}

	public String getAulasSemMonitor(String filtro,int id) {
		JSONArray array = new JSONArray();
		for (Aula a2 : aulas) {
			if (a2.getMonitorDaAula().equals("Aguardando Monitor") && !a2.getAlunoDaAula().equals(this.getUsuario(id).getNome())) {
				array.put(a2.toJson());
			}
		}

		return array.toString();
	}
	// --------------------------------------------------------------------------------------------------------------------

	public void addCurso(String categoria) {
		Curso c = new Curso(categoria);
		cursos.add(c);
	}

	public String verCursos() {
		JSONArray array = new JSONArray();
		// for (Curso c : cursos) {
		// array.put(c.toJson());
		// }

		cursos.forEach(c -> array.put(c.toJson()));
		return array.toString();
	}

	public Curso getCurso(String categoria) {
		for (Curso c : cursos) {
			if (c.getCategoria().equals(categoria)) {
				return c;
			}
		}
		return null;
	}
	public String getMyCursos(int id) {
		return this.getUsuario(id).getCursosMonitor();
	}

	public String addCursoAoMonitor(int idMonitor, String c) {
		if (this.getCurso(c) == null) {
			this.addCurso(c);
		}
		this.getUsuario(idMonitor).addCursoMonitor(this.getCurso(c));
		return this.getUsuario(idMonitor).getCursosMonitor();
	}

	// --------------------------------------------------------------------------------------------------------------------

	public boolean emailEValido(String email) {
		for (Usuario u : users) {
			if (u.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public JSONArray toJsonArray() {
		JSONArray array = new JSONArray();
		for (int i = 1; i <= idUsr; i++) {
			if (usuarios[i] == null) {
				break;
			}
			array.put(usuarios[i].toJson());
		}
		return array;
	}

	@Override
	public JSONObject toJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
