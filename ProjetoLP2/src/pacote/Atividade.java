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

	private String codigo;
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private Period dias;
	private List<Item> itens;

/**
 * Contrutor de uma atividade, com sua descricao, nivel de risco, descricao do risco, seu codigo de identificacao e o numero de dias.
 * 
 * @param descricao valor que relata como e a atividade
 * @param nivelRisco valor que relata a itensidade desta atividade
 * @param descricaoRisco valor que relata o porque esta atividade possivel determinado nivel de risco
 * @param codigo valor que relata o identificador unico desta atividade
 */

	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String codigo, Period dias) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.codigo = codigo;
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
 * 
 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

/**
 * 
 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
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