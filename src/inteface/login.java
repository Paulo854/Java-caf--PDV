package inteface;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import conect_banco.TesteConexaoMySQL;
import conect_banco.valida_login;
import conexao_controle.conect_internet;
import conexao_controle.discord_entrada_caixa;
import conexao_controle.discord_erro_pdv;
import conexao_controle.discord_pedidos;
import controladores.controlador_login_system;
import controladores.controlador_operador;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class login extends JFrame implements ActionListener{
	public JLabel date;
	public JLabel logoIcon;
	public JLabel lblLogin;
	public JTextField txtLogin;
	public JLabel lblSenha;
	public JPasswordField txtSenha;
    public Timer timer;
    public static int matriculaGerencia, senhaGerencia; 
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public discord_entrada_caixa discord_entrada = new discord_entrada_caixa();
    public JFrame parentFrame; 
	
	public login() {
		
		setUndecorated(true);
		
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setTitle("Java&Café");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon_bar.png"))); // chama imagem para aparecer na barra de tarefas


        
        JPanel panelBTN = new JPanel();
        panelBTN.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> solicitarSenhaParaFechar());
        
        panelBTN.add(closeButton);
        add(panelBTN, BorderLayout.NORTH);

        
        JPanel panelDate = new JPanel();
        panelDate.setLayout(new FlowLayout(FlowLayout.RIGHT));

        date = new JLabel();
        panelDate.add(date);

        add(panelDate, BorderLayout.SOUTH);
             
        
        // Painel central que vai conter a logo e o txtLogin centralizados
        JPanel centro = new JPanel(new GridBagLayout()); // Centraliza conteúdo
        centro.setOpaque(false); // Fundo transparente pra aparecer o fundo da tela

        // Painel interno com logo
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.X_AXIS));
        iconPanel.setOpaque(false);

        // Logo
        logoIcon = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("logo1.png"));

        // Redimensiona a imagem, se necessário
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        logoIcon.setIcon(new ImageIcon(img));
        logoIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // configurações de deslocamento dos campos de input
        GridBagConstraints info = new GridBagConstraints();
        info.insets = new Insets(0, 50, 0, 0); // top, left, bottom, right
        
        // Painel interno com login e senha
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        // Painel interno com login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));
        loginPanel.setOpaque(false);
        
               
        
        // Campo de login
        lblLogin = new JLabel();
        lblLogin.setText("LOGIN:  ");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 20));

        txtLogin = new JTextField(20);
        txtLogin.setMaximumSize(new Dimension(200, 30)); // Limita o tamanho
        txtLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Painel interno com senha
        JPanel senhaPanel = new JPanel();
        senhaPanel.setLayout(new BoxLayout(senhaPanel, BoxLayout.X_AXIS));
        senhaPanel.setOpaque(false);
        
        
        // Campo de senha
        lblSenha = new JLabel();
        lblSenha.setText("SENHA: ");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 20));

        txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(200, 30)); // Limita o tamanho
        txtSenha.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel botaoPanel = new JPanel();
        botaoPanel.setOpaque(false);
        botaoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //botão
        JButton btn_logar = new JButton("Entrar");
        btn_logar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn_logar.setPreferredSize(new Dimension(200, 30));
        btn_logar.addActionListener(e -> validarLogin(txtLogin.getText(), txtSenha.getText()));


        // Adiciona aos painéis criados
        iconPanel.add(logoIcon);
        //iconPanel.add(Box.createHorizontalStrut(20)); // Espaço entre logo e input
        loginPanel.add(lblLogin);
        loginPanel.add(txtLogin);
        senhaPanel.add(lblSenha);
        senhaPanel.add(txtSenha);
        botaoPanel.add(btn_logar);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(loginPanel);
        infoPanel.add(senhaPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(botaoPanel);

        // Adiciona ao centro com GridBagConstraints para centralizar
        centro.add(iconPanel, new GridBagConstraints());
        centro.add(infoPanel, info);
        infoPanel.add(Box.createVerticalStrut(20)); // Espaço entre logo e input

        // Adiciona o painel central à janela
        add(centro, BorderLayout.CENTER);

        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Alt + F4 bloqueado!"); 
            }
        });
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime agora = LocalDateTime.now();
                date.setText(agora.format(formatter));
            }
        });

        timer.start();
	}
	
	private void validarLogin(String mat, String senha) {
	    try {	        
	        matriculaGerencia = Integer.parseInt(mat); 
	        senhaGerencia = Integer.parseInt(senha); 
	        valida_login validaBanco = new valida_login();
	        controlador_operador operador = new controlador_operador();
	        discord_entrada_caixa entradaCaixa = new discord_entrada_caixa();
	        vendas vender = new vendas();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        	LocalDateTime agora = LocalDateTime.now();
            String data = agora.format(formatter);
	        int filial;
	        int matricula;
	        double money;

	        int resultado = validaBanco.verificaLogin(matriculaGerencia, senhaGerencia);

	        if (resultado == 1) {
	            JOptionPane.showMessageDialog(null, "Os dados de liberação foram gravados");

	            while (true) {
	                try {
	                    matricula = Integer.parseInt(JOptionPane.showInputDialog("Qual a matrícula do operador?"));
	                    money = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor de lastro do caixa?"));

	                    String nomeOperador = validaBanco.getNomeFuncionario(matricula);
	                    filial = validaBanco.getFilial(matricula);

	                    if (nomeOperador != null && matricula != 0) {
	                        operador.setNomeOperador(nomeOperador);
	                        operador.setNumberOperador(matricula);

	                        int result = JOptionPane.showConfirmDialog(null,
	                                "Este PDV será aberto para " + nomeOperador + " com um valor de: R$" + String.format("%.2f", money),
	                                "Confirmação",
	                                JOptionPane.OK_CANCEL_OPTION);

	                        if (result == JOptionPane.OK_OPTION) {
	                            validaBanco.setPDV(nomeOperador, filial, money, matricula, matriculaGerencia);
	                            
	                          if(filial == 1001) {
	                            	entradaCaixa.enviarEmbed("Abertura caixa", "Caixa da filial do(a) Paulista aberto valor **R$"+money+"0**", validaBanco.getNomeGerenciaMatricula(validaBanco.getNumberGerenciaPDV(operador.getNumberOperador())), data, validaBanco.getNomeFuncionario(matricula), "https://discord.com/api/webhooks/1362588815562379466/5IwbPRaz2aDUryugBvJS5F3EwTDFvQfzWl5Lt7PlkNuVCNwMAIOgvvqdq1EQHl8OgQL7");
	                            	vender.setVisible(true);
		                            setVisible(false);
	                          }else if(filial == 1002) {
	                            	entradaCaixa.enviarEmbed("Abertura caixa", "Caixa da filial do(a) Jd Ângela aberto valor **R$"+money+"0**", validaBanco.getNomeGerenciaMatricula(validaBanco.getNumberGerenciaPDV(operador.getNumberOperador())), data, validaBanco.getNomeFuncionario(matricula), "https://discord.com/api/webhooks/1362589263077576926/CNkZrr49Q0fzPECxO1eYPXohrCHHr02F_1X6_tX_62Gc7DAbN4KRvxsAQKZ44BgJLrjZ");
	                            	vender.setVisible(true);
		                            setVisible(false);
	                          }else if(filial == 1003) {
	                            	entradaCaixa.enviarEmbed("Abertura caixa", "Caixa da filial do(a) Liberdade aberto valor **R$"+money+"0**", validaBanco.getNomeGerenciaMatricula(validaBanco.getNumberGerenciaPDV(operador.getNumberOperador())), data, validaBanco.getNomeFuncionario(matricula), "https://discord.com/api/webhooks/1362589310129405973/dDY4uwLyqbBH2BEdAXRuLj3ejG4B1uDvSPPG_N35WUeQXbUy2qAr281jxhYE0PSByWn8");
	                            	vender.setVisible(true);
		                            setVisible(false);
	                          }
	                        } else {
	                            JOptionPane.showMessageDialog(null, "A operação foi cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
	                        }
	                        break;
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Matrícula inválida. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Digite valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
	                }
	            }

	        } else if (resultado == 0) {
	            JOptionPane.showMessageDialog(null, "Os dados informados estão incorretos");
	        } else {
	            JOptionPane.showMessageDialog(null, "Erro ao se conectar ao banco de dados");
	        }

	    } catch (NumberFormatException ex) {

	        JOptionPane.showMessageDialog(null, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
	        
	    } catch (Exception e) {
	    	discord_erro_pdv erroDiscord = new discord_erro_pdv();
	    	 String tipoErro = e.getClass().getSimpleName();
	    	 String mensagemErro = e.getMessage();
	    	 String resumo = "Erro [" + tipoErro + "]: " + mensagemErro;
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            	LocalDateTime agora = LocalDateTime.now();
                String data = agora.format(formatter);
	    	 erroDiscord.enviarEmbed("Erro desconhecido",resumo, "login() -> validarLogin()", "Alta", data, "Segurança PDVs", "https://discord.com/api/webhooks/1361852916377583756/fkqRCJIayPCiVB69CvKTCqM6k8xCuNJA4P5fPmnICrnvcCLXEfwJnKzp6a3Eg4qkKa_i");
	    	
	        JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	private void solicitarSenhaParaFechar() {;
        valida_login validaBanco = new valida_login();
        controlador_operador operador = new controlador_operador();
        while (true) {
        	
            String input = JOptionPane.showInputDialog("Para executar essa ação, informe a senha:");
            if (input == null) { 
                return;
            }

            try {
                int senha = Integer.parseInt(input);
                int resultado = validaBanco.fecharPDV(senha);
                if (resultado == 1) {
                	 LocalDateTime agora = LocalDateTime.now();
                	discord_entrada_caixa.enviarEmbed("Sistema fechado", "Uma filial fechou o PDV", "**"+validaBanco.getNomeGerencia(senha)+"**", agora.format(formatter), "Segurança PDVs", "https://discord.com/api/webhooks/1358563763598393356/O5REd2sYuHPZwmL_tIIBadk5pKYk0Uj5QMwUA0sH2LcIiybc1LvicLNHOBOOj9oNI3Vu");
                	if(operador.getNomeOperador() != null) {
                		validaBanco.deletarPDV(operador.getNumberOperador());
                	}
                    System.exit(0);
                } else {
                	 LocalDateTime agora = LocalDateTime.now();
                	discord_entrada_caixa.enviarEmbed("Tentativa de fechar aplicação", "indetificamos que uma filial tentou fechar o PDV", "foi identificado uma senha: **"+senha+"**", agora.format(formatter), "Segurança PDVs", "https://discord.com/api/webhooks/1358563763598393356/O5REd2sYuHPZwmL_tIIBadk5pKYk0Uj5QMwUA0sH2LcIiybc1LvicLNHOBOOj9oNI3Vu");
                    JOptionPane.showMessageDialog(null, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }            
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
