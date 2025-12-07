package com.calculadora.model;

/**
 * Interface que define o contrato para todas as operações matemáticas.
 * Utiliza o conceito de Polimorfismo através da interface.
 */
public interface Operacao {
    /**
     * Executa a operação matemática.
     * 
     * @param a primeiro operando
     * @param b segundo operando
     * @return resultado da operação
     */
    double executar(double a, double b);
    
    /**
     * Retorna o símbolo da operação.
     * 
     * @return símbolo (+, -, *, /)
     */
    String getSimbolo();
    
    /**
     * Retorna a descrição da operação.
     * 
     * @return descrição textual
     */
    String getDescricao();
}
