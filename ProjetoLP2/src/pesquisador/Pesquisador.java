package pesquisador;

import java.io.Serializable;

import utils.ValidadorDeEntradas;

/**
 * Representacao de um Pesquisador no sistema. Cada pesquisador e identificado
 * unicamente pelo seu email.
 * 
 * @author Andrielly de Lima Lucena
 * @author Anna Beatriz Lucena Lira
 * @author Helen Bento Cavalcanti
 * @author Henrique Lemos Leite
 *
 */
public class Pesquisador implements Serializable {
	
	/**
	 * Codigo de serializacao
	 */
	private static final long serialVersionUID = 4111587496949639749L;
	
	/**
	 * Nome do pesquisador
	 */
	private String nome;
	
	/**
	 * Pequeno texto que descreve o pesquisador.
	 */
	private String biografia;
	
	/**
	 * Email do pesquisador
	 */
	private String email;
	
	/**
	 * URL de uma foto do pesquisador
	 */
	private String foto;
	
	/**
	 * Funcao do pesquisador (Professor, aluno ou externo)
	 */
	private String funcao;

	/**
	 * Atributo que guarada a especialidade do Pesquisador.
	 */
	private Funcao especialidade;

	/**
	 * Boolean que representa se o pesquisador esta ou nao ativo
	 */
	private boolean isAtivo;

	/**
	 * Constroi um pesquisador a partir do nome, funcao, biografia, email e URL da
	 * foto.
	 * 
	 * @param nome      nome do pesquisador
	 * @param funcao    funcao do pesquisador
	 * @param biografia biografia do pesquisador
	 * @param email     email do pesquisador
	 * @param fotoUrl   URL da foto do pesquisador
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(nome, "Campo nome nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(funcao, "Campo funcao nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(biografia, "Campo biografia nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(fotoUrl, "Campo fotoUrl nao pode ser nulo ou vazio");
		ValidadorDeEntradas.verificaEmail(email);
		ValidadorDeEntradas.verificaURL(fotoUrl);
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = fotoUrl;
		this.funcao = funcao;
		this.isAtivo = true;
	}

	/**
	 * Altera o nome do pesquisador
	 * 
	 * @param novoNome novo valor para o atributo nome
	 */
	public void setNome(String novoNome) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoNome, "Campo nome nao pode ser nulo ou vazio");
		this.nome = novoNome;
	}

	/**
	 * Altera a biografia do pesquisador
	 * 
	 * @param novaBiografia novo valor para o atributo biografia
	 */
	public void setBiografia(String novaBiografia) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaBiografia, "Campo biografia nao pode ser nulo ou vazio");
		this.biografia = novaBiografia;
	}

	/**
	 * Altera o email do pesquisador
	 * 
	 * @param novoEmail novo valor para o atributo email
	 */
	public void setEmail(String novoEmail) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoEmail, "Campo email nao pode ser nulo ou vazio");
		this.email = novoEmail;
	}

	/**
	 * Altera a URL da foto do pesquisador
	 * 
	 * @param novaFoto novo valor para o atributo foto
	 */
	public void setFoto(String novaFoto) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaFoto, "Campo fotoUrl nao pode ser nulo ou vazio");
		this.foto = novaFoto;
	}

	/**
	 * Altera a funcao do pesquisador
	 * 
	 * @param novaFuncao novo valor para o atributo foto
	 */
	public void setFuncao(String novaFuncao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaFuncao, "Campo fotoUrl nao pode ser nulo ou vazio");
		this.funcao = novaFuncao;
		this.especialidade = null;
	}

	/**
	 * Altera o status do pesquisador, que passa a ser inativo
	 */
	public void desativaPesquisador() {
		this.isAtivo = false;
	}

	/**
	 * Altera o status do pesquisador, que passa a ser ativo
	 */
	public void ativaPesquisador() {
		this.isAtivo = true;
	}

	/**
	 * Retorna um valor em boolean que representa se o pesquisador eh ativo ou nao
	 * 
	 * @return status do pesquisador
	 */
	public boolean ehAtivo() {
		return this.isAtivo;
	}

	/**
	 * Gera um codigo inteiro do objeto a partir de seu identificador unico (email)
	 * 
	 * @return o codigo gerado
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	/**
	 * Compara se um objeto eh igual ao objeto atual. Serao iguais se forem da mesma
	 * classe e se tiverem emails iguais.
	 * 
	 * @return um valor booleano que representa se os objetos sao iguais ou nao
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	/**
	 * Gera uma representacao textual do pesquisador, com nome, funcao, biografia,
	 * email e URL da foto.
	 * 
	 * @return a representacao em string
	 */
	public String toString() {
		if (funcao.equalsIgnoreCase("Externo") || especialidade == null) {
			return this.nome + " (" + this.funcao + ")" + " - " + this.biografia + " - " + this.email + " - "
					+ this.foto;
		}
		return especialidade.toString(nome, funcao, biografia, email, foto);
	}

//------------------------------------------ Pesquisador (Parte 2) ------------------------------------------

	/**
	 * Metodo que cadastra a especalidade Professor em um determinado Pesquisador,
	 * nao retorna nada e recebe como parametro caracteristicas especificas dessa
	 * especialidade, que sao elas: formacao, unidade e data.
	 * 
	 * @param formacao a formacao daquele professor.
	 * @param unidade  a unidade que o professor faz parte.
	 * @param data     a data de contratacao do professor.
	 */
	public void cadastraEspecialidadeProfessor(String formacao, String unidade, String data) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(data, "Campo data nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaData(data, "Atributo data com formato invalido.");
		if (!(funcao.equalsIgnoreCase("Professor"))) {
			throw new RuntimeException("Pesquisador nao compativel com a especialidade.");
		}
		this.especialidade = new Professor(formacao, unidade, data);
	}

	/**
	 * Metodo que cadastra a especalidade Aluno, nao retorna nada e recebe como
	 * parametro caracteristicas especificas dessa especialidade, que sao elas:
	 * semestre e IEA.
	 * 
	 * @param semestre o semestre que o aluno se encontra.
	 * @param IEA      o indice de eficiencia academica do aluno.
	 */
	public void cadastraEspecialidadeAluno(int semestre, double IEA) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.verificaSemestre(semestre, "Atributo semestre com formato invalido.");
		ValidadorDeEntradas.verificaIEA(IEA, "Atributo IEA com formato invalido.");
		if (!(funcao.equalsIgnoreCase("Estudante"))) {
			throw new RuntimeException("Pesquisador nao compativel com a especialidade.");
		}
		this.especialidade = new Aluno(semestre, IEA);
	}

	/**
	 * Metodo que altera caracteristicas especificas de cada especialidade,
	 * recebendo qual atributo quer alterar e a nova versao desse atributo e nao
	 * retorna nada.
	 * 
	 * @param atributo     atributo que deve alterar.
	 * @param novoAtributo nova versao desse atributo.
	 */
	public void setEspecialidade(String atributo, String novoAtributo) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(atributo, "Campo atributo nao pode ser nulo ou vazio.");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoAtributo, "Campo novo atributo nao pode ser nulo ou vazio.");
		especialidade.setEspecialidade(atributo, novoAtributo);
	}
	
	/**
	 * Metodo que fornce uma string com a funcao desse Pesquisador e nao recebe nada
	 * como parametro.
	 * 
	 * @return string com a funcao desse Pesquisador.
	 */
	public String getFuncao() {
		return this.funcao;
	}
	
	/**
	 * Procura no atributo biografia do pesquisador a palavra-chave passada como
	 * parametro
	 * 
	 * @param palavraChave palavra-chave que sera pesquisada na biografia
	 * @return se a palavra-chave existir na String de biografia, essa string sera
	 *         retornada. Se nao, sera retornada uma String vazia
	 */
	public String procuraPalavraChave(String palavraChave) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(palavraChave, "Palavra nao pode ser nula ou vazia");
		if (biografia.contains(palavraChave)) {
			return this.email + ": " + biografia;
		}
		return "";
	}
}