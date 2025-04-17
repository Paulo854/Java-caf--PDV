package inteface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import conect_banco.valida_login;
import controladores.controlador_operador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class vendas extends JFrame {

    public vendas() {
        // Estilo moderno
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));
        UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 13));
        UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 13));
        UIManager.put("Table.font", new Font("SansSerif", Font.PLAIN, 13));

        // Janela principal
        setTitle("Sistema de Vendas Moderno");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245)); // Fundo cinza-claro

        setLayout(new BorderLayout());

        // -------- PAINEL CENTRAL --------
        JPanel painelCentro = new JPanel(new GridLayout(1, 2));
        painelCentro.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelCentro.setBackground(new Color(245, 245, 245));

        // -------- PAINEL ESQUERDO (Cardápio) --------
        JPanel painelEsquerdo = new JPanel(new BorderLayout(10, 10));
        painelEsquerdo.setBackground(new Color(245, 245, 245));

        // Cardápio
        JPanel painelCardapio = new JPanel(new GridLayout(10, 1, 5, 5));
        painelCardapio.setBorder(BorderFactory.createTitledBorder("Cardápio"));
        painelCardapio.setBackground(new Color(245, 245, 245));
        for (int i = 1; i <= 10; i++) {
            JButton botao = new JButton("Item " + i);
            botao.setBackground(new Color(220, 230, 240));
            botao.setFocusPainted(false);
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            painelCardapio.add(botao);
        }

        // ADD Pedido
        JPanel painelAdd = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Pedido"));
        painelAdd.setBackground(new Color(245, 245, 245));
        painelAdd.add(new JLabel("Item:"));
        painelAdd.add(new JTextField(6));
        painelAdd.add(new JLabel("Qtd:"));
        painelAdd.add(new JTextField(3));
        painelAdd.add(estilizarBotao("Adicionar"));
        painelAdd.add(estilizarBotao("Cancelar"));

        // Detalhes
        JPanel painelDetalhes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes do Pedido"));
        painelDetalhes.setBackground(new Color(245, 245, 245));
        painelDetalhes.add(new JLabel("Informações aqui..."));

        // Organiza lado esquerdo
        painelEsquerdo.add(painelCardapio, BorderLayout.CENTER);

        JPanel painelInferiorEsquerdo = new JPanel(new BorderLayout(10, 10));
        painelInferiorEsquerdo.setBackground(new Color(245, 245, 245));
        painelInferiorEsquerdo.add(painelAdd, BorderLayout.NORTH);
        painelInferiorEsquerdo.add(painelDetalhes, BorderLayout.CENTER);
        painelEsquerdo.add(painelInferiorEsquerdo, BorderLayout.SOUTH);

        // -------- PAINEL DIREITO (Pedidos + Total) --------
        JPanel painelDireito = new JPanel(new BorderLayout(10, 10));
        painelDireito.setBackground(new Color(245, 245, 245));

        // Tabela
        String[] colunas = {"Item", "Qtd", "Preço"};
        Object[][] dados = {
                {"Café", "2", "R$ 10"},
                {"Bolo", "1", "R$ 7"}
        };
        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);
        estilizarTabela(tabela);
        painelDireito.add(scroll, BorderLayout.CENTER);

        // Total
        JPanel painelTotal = new JPanel(new BorderLayout());
        painelTotal.setBorder(BorderFactory.createTitledBorder("Resumo"));
        painelTotal.setBackground(new Color(245, 245, 245));

        JLabel totalLabel = new JLabel("Total: R$ 17");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        JButton removerBtn = estilizarBotao("Remover Itens");

        painelTotal.add(totalLabel, BorderLayout.WEST);
        painelTotal.add(removerBtn, BorderLayout.EAST);

        painelDireito.add(painelTotal, BorderLayout.SOUTH);

        painelCentro.add(painelEsquerdo);
        painelCentro.add(painelDireito);

        // -------- RODAPÉ --------
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(new Color(230, 230, 230));
        rodape.setBorder(new EmptyBorder(10, 10, 10, 10));
        rodape.add(estilizarBotao("Confirmar"));
        rodape.add(estilizarBotao("Gerar Comanda"));

        // -------- ADD NA JANELA --------
        add(painelCentro, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

    }

    // Botão customizado
    private JButton estilizarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(100, 149, 237));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(130, 30));
        return botao;
    }

    // Tabela customizada
    private void estilizarTabela(JTable tabela) {
        tabela.setRowHeight(25);
        tabela.setGridColor(new Color(200, 200, 200));
        tabela.setShowGrid(true);

        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        header.setBackground(new Color(200, 220, 240));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                valida_login banco = new valida_login();
                controlador_operador operador = new controlador_operador();
                banco.deletarPDV(operador.getNumberOperador());
                System.exit(0);
            }
        });
    }
}
