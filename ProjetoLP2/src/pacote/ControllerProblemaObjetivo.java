package pacote;

import java.util.HashMap;
import java.util.Map;

public class ControllerProblemaObjetivo {
	
	private Map<String,Problema> problemas;
	private Map<String,Objetivo> objetivos;
	private int controlaNumeroProblemas;
	private int controlaNumeroObjetivos;
	
	public ControllerProblemaObjetivo() {
		this.problemas = new HashMap<>();
		this.objetivos = new HashMap<>();
		this.controlaNumeroProblemas = 0;
		this.controlaNumeroObjetivos = 0;
			
	}
	
	private boolean problemaExiste(String codigo) {
		if(!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
		return true;
		
	}
	private boolean objetivoExiste(String codigo) {
		if(!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
		return true;
		
	}
	public String cadastraProblema(String descricao, int viabilidade) {
		
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
		
		controlaNumeroProblemas += 1;
		String codigo = "P"+ controlaNumeroProblemas;
		
		this.problemas.put(codigo, new Problema(descricao,viabilidade, codigo));
		
		return codigo;
		
		 
		
	}
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		
		ValidadorDeEntradas.validaEntradaNulaOuVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaTipo(tipo);
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(aderencia, "Valor invalido de aderencia");
		ValidadorDeEntradas.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
		
		controlaNumeroObjetivos += 1;
		String codigo = "O"+ controlaNumeroObjetivos;
		
		this.objetivos.put(codigo, new Objetivo(tipo, descricao, aderencia,viabilidade,codigo));
		return codigo;
		
	}
	public void apagarProblema(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if(problemaExiste(codigo)){
			problemas.remove(codigo);
			
		}
		
	}
	public void apagarObjetivo(String codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if(objetivoExiste(codigo)){
			objetivos.remove(codigo);
			
		}
	}
	public String exibeProblema(String codigo) {
		String saida = "";
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if(problemaExiste(codigo)){
			saida =  problemas.get(codigo).toString();	
		}
		return saida;
		
	}
	public String exibeObjetivo(String codigo) {
		String saida = "";
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		
		if(objetivoExiste(codigo)){
			saida = objetivos.get(codigo).toString();	
		}
		return saida;
		
	}
	

}
