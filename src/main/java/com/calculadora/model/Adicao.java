package com.calculadora.model;

/**
 * Implementação concreta da operação de Adição.
 */
public class Adicao extends OperacaoAbstrata {
    
    public Adicao() {
        super("+", "Adição");
    }
    
    @Override
    public double executar(double a, double b) {
        return a + b;
    }
}
