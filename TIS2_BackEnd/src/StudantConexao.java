
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class StudantConexao implements Container {
	static StudantService stdService;
	static String caminhoArquivo = "studant.dat";
	private static Arquivo arq = new Arquivo();

	public void handle(Request request, Response response) {
		try {
			String path = request.getPath().getPath();
			String method = request.getMethod();
			String mensagem;
			if (path.startsWith("/login") && "POST".equals(method)) {
				mensagem = stdService.logar(request);
				this.enviaResposta(Status.CREATED, response, mensagem);

			} else if (path.startsWith("/cadastro") && "POST".equals(method)) {
				mensagem = stdService.criarConta(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/criarAula") && "POST".equals(method)) {
				mensagem = stdService.criarAulas(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/darAula") && "POST".equals(method)) {
				mensagem = stdService.darAula(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/iniciarAula") && "POST".equals(method)) {
				mensagem = stdService.iniciarAula(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/cancelarAula") && "POST".equals(method)) {
				mensagem = stdService.cancelarAula(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/finalizarAula") && "POST".equals(method)) {
				mensagem = stdService.finalizarAula(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/aulasRecebidas") && "POST".equals(method)) {
				mensagem = stdService.getMyAulasRecebidas(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/aulasDadas") && "POST".equals(method)) {
				mensagem = stdService.getMyAulasDadas(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			else if (path.startsWith("/verCursos") && "POST".equals(method)) {
				mensagem = stdService.getCursos(request);
				this.enviaResposta(Status.OK, response, mensagem);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enviaResposta(Status status, Response response, String mensagem) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Studant (1.0)");
		response.setDate("Date", time);
		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (mensagem != null)
			body.println(mensagem);
		body.close();
	}

	public static void main(String[] args) throws Exception {
		arq.iniciarArquivo(caminhoArquivo);
		stdService = new StudantService();
		int porta = 7800;
		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new StudantConexao();
		ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
		Connection conexao = new SocketConnection(servidor);
		SocketAddress endereco = new InetSocketAddress(porta);
		conexao.connect(endereco);
		// Testa a conexão abrindo o navegador padrão.
		// Desktop.getDesktop().browse(new URI("http://127.0.0.1:" + porta));

		System.out.println("Tecle ENTER para interromper o servidor...");
		System.in.read();

		conexao.close();
		servidor.stop();
	}

}
