package com.calculadora;

import com.calculadora.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para as operações matemáticas.
 */
public class OperacoesTest {
    
    private Adicao adicao;
    private Subtracao subtracao;
    private Multiplicacao multiplicacao;
    private Divisao divisao;
    
    @Before
    public void setUp() {
        adicao = new Adicao();
        subtracao = new Subtracao();
        multiplicacao = new Multiplicacao();
        divisao = new Divisao();
    }
    
    @Test
    public void testeAdicao() {
        double resultado = adicao.executar(5, 3);
        assertEquals(8.0, resultado, 0.001);
    }
    
    @Test
    public void testeSubtracao() {
        double resultado = subtracao.executar(10, 3);
        assertEquals(7.0, resultado, 0.001);
    }
    
    @Test
    public void testeSubtracaoNegativa() {
        double resultado = subtracao.executar(3, 10);
        assertEquals(-7.0, resultado, 0.001);
    }
    
    @Test
    public void testeMultiplicacao() {
        double resultado = multiplicacao.executar(4, 5);
        assertEquals(20.0, resultado, 0.001);
    }
    
    @Test
    public void testeMultiplicacaoPorZero() {
        double resultado = multiplicacao.executar(5, 0);
        assertEquals(0.0, resultado, 0.001);
    }
    
    @Test
    public void testeDivisao() {
        double resultado = divisao.executar(20, 4);
        assertEquals(5.0, resultado, 0.001);
    }
    
    @Test
    public void testeDivisaoDecimal() {
        double resultado = divisao.executar(7, 2);
        assertEquals(3.5, resultado, 0.001);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testeDivisaoPorZero() {
        divisao.executar(10, 0);
    }
    
    @Test
    public void testeSimbolo() {
        assertEquals("+", adicao.getSimbolo());
        assertEquals("-", subtracao.getSimbolo());
        assertEquals("*", multiplicacao.getSimbolo());
        assertEquals("/", divisao.getSimbolo());
    }
    
    @Test
    public void testeDescricao() {
        assertEquals("Adição", adicao.getDescricao());
        assertEquals("Subtração", subtracao.getDescricao());
        assertEquals("Multiplicação", multiplicacao.getDescricao());
        assertEquals("Divisão", divisao.getDescricao());
    }
}
