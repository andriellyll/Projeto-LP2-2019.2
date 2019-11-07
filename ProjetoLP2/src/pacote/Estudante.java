package pacote;

public class Estudante implements Funcao{
	private int semestre;
	private double IAE;
	
	public Estudante() {
		this.semestre = 0;
		this.IAE = 0;
	}
	
	public Estudante(int semestre, double IEA) {
		this.semestre = semestre;
		this.IAE = IEA;
	}
	/*
	public void cadastraEspecialidade(int semestre, double IEA) {
		this.semestre = semestre;
		this.IAE = IEA;
	}
	*/
}
