package com.calculadora.view;

import com.calculadora.controller.CalculadoraController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View da Calculadora - Interface Gráfica usando Swing.
 * Implementa o padrão MVC (Model-View-Controller).
 */
public class CalculadoraView extends JFrame {
    
    private CalculadoraController controller;
    
    // Componentes da interface
    private JTextField displayField;
    private JButton[] botoesNumeros;
    private JButton[] botoesOperacoes;
    private JButton botaoIgual;
    private JButton botaoLimpar;
    private JButton botaoDeletar;
    
    // Variáveis de controle
    private double primeiroOperando;
    private String operacaoSelecionada;
    private boolean novoNumero = true;
    
    public CalculadoraView(CalculadoraController controller) {
        this.controller = controller;
        inicializarInterface();
    }
    
    /**
     * Inicializa todos os componentes da interface gráfica.
     */
    private void inicializarInterface() {
        setTitle("Calculadora MVC - Com Polimorfismo e Reflection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        
        // Painel do display
        JPanel painelDisplay = criarPainelDisplay();
        add(painelDisplay, BorderLayout.NORTH);
        
        // Painel dos botões
        JPanel painelBotoes = criarPainelBotoes();
        add(painelBotoes, BorderLayout.CENTER);
        
        // Painel de informações
        JPanel painelInfo = criarPainelInfo();
        add(painelInfo, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    /**
     * Cria o painel do display da calculadora.
     */
    private JPanel criarPainelDisplay() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setText("0");
        displayField.setBackground(Color.BLACK);
        displayField.setForeground(Color.WHITE);
        
        painel.add(displayField, BorderLayout.CENTER);
        return painel;
    }
    
    /**
     * Cria o painel com todos os botões.
     */
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(6, 4, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Botão Limpar
        botaoLimpar = criarBotao("C", new Color(220, 53, 69));
        botaoLimpar.addActionListener(e -> limpar());
        painel.add(botaoLimpar);
        
        // Botão Deletar
        botaoDeletar = criarBotao("DEL", new Color(220, 53, 69));
        botaoDeletar.addActionListener(e -> deletarUltimoDigito());
        painel.add(botaoDeletar);
        
        // Espaço vazio
        painel.add(new JLabel());
        
        // Botão Divisão
        JButton botaoDivisao = criarBotao("/", new Color(255, 193, 7));
        botaoDivisao.addActionListener(e -> selecionarOperacao("/"));
        painel.add(botaoDivisao);
        
        // Botões numéricos 7, 8, 9
        adicionarBotoesNumericos(painel, 7, 9);
        
        // Botão Multiplicação
        JButton botaoMultiplicacao = criarBotao("*", new Color(255, 193, 7));
        botaoMultiplicacao.addActionListener(e -> selecionarOperacao("*"));
        painel.add(botaoMultiplicacao);
        
        // Botões numéricos 4, 5, 6
        adicionarBotoesNumericos(painel, 4, 6);
        
        // Botão Subtração
        JButton botaoSubtracao = criarBotao("-", new Color(255, 193, 7));
        botaoSubtracao.addActionListener(e -> selecionarOperacao("-"));
        painel.add(botaoSubtracao);
        
        // Botões numéricos 1, 2, 3
        adicionarBotoesNumericos(painel, 1, 3);
        
        // Botão Adição
        JButton botaoAdicao = criarBotao("+", new Color(255, 193, 7));
        botaoAdicao.addActionListener(e -> selecionarOperacao("+"));
        painel.add(botaoAdicao);
        
        // Botão 0
        JButton botaoZero = criarBotao("0", new Color(108, 117, 125));
        botaoZero.addActionListener(e -> adicionarDigito("0"));
        painel.add(botaoZero);
        
        // Botão Ponto Decimal
        JButton botaoPonto = criarBotao(".", new Color(108, 117, 125));
        botaoPonto.addActionListener(e -> adicionarDigito("."));
        painel.add(botaoPonto);
        
        // Espaço vazio
        painel.add(new JLabel());
        
        // Botão Igual
        botaoIgual = criarBotao("=", new Color(40, 167, 69));
        botaoIgual.addActionListener(e -> calcularResultado());
        painel.add(botaoIgual);
        
        return painel;
    }
    
    /**
     * Adiciona botões numéricos ao painel.
     */
    private void adicionarBotoesNumericos(JPanel painel, int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            JButton botao = criarBotao(String.valueOf(i), new Color(108, 117, 125));
            final int numero = i;
            botao.addActionListener(e -> adicionarDigito(String.valueOf(numero)));
            painel.add(botao);
        }
    }
    
    /**
     * Cria um botão com estilo padrão.
     */
    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return botao;
    }
    
    /**
     * Cria o painel de informações.
     */
    private JPanel criarPainelInfo() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        painel.setBackground(new Color(240, 240, 240));
        
        JLabel label = new JLabel("Calculadora com MVC, Polimorfismo e Reflection");
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        painel.add(label);
        
        return painel;
    }
    
    /**
     * Adiciona um dígito ao display.
     */
    private void adicionarDigito(String digito) {
        String texto = displayField.getText();
        
        if (novoNumero) {
            if (!digito.equals(".")) {
                displayField.setText(digito);
                novoNumero = false;
            }
        } else {
            if (digito.equals(".")) {
                if (!texto.contains(".")) {
                    displayField.setText(texto + digito);
                }
            } else {
                displayField.setText(texto + digito);
            }
        }
    }
    
    /**
     * Deleta o último dígito do display.
     */
    private void deletarUltimoDigito() {
        String texto = displayField.getText();
        if (texto.length() > 1) {
            displayField.setText(texto.substring(0, texto.length() - 1));
        } else {
            displayField.setText("0");
            novoNumero = true;
        }
    }
    
    /**
     * Seleciona uma operação.
     */
    private void selecionarOperacao(String simbolo) {
        try {
            primeiroOperando = Double.parseDouble(displayField.getText());
            operacaoSelecionada = simbolo;
            novoNumero = true;
            
            // Mostra qual operação foi selecionada
            JOptionPane.showMessageDialog(
                this,
                "Operação: " + controller.getDescricaoOperacao(simbolo),
                "Operação Selecionada",
                JOptionPane.INFORMATION_MESSAGE
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Número inválido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Calcula o resultado da operação usando o Controller.
     */
    private void calcularResultado() {
        try {
            double segundoOperando = Double.parseDouble(displayField.getText());
            
            // Utiliza o Controller com Reflection para executar a operação
            double resultado = controller.executarOperacao(
                operacaoSelecionada,
                primeiroOperando,
                segundoOperando
            );
            
            displayField.setText(String.valueOf(resultado));
            novoNumero = true;
            operacaoSelecionada = null;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Número inválido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Erro Matemático",
                JOptionPane.ERROR_MESSAGE
            );
            limpar();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Erro de Operação",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    /**
     * Limpa o display e reinicializa as variáveis.
     */
    private void limpar() {
        displayField.setText("0");
        primeiroOperando = 0;
        operacaoSelecionada = null;
        novoNumero = true;
    }
}
