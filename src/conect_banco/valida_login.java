package conect_banco;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class valida_login {	
	
	public int verificaLogin(int matricula, int senhaLogin) {
        String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
        String usuario = "urrerpo6lwwp9trh";
        String senha = "EYWBqRaV6CD016CndFtR";

        String sql = "select * from funcionario where matricula = ? and senha = ? and gerencia = 1";
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
        public String getNomeGerencia(int senhaLogin) {
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
        public String getNomeFuncionario(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT nome FROM funcionario WHERE matricula = ?";
            
            String nome = null;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, matricula);

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
        
        public String getFilial(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT filial FROM funcionario WHERE matricula = ?";
            
            String filial = null;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, matricula);

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                	filial = resultado.getString("filial");
                    return filial;
                } else {
                    System.out.println("Funcionário não encontrado.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return filial;
    }
        
        public void setPDV(String nomeFuncionario, String filial, double money, int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String nome = null;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
                // Primeiro: buscar o maior número de PDV atual
                String consultaPDV = "SELECT MAX(pdv) AS max_pdv FROM caixas_abertos";
                int novoPDV = 1; // padrão caso não haja nenhum registro

                try (PreparedStatement stmtConsulta = conexao.prepareStatement(consultaPDV);
                     ResultSet resultado = stmtConsulta.executeQuery()) {

                    if (resultado.next()) {
                        int maxPDV = resultado.getInt("max_pdv");
                        if (!resultado.wasNull()) {
                            novoPDV = maxPDV + 1;
                        }
                    }
                }

                // Agora, faz o INSERT com o novo PDV
                String sql = "INSERT INTO caixas_abertos (funcionario, filial, qtd_dinheiro, pdv, matricula) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement stmtInsercao = conexao.prepareStatement(sql)) {
                    stmtInsercao.setString(1, nomeFuncionario);
                    stmtInsercao.setString(2, filial);
                    stmtInsercao.setDouble(3, money);
                    stmtInsercao.setInt(4, novoPDV);
                    stmtInsercao.setInt(5, matricula);

                    int linhasAfetadas = stmtInsercao.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.print("Este PDV está registrado no Java&Café");
                    } else {
                        System.out.println("Falha ao inserir o registro.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
      }
        public boolean deletarPDV(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "DELETE FROM caixas_abertos WHERE matricula = ?";

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, matricula);

                int linhasAfetadas = stmt.executeUpdate();

                return linhasAfetadas > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }
}
