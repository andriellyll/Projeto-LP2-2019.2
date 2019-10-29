package pacote;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Para atingir um objetivo, e importante descrever e planejar atividades a serem realizadas para obter resultados.
 * Cada atividade planejada apresenta uma descricao do que deve ser feito, uma duracao planejada, resultados esperados,
 * um risco associado, uma duração em dias, um codigo de identificao unica e seus itens. 
 * 
 * @author Henrique Lemos
 */

public class Atividade {

/**
 * Cada atividade apresenta uma descricao dela, um nivel de risco apresentado em ate tres niveis,
 * a descricao deste risco, a quantidade de dias e seus itens para a conclusao da atividade,
 * para obtencao de um resultado.
 */

	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private Period dias;
	private List<Item> itens;

/**
 * Contrutor de uma atividade, com sua descricao, nivel de risco, descricao do risco e o numero de dias.
 * 
 * @param descricao valor que relata como e a atividade
 * @param nivelRisco valor que relata a itensidade desta atividade
 * @param descricaoRisco valor que relata o porque esta atividade possivel determinado nivel de risco
 */

	public Atividade(String descricao, String nivelRisco, String descricaoRisco, Period dias) {
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.dias = dias;
		this.itens = new ArrayList<>(); 
	}

/**
 * Metodo responsavel por adicionar um novo item, na lista de itens, da atividade.
 * 
 * @param item valor que descreve o novo item
 * @param codigo valor de identificacao do novo item
 */

	public void adicionaItem(String item, int codigo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(item, "Item nao pode ser nulo ou vazio.");
		Item novoItem = new Item(item, codigo);
		if (!itens.contains(novoItem)) {
			itens.add(novoItem);
		}
	}

/**
 * Metodo responsavel por contar o numero de itens pendentes.
 * 
 * @return um inteiro representando esta quantidade
 */

	public int ItensPendentes() {
		int numPendentes = 0;
		
		for (int i = 0; i < itens.size(); i++) {
			if ("PENDENTE".equals(itens.get(i).getSituacao())) {
				numPendentes++;
			}
		}
		return numPendentes;
	}

/**
 * Metodo responssavel por contar o numero de itens realizados.
 * 
 * @return um inteiro representando esta quantidade
 */

	public int ItensRealizados() {
		int numRealizados = 0;
		
		for (int i = 0; i < itens.size(); i++) {
			if ("REALIZADO".equals(itens.get(i).getSituacao())) {
				numRealizados++;
			}
		}
		return numRealizados; 
	}

/**
 * Metodo resposavel por retornar uma listagem dos itens desta atividade.
 * 
 * @return Em formato de String, na representacao "SITUACAO - ITEM | "
 */ 

	public String exibeItens() {
		String retorno = "";
		
		for (int i = 0; i < itens.size(); i++) {
			if (i == itens.size() - 1) {
				retorno += itens.get(i).toString();
			} else {
				retorno += itens.get(i).toString() + " | ";
			}
		}
		return retorno;
	}

/**
 * Metodo responsavel por retornar a String que representa a atividade.
 * 
 * @return A representacao que segue no formato "DESCRICAO (NIVEL DE RISCO - DESCRICAO RISCO)"
 */

	@Override
	public String toString() {
		return descricao + " (" + nivelRisco + " - " + descricaoRisco + ")";
	}
}