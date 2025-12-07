package com.calculadora.model;

/**
 * Classe abstrata que implementa funcionalidades comuns de operações.
 * Utiliza o conceito de Polimorfismo através de classe abstrata.
 */
public abstract class OperacaoAbstrata implements Operacao {
    
    protected String simbolo;
    protected String descricao;
    
    /**
     * Construtor da classe abstrata.
     * 
     * @param simbolo símbolo da operação
     * @param descricao descrição da operação
     */
    public OperacaoAbstrata(String simbolo, String descricao) {
        this.simbolo = simbolo;
        this.descricao = descricao;
    }
    
    @Override
    public String getSimbolo() {
        return simbolo;
    }
    
    @Override
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Template Method - define a estrutura de execução.
     * Subclasses devem implementar o cálculo específico.
     */
    @Override
    public abstract double executar(double a, double b);
}
