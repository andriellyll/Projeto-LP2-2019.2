package pacote;

public class Pesquisador {
	
	private String nome;
	private String biografia;
	private String email;
	private String foto;
	private String funcao;
	private boolean isAtivo;
	
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(nome, "Campo nome não pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(funcao, "Campo funcao não pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(biografia, "Campo biografia não pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(email, "Campo email não pode ser nulo ou vazio");
		ValidadorDeEntradas.validaEntradaNulaOuVazia(fotoUrl, "Campo fotoUrl não pode ser nulo ou vazio");
		ValidadorDeEntradas.verificaEmail(email);
		ValidadorDeEntradas.verificaURL(fotoUrl);

		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = fotoUrl;
		this.funcao = funcao;
		this.isAtivo = true;
	}
	
	public void setNome(String novoNome) { 
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoNome, "Campo nome não pode ser nulo ou vazio");

		this.nome = novoNome;
	}
	
	public void setBiografia(String novaBiografia) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaBiografia, "Campo biografia não pode ser nulo ou vazio");
		
		this.biografia = novaBiografia;
	}
	
	public void setEmail(String novoEmail) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novoEmail, "Campo email não pode ser nulo ou vazio");
		
		this.email = novoEmail;
	}
	
	public void setFoto(String novaFoto) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaFoto, "Campo fotoUrl não pode ser nulo ou vazio");
			
		this.foto = novaFoto;
	}
	
	public void setFuncao(String novaFuncao) {
		ValidadorDeEntradas.validaEntradaNulaOuVazia(novaFuncao, "Campo funcao não pode ser nulo ou vazio");
		
		this.funcao = novaFuncao;
	}
	
	public void desativaPesquisador() {
		this.isAtivo = false;
	}
	
	public void ativaPesquisador() {
		this.isAtivo = true;
	}
	
	public String toString() {
		return this.nome + " (" + this.funcao + ")" + " - " + this.biografia + " - " + this.email + " - " + this.foto;
	}
	
	public boolean ehAtivo() {
		return this.isAtivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

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
}