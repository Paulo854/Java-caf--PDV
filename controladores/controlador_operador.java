package controladores;

import javax.swing.JOptionPane;

public class controlador_operador {

	private static int opN;
	private static String nome;
	private double money;
	
	public controlador_operador() {
		
	}
	public void setNumberOperador(int number) {
		this.opN = number;
	}
	public int getNumberOperador() {
		return this.opN;
	}
	public void setNomeOperador(String nome) {
		this.nome = nome;
	}
	public String getNomeOperador() {
		return this.nome;
	}
	public void pagamentoDinheiro(double dinheiro) {
		this.money = dinheiro + this.money;
	}
	public double fechamentoCaixa() {
		return this.money;
	}
	public void entradaCaixa(double dinheiro) {
		if(dinheiro >= 101) {
			JOptionPane.showMessageDialog(null, "O caixa não pode abrir com esse valor");
		}else {
			this.money = dinheiro;
		}
	}
	
}
