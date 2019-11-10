package pacote;

public class Aluno implements Funcao{
	private int semestre;
	private double IEA;
	
	
	public Aluno(int semestre, double IEA) {
		ValidadorDeEntradas.verificaSemestre(semestre, "Atributo semestre com formato invalido.");
		ValidadorDeEntradas.verificaIEA(IEA, "Atributo IEA com formato invalido.");
		
		this.semestre = semestre;
		this.IEA = IEA;
	}


	@Override
	public String toString(String nome, String funcao, String biografia, String email, String foto) {
		return nome + " (" + funcao + ")" + " - " + biografia + " - " + email + " - " + foto +  " - " +  this.semestre+  "o SEMESTRE - " +  this.IEA;
	}


	@Override
	public void setEspecialidade(String atributo, String novoAtributo) {
		if(atributo.equalsIgnoreCase("semestre")) {
			this.semestre = Integer.parseInt(novoAtributo);
		}else if(atributo.equalsIgnoreCase("IEA")) {
			this.IEA = Double.parseDouble(novoAtributo);
		}else {
			throw new RuntimeException("Atributo invalido.");
		}
		
	}
}