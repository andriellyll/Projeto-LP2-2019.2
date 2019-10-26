package pacote;

public class Pesquisador {
	
	private String nome;
	private String biografia;
	private String email;
	private String foto;
	private String funcao;
	private boolean isAtivo;
	
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoUrl) {
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.foto = fotoUrl;
		this.funcao = funcao;
		this.isAtivo = true;
	}
	
	public void setNome(String novoNome) { 
		this.nome = novoNome;
	}
	
	public void setBiografia(String novaBiografia) {
		this.biografia = novaBiografia;
	}
	
	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}
	
	public void setFoto(String novaFoto) {
		this.foto = novaFoto;
	}
	
	public void setFuncao(String novaFuncao) {
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
}