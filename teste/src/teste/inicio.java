package teste;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;




public class inicio extends JFrame{
	
	public inicio() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        
    }
	
	
	
        
      
	public static void main(String[] args) {
		//imports internos
		discord discord = new discord();
		conect internet = new conect();
		controlador caixa = new controlador();
		inicio iniciar = new inicio();
		//Chama interface 
		iniciar.setVisible(true);
		//Variáveis
		boolean closeSystem = false;
		
		
		double entrada = Double.parseDouble(JOptionPane.showInputDialog("valor do caixa?"));
		
		caixa.entradaCaixa(entrada);
		iniciar.setVisible(false);
		
		
		
		while(closeSystem != true) {
		 if (internet.temConexao()) {
	            System.out.println("Conectado à internet.");
	            closeSystem = true;
	    		//discord.enviarEmbed();
	        } else {
	            System.out.println("Sem conexão com a internet.");
	        }
	    }
	}
}