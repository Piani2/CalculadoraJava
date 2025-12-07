package com.calculadora.model;

/**
 * Implementação concreta da operação de Multiplicação.
 */
public class Multiplicacao extends OperacaoAbstrata {
    
    public Multiplicacao() {
        super("*", "Multiplicação");
    }
    
    @Override
    public double executar(double a, double b) {
        return a * b;
    }
}
