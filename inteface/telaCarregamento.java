package inteface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.nio.channels.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.JWindow;

import conexao_controle.conect_internet;
import zona_teste.TesteConexaoMySQL;


public class telaCarregamento extends JWindow { // Extende JWindow ou JFrame

    private JProgressBar barraProgresso;
    private JLabel statusLabel;
    public conect_internet conect = new conect_internet();
    public TesteConexaoMySQL banco = new TesteConexaoMySQL();
    private static FileLock lock;
    private static FileChannel channel;		

    public telaCarregamento() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(40, 40, 40)); // fundo escuro

        // Logo no centro
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            BufferedImage imagem = ImageIO.read(getClass().getResource("logo1.png")); // Substitua pela sua logo
            Image redimensionada = imagem.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(redimensionada));
        } catch (IOException e) {
            logo.setText("LOGO");
            logo.setForeground(Color.WHITE);
        }

        // Barra de progresso
        barraProgresso = new JProgressBar(0, 100);
        barraProgresso.setForeground(Color.ORANGE);
        barraProgresso.setBackground(Color.DARK_GRAY);
        barraProgresso.setStringPainted(true);
        barraProgresso.setBorderPainted(false);

        // Texto de status
        statusLabel = new JLabel("Iniciando...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Layout final
        painel.add(logo, BorderLayout.CENTER);
        painel.add(barraProgresso, BorderLayout.SOUTH);
        painel.add(statusLabel, BorderLayout.NORTH);

        // Ajuste da tela
        int largura = 400;
        int altura = 250;
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((tela.width - largura) / 2, (tela.height - altura) / 2, largura, altura);

        setContentPane(painel);
        setVisible(true);

        // Começar carregamento
        iniciarCarregamento();
    }

    private void iniciarCarregamento() {
        SwingWorker<Void, String> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                String[] mensagens = {
                    "<html><font face='Segoe UI Emoji'>🚀 Verificando conexão com o banco de dados...</font></html>",
                    "<html><font face='Segoe UI Emoji'>⚙️ Carregando configurações...</font></html>",
                    "<html><font face='Segoe UI Emoji'>💥 Validando permissões...</font></html>",
                    "<html><font face='Segoe UI Emoji'>⚖️ Inicializando módulos...</font></html>",
                    "<html><font face='Segoe UI Emoji'>✅ Tudo pronto!</font></html>"
                };

                // verifica cada etapa
                for (int i = 0; i < mensagens.length; i++) {
                	//verifica as conexões
                    if (i == 0 && (!conect.temConexao() || !banco.conect())) {
                        publish("<html><font face='Segoe UI Emoji'>❌ Erro ao conectar ao banco de dados.</font></html>");
                        System.exit(0);
                        break; // Interrompe o loop se não houver conexão
                    }

                    publish(mensagens[i]);
                    Thread.sleep(1000); // Simulando o tempo de cada etapa
                    barraProgresso.setValue((i + 1) * 100 / mensagens.length);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                String mensagemAtual = chunks.get(chunks.size() - 1);
                statusLabel.setText(mensagemAtual);
            }

            @Override
            protected void done() {
                fechar();

                // Abrir tela principal
                SwingUtilities.invokeLater(() -> {
                    login lg = new login();
                    lg.setVisible(true);
                });
            }
        };

        worker.execute();
    }

    public void fechar() {
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new telaCarregamento();
        try {
            File file = new File("program.lock");
            channel = new RandomAccessFile(file, "rw").getChannel();
            lock = channel.tryLock();

            if (lock == null) {
                System.out.println("O programa já está em execução.");
                System.exit(1);
            }

            System.out.println("Programa rodando normalmente...");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lock != null) lock.release();
                if (channel != null) channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
