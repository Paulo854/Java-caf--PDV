package zona_teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TesteConexaoMySQL {
		
			public boolean conect() {
		        String url = "jdbc:mysql://brbgahf9wursozxo0tak-mysql.services.clever-cloud.com:3306/brbgahf9wursozxo0tak";
		        String usuario = "uergyxu498ygdwjf";
		        String senha = "8frxCjFJCP4xzJUEPssm";

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