package teste;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class discord {

	    private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1101930370070499398/xZ9Ef6KNeKyaM2zW1NbVFJ0sQixY_Z00UMBMv11fwD9jZlGqMBjXb9nZwHC5t49VRS36";

	    public static void enviarEmbed() {
	        try {
	            URL url = new URL(WEBHOOK_URL);
	            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
	            conexao.setRequestMethod("POST");
	            conexao.setRequestProperty("Content-Type", "application/json");
	            conexao.setDoOutput(true);

	            
	            String jsonPayload = """
	            {
	              "username": "Java&Café",
	              "avatar_url": "https://media.discordapp.net/attachments/1225809616978313348/1352467083266625680/logo1.png?ex=67de1ea0&is=67dccd20&hm=6fec439ee31d25a9b4fbaff6f4bf7ec30260209c396286bcec708b98ee7df748&=&format=webp&quality=lossless&width=960&height=960",
	              "embeds": [{
	                "title": "Pedidos Java&Café",
	                "description": "Olá, um pedido novo foi realizado",
	                "color": 16711680,
	                "fields": [
	                  {
	                    "name": "Café",
	                    "value": "Sem açúcar",
	                    "inline": true
	                  },
	                  {
	                    "name": "Café",
	                    "value": "Com açúcar",
	                    "inline": true
	                  }
	                ],
	                "footer": {
	                  "text": "Desenvolvido por Paulo <3",
	                  "icon_url": "https://media.discordapp.net/attachments/1225809616978313348/1352467083266625680/logo1.png?ex=67de1ea0&is=67dccd20&hm=6fec439ee31d25a9b4fbaff6f4bf7ec30260209c396286bcec708b98ee7df748&=&format=webp&quality=lossless&width=960&height=960"
	                }
	              }]
	            }
	            """;

	            try (OutputStream output = conexao.getOutputStream()) {
	                output.write(jsonPayload.getBytes());
	                output.flush();
	            }

	            int resposta = conexao.getResponseCode();
	            if (resposta == 204) {
	                System.out.println("Embed enviado com sucesso!");
	            } else {
	                System.out.println("Erro ao enviar Embed: Código " + resposta);
	            }

	        } catch (Exception e) {
	            System.out.println("Erro: " + e.getMessage());
	        }
	    }
		
	}

