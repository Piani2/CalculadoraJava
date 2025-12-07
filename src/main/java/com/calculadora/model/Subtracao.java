package com.calculadora.model;

/**
 * Implementação concreta da operação de Subtração.
 */
public class Subtracao extends OperacaoAbstrata {
    
    public Subtracao() {
        super("-", "Subtração");
    }
    
    @Override
    public double executar(double a, double b) {
        return a - b;
    }
}
