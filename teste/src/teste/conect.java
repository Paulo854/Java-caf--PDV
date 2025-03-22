package teste;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class conect {
	public static boolean temConexao() {
	try {
        URL url = new URL("https://discord.com");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("HEAD");
        conexao.setConnectTimeout(3000); 
        conexao.setReadTimeout(3000);
        int codigoResposta = conexao.getResponseCode();
        return (codigoResposta >= 200 && codigoResposta < 400);
    } catch (IOException e) {
        return false; 
    	}
	}
}
