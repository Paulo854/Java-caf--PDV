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
		String cafe = null, 
				leite = null, 
				acucar = null, 
				title = "Um pedido novo foi registrado", 
				link = "https://discord.com/api/webhooks/1101930370070499398/xZ9Ef6KNeKyaM2zW1NbVFJ0sQixY_Z00UMBMv11fwD9jZlGqMBjXb9nZwHC5t49VRS36"; 
		//Chama interface 
		iniciar.setVisible(true);
		//Variáveis
		boolean closeSystem = false;
		
		while(closeSystem != true) {
		
		
		
		 if (internet.temConexao()) {
	            //System.out.println("Conectado à internet.");
			 int mat = 0;
			 if(mat == 0) {
	            mat =  Integer.parseInt(JOptionPane.showInputDialog("Matricula do operador?"));
	    		
	    		caixa.setNumberOperador(mat);
	    		
	    		if(caixa.getNumberOperador() == 375834) {
	    			caixa.setNomeOperador("Paulo Victor Nascimento Cardoso");
	    			double dinheiro = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor do caixa?"));
	    			if(dinheiro == 100) {
	    				int resp = JOptionPane.showConfirmDialog(null, "O caixa será aberto para:"+caixa.getNomeOperador()+"\ncom um valor de R$"+dinheiro+"0\n deseja confimar?");
		    			if(resp == JOptionPane.YES_OPTION) {
		    				caixa.entradaCaixa(dinheiro);
		    				JOptionPane.showMessageDialog(null, "Dados gravados\ncaixa aberto :)");
		    				cafe = JOptionPane.showInputDialog("Qual será o seu café?");
		    				leite = JOptionPane.showInputDialog("Deseja adcionar leite a sua bebida?");
		    				acucar = JOptionPane.showInputDialog("Deseja açúcar ou adoçante?");
		    				closeSystem = true;
		    			}else if(resp == JOptionPane.NO_OPTION){
		    				closeSystem = false;
		    			}else if(resp == JOptionPane.CLOSED_OPTION) {
		    				closeSystem = false;
		    			}else if(resp == JOptionPane.CANCEL_OPTION) {
		    				closeSystem = false;
		    			}
		    			
		    				}else {
		    					JOptionPane.showMessageDialog(null, "O lastro do caixa deve ser R$100,00");
		    				} 
	    				}else {
	    					JOptionPane.showMessageDialog(null, "Operador não encontrado");
	    				}
	    		}
	            discord.enviarEmbed(title,cafe, leite, acucar, caixa.getNomeOperador(), link);
	        } else {
	            System.out.println("Sem conexão com a internet.");
	        }
	    }
	}
}