package com.calculadora.controller;

import com.calculadora.model.Operacao;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller que gerencia as operações da calculadora.
 * Utiliza Reflection para instanciar dinamicamente as operações.
 */
public class CalculadoraController {
    
    private Map<String, Operacao> operacoes;
    
    public CalculadoraController() {
        this.operacoes = new HashMap<>();
        carregarOperacoes();
    }
    
    /**
     * Carrega dinamicamente todas as operações usando Reflection.
     * Procura por classes no pacote com.calculadora.model que herdam de OperacaoAbstrata.
     */
    private void carregarOperacoes() {
        String[] nomeClasses = {
            "com.calculadora.model.Adicao",
            "com.calculadora.model.Subtracao",
            "com.calculadora.model.Multiplicacao",
            "com.calculadora.model.Divisao"
        };
        
        for (String nomeClasse : nomeClasses) {
            try {
                // Reflection: carrega a classe dinamicamente
                Class<?> classe = Class.forName(nomeClasse);
                
                // Verifica se a classe implementa Operacao
                if (Operacao.class.isAssignableFrom(classe)) {
                    // Reflection: instancia a classe usando o construtor padrão
                    Operacao operacao = (Operacao) classe.getDeclaredConstructor().newInstance();
                    
                    // Armazena a operação usando o símbolo como chave
                    operacoes.put(operacao.getSimbolo(), operacao);
                    
                    System.out.println("✓ Operação carregada: " + operacao.getDescricao() + " (" + operacao.getSimbolo() + ")");
                }
            } catch (ClassNotFoundException e) {
                System.err.println("✗ Classe não encontrada: " + nomeClasse);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                System.err.println("✗ Erro ao instanciar " + nomeClasse + ": " + e.getMessage());
            }
        }
    }
    
    /**
     * Executa uma operação matemática.
     * 
     * @param simbolo símbolo da operação (+, -, *, /)
     * @param a primeiro operando
     * @param b segundo operando
     * @return resultado da operação
     * @throws IllegalArgumentException se a operação não existir
     */
    public double executarOperacao(String simbolo, double a, double b) {
        Operacao operacao = operacoes.get(simbolo);
        
        if (operacao == null) {
            throw new IllegalArgumentException("Operação '" + simbolo + "' não encontrada!");
        }
        
        return operacao.executar(a, b);
    }
    
    /**
     * Retorna todas as operações disponíveis.
     * 
     * @return mapa com símbolo e operação
     */
    public Map<String, Operacao> getOperacoes() {
        return new HashMap<>(operacoes);
    }
    
    /**
     * Retorna a descrição de uma operação.
     * 
     * @param simbolo símbolo da operação
     * @return descrição ou null se não encontrar
     */
    public String getDescricaoOperacao(String simbolo) {
        Operacao operacao = operacoes.get(simbolo);
        return operacao != null ? operacao.getDescricao() : null;
    }
}
