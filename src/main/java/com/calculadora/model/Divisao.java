package com.calculadora.model;

/**
 * Implementação concreta da operação de Divisão.
 */
public class Divisao extends OperacaoAbstrata {
    
    public Divisao() {
        super("/", "Divisão");
    }
    
    @Override
    public double executar(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida!");
        }
        return a / b;
    }
}
