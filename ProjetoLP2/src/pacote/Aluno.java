package pacote;

/**
 * Classe que representa no sistema um aluno, uma especialidade de Pesquisador.
 * 
 * @author Helen Bento Cavalcanti
 *
 */

public class Aluno implements Funcao {
	/**
	 * Atributo que representa o semestre que o aluno se encontra.
	 */
	private int semestre;
	/**
	 * Atributo que representa o indice de eficiencia academica desse aluno.
	 */
	private double IEA;

	/**
	 * Contrutor de aluno, que recebe seu semestre e seu IEA.
	 * 
	 * @param semestre o semestre que o aluno se encontra.
	 * @param IEA      o indice de eficiencia academica desse aluno.
	 */
	public Aluno(int semestre, double IEA) {
		ValidadorDeEntradas.verificaSemestre(semestre, "Atributo semestre com formato invalido.");
		ValidadorDeEntradas.verificaIEA(IEA, "Atributo IEA com formato invalido.");

		this.semestre = semestre;
		this.IEA = IEA;
	}

	/**
	 * Metodo que gera a representacao textual do aluno, recebendo caracteristicas
	 * que sao do Pesquisador,
	 * 
	 * @param nome      o nome do pesquisador.
	 * @param funcao    a funcao do pesquisador.
	 * @param biografia a biografia do pesquisador.
	 * @param email     o email do pesquisador.
	 * @param foto      a foto do pesquisador.
	 * @return uma String com a representacao textual do Aluno.
	 */
	@Override
	public String toString(String nome, String funcao, String biografia, String email, String foto) {
		return nome + " (" + funcao + ")" + " - " + biografia + " - " + email + " - " + foto + " - " + this.semestre
				+ "o SEMESTRE - " + this.IEA;
	}

	/**
	 * Metodo que altera caracteristicas especificas do Aluno, recebendo qual
	 * atributo quer alterar e a nova versao desse atributo e nao retorna nada.
	 * 
	 * @param atributo     atributo que deve alterar.
	 * @param novoAtributo nova versao desse atributo.
	 */

	@Override
	public void setEspecialidade(String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Campo atributo nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo novoAtributo nao pode ser nulo ou vazio");

		if (atributo.equalsIgnoreCase("semestre")) {
			int novoSemestre = Integer.parseInt(novoAtributo);

			ValidadorDeEntradas.verificaSemestre(novoSemestre, "Atributo semestre com formato invalido.");

			this.semestre = novoSemestre;
		} else if (atributo.equalsIgnoreCase("IEA")) {
			double novoIEA = Double.parseDouble(novoAtributo);

			ValidadorDeEntradas.verificaIEA(novoIEA, "Atributo IEA com formato invalido.");

			this.IEA = novoIEA;
		} else {
			throw new RuntimeException("Atributo invalido.");
		}

	}
}