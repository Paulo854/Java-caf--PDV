package controladores;

public class controlador_login_system {

	private static int matricula = 0;
	private static int senha = 0;
	
	public controlador_login_system() {	
		
	}
	public void setNumberMatricula(int number) {
		this.matricula = number;
	}
	public int getMatricula() {
		return this.matricula;
	}
	public void setSenha(int number) {
		this.senha = senha;
	}
	public int getSenha() {
		return this.senha;
	}
}
