package zona_teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TesteConexaoMySQL {
		
			public boolean conect() {
		        String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
		        String usuario = "urrerpo6lwwp9trh";
		        String senha = "EYWBqRaV6CD016CndFt";

		        try {
		            Connection conexao = DriverManager.getConnection(url, usuario, senha);
		            System.out.println("Conectado aos serviços Java&Café!");
		            conexao.close();
		            return true;
		        } catch (SQLException e) {
		            System.out.println("Erro ao conectar ao banco de dados:");
		            e.printStackTrace();
		            return false;
		        }
		  }
}