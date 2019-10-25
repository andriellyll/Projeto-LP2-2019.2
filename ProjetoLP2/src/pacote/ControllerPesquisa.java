package pacote;

import java.util.HashMap;
import java.util.Map;

public class ControllerPesquisa{
	
	private Map<String, Pesquisa> pesquisas;
	private Map<String, Integer> codigos;
	
	public ControllerPesquisa() {
		this.pesquisas = new HashMap<>();
		this.codigos = new HashMap<>();
	}
	
    public String cadastraPesquisa(String descricao, String campoDeInteresse) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "");
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(campoDeInteresse, "");
    	ValidadorDeEntradas.validaTamanhoString(campoDeInteresse, "Formato do campo de interesse invalido.", 250);
    	ValidadorDeEntradas.validaCampoDeInteresse(campoDeInteresse);
    	String codigoChave = campoDeInteresse.substring(0, 3).toUpperCase();
    	String codigoPesquisa;
    
    
    	if(this.codigos.containsKey(codigoChave)) {
    		int valor = this.codigos.get(codigoChave) + 1;
    		this.codigos.put(codigoChave, valor);
    		codigoPesquisa = codigoChave + Integer.toString(valor);
    		this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
    	} else {
    		this.codigos.put(codigoChave, 1);
    		codigoPesquisa = codigoChave + "1";
    		this.pesquisas.put(codigoPesquisa, new Pesquisa(codigoPesquisa, descricao, campoDeInteresse));
    	}
    	return codigoPesquisa;
    	
    	
    }

    

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "");
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(conteudoASerAlterado, "");
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(novoConteudo, "");
    	
    }

    

    public void encerraPesquisa(String codigo, String motivo) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "");
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(motivo, "");
    	
    }
    public void ativaPesquisa(String codigo) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "");
    	
    }
    public String exibePesquisa(String codigo) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "");
		return "";
    	
    }
    public boolean pesquisaEhAtiva(String codigo) {
    	ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "");
		return true;
    	
    }



}
