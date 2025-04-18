package conect_banco;

import javax.swing.*;

import conexao_controle.discord_erro_pdv;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class valida_login {	
	public String ipPC;
	
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
                	JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return nome;
    }
        public String getNomeGerenciaMatricula(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT nome FROM funcionario WHERE matricula = ? AND gerencia = 1";
            
            String nome = null;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, matricula);

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                    nome = resultado.getString("nome");
                    return nome;
                } else {
                	JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "error", JOptionPane.ERROR_MESSAGE);
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
                	JOptionPane.showMessageDialog(null, "Funcionário não encontrado", "error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return nome;
    }
        
        public int getFilial(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT filial FROM funcionario WHERE matricula = ?";
            
            int filial = 0;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, matricula);

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                	filial = resultado.getInt("filial");
                    return filial;
                } else {
                	JOptionPane.showMessageDialog(null, "Filial não localizada", "error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return filial;
    }
        public int getNumberGerenciaPDV(int matricula) {
            String url = "jdbc:mysql://boen8rx43tg50tzvfbdx-mysql.services.clever-cloud.com:3306/boen8rx43tg50tzvfbdx";
            String usuario = "urrerpo6lwwp9trh";
            String senha = "EYWBqRaV6CD016CndFtR";

            String sql = "SELECT matriculaGerencia FROM caixas_abertos WHERE matricula = ?";
            
            int matriculaGerencia = 0;

            try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

            	 stmt.setInt(1, matricula);

                ResultSet resultado = stmt.executeQuery();

                if (resultado.next()) {
                	matriculaGerencia = resultado.getInt("matriculaGerencia");
                    return matriculaGerencia;
                } else {
                	JOptionPane.showMessageDialog(null, "Membro gerencial não localizado", "error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return matriculaGerencia;
    }
        
        public void setPDV(String nomeFuncionario, int filial, double money, int matricula, int matriculaGerencia) {
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

               
                String sql = "INSERT INTO caixas_abertos (funcionario, filial, qtd_dinheiro, pdv, matricula, ip, matriculaGerencia) VALUES (?, ?, ?, ?, ?, ?, ?)";

                
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    ipPC = ip.getHostAddress();
                } catch (UnknownHostException e) {
                    System.err.println("Não foi possível obter o endereço IP.");
                    e.printStackTrace();
                }
                try (PreparedStatement stmtInsercao = conexao.prepareStatement(sql)) {
                	
                	
                	
                    stmtInsercao.setString(1, nomeFuncionario);
                    stmtInsercao.setInt(2, filial);
                    stmtInsercao.setDouble(3, money);
                    stmtInsercao.setInt(4, novoPDV);
                    stmtInsercao.setInt(5, matricula);
                    stmtInsercao.setString(6,  ipPC);
                    stmtInsercao.setInt(7, matriculaGerencia);

                    int linhasAfetadas = stmtInsercao.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.print("Este PDV está registrado no Java&Café");
                    } else {
                    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    	LocalDateTime agora = LocalDateTime.now();
                        String data = agora.format(formatter);
                    	discord_erro_pdv discordErro = new discord_erro_pdv();
                    	discordErro.enviarEmbed("Conexão do PDV","o PDV não está sendo registrado no banco de dados", "valida_login() -> setPDV()", "Alta", data, "Segurança PDVs", "https://discord.com/api/webhooks/1361852916377583756/fkqRCJIayPCiVB69CvKTCqM6k8xCuNJA4P5fPmnICrnvcCLXEfwJnKzp6a3Eg4qkKa_i");
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
