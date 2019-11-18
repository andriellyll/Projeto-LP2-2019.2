package pacote;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class ControllerPersistencia {

	private ControllerAtividade controllerAtividade;
	private ControllerPesquisa controllerPesquisa;
	private ControllerPesquisador controllerPesquisador;
	private ControllerProblemaObjetivo controllerProblemaObjetivo;

	public ControllerPersistencia(ControllerAtividade controllerAtividade, ControllerPesquisa controllerPesquisa,
	 ControllerPesquisador controllerPesquisador, ControllerProblemaObjetivo controllerProblemaObjetivo ) {
		this.controllerAtividade = controllerAtividade;
		this.controllerPesquisa = controllerPesquisa;
		this.controllerPesquisador = controllerPesquisador;
		this.controllerProblemaObjetivo = controllerProblemaObjetivo;
		
	}
	
	public void salvar() {
		try {
			FileOutputStream saveFile = new FileOutputStream("controllerAtividade.dat");
			ObjectOutputStream stream = new ObjectOutputStream(saveFile);
			stream.writeObject(controllerAtividade);

			stream.close();
			
			FileOutputStream saveFile2 = new FileOutputStream("controllerPesquisa.dat");
			ObjectOutputStream stream2 = new ObjectOutputStream(saveFile2);
			stream2.writeObject(controllerPesquisa);

			stream2.close();
			
			FileOutputStream saveFile3 = new FileOutputStream("controllerPesquisador.dat");
			ObjectOutputStream stream3 = new ObjectOutputStream(saveFile3);
			stream3.writeObject(controllerPesquisador);

			stream3.close();
			
			FileOutputStream saveFile4 = new FileOutputStream("controllerProblemaObjetivo.dat");
			ObjectOutputStream stream4 = new ObjectOutputStream(saveFile4);
			stream4.writeObject(controllerProblemaObjetivo);

			stream4.close();
			
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Metodo responsavel por recuperar o que foi salvo previamente
	 */
	public void carregar() {
		try {
			FileInputStream restFile = new FileInputStream("controllerAtividade.dat");
			ObjectInputStream stream = new ObjectInputStream(restFile);
			ControllerAtividade controllerAtividade = (ControllerAtividade) stream.readObject();

			stream.close();
			this.controllerAtividade = controllerAtividade;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	
	}
	
	

}
