package conexao_controle;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class conect_internet {
	public boolean temConexao() {
		try {
	        URL url = new URL("https://discord.com");
	        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
	        conexao.setRequestMethod("HEAD");
	        conexao.setConnectTimeout(3000); 
	        conexao.setReadTimeout(3000);
	        System.out.println("Sistema conectado a internet");
	        int codigoResposta = conexao.getResponseCode();
	        return (codigoResposta >= 200 && codigoResposta < 400);
	    } catch (IOException e) {
	    	System.out.println("Sistema sem conexão com a internet");
	        return false; 
	    	}
		}
}
