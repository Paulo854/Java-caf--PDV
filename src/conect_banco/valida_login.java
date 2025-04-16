package conect_banco;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class valida_login {	
	
	public int verificaLogin(int matricula, int senhaLogin) {
        String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
        String usuario = "urrerpo6lwwp9trh";
        String senha = "EYWBqRaV6CD016CndFtR";

        String sql = "select * from funcionario where matricula = ? and senha = ? and gerencia = 1 and nome";
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
        	 
        	stmt.setInt(1, matricula);
            stmt.setInt(2, senhaLogin);
            
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
            	conexao.close();
                 return 1;
            } else {
            	conexao.close();
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
	}	
        public int fecharPDV(int senhaLogin) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "select * from funcionario where senha = ? and gerencia = 1";
            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                    PreparedStatement stmt = conexao.prepareStatement(sql)) {
            	 
                stmt.setInt(1, senhaLogin);
                
                ResultSet resultado = stmt.executeQuery();
                
                if (resultado.next()) {
                	conexao.close();
                     return 1;
                } else {
                	conexao.close();
                    return 0;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
    }
        public String getNome(int senhaLogin) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT nome FROM funcionario WHERE senha = ? AND gerencia = 1";
            
            String nome = null;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, senhaLogin);

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    nome = resultado.getString("nome");
                    return nome;
                } else {
                    System.out.println("Funcionário não encontrado.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return nome;
    }
}
