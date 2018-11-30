import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Arquivo {
	public void iniciarArquivo(String endereco) {
		Studant studant = new Studant();
		try {
			FileInputStream arqIn = new FileInputStream(endereco);
			ObjectInputStream objIn = new ObjectInputStream(arqIn);
			objIn.close();
			arqIn.close();
		} catch (Exception err) {

			try {
				FileOutputStream arq = new FileOutputStream(endereco);
				ObjectOutputStream obj = new ObjectOutputStream(arq);
				obj.writeObject(studant);
				obj.flush();
				obj.close();
				arq.close();
			} catch (Exception e) {
				System.out.println("ERROR:" + e);
			}
		}

	}

	public Studant lerArquivo(String endereco) {
		Studant a = null;
		try {
			FileInputStream arqIn = new FileInputStream(endereco);
			ObjectInputStream objIn = new ObjectInputStream(arqIn);
			a = (Studant) objIn.readObject();
			objIn.close();
			arqIn.close();

		} catch (Exception err) {
			System.out.println("Error ao ler arquivo: " + err);
		}

		return a;
	}

	public void gravarEmArquivo(Studant studant, String endereco) {
		try {
			FileOutputStream arq = new FileOutputStream(endereco);
			ObjectOutputStream obj = new ObjectOutputStream(arq);
			obj.writeObject(studant);
			obj.flush();
			obj.close();
			arq.close();
		} catch (Exception err) {
			System.out.println("error gravar" + err);
		}
	}
}
