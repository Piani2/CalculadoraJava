package com.calculadora;

import com.calculadora.controller.CalculadoraController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes unitários para o Controller.
 */
public class CalculadoraControllerTest {
    
    private CalculadoraController controller;
    
    @Before
    public void setUp() {
        controller = new CalculadoraController();
    }
    
    @Test
    public void testeControllerAdicion() {
        double resultado = controller.executarOperacao("+", 10, 5);
        assertEquals(15.0, resultado, 0.001);
    }
    
    @Test
    public void testeControllerSubtracao() {
        double resultado = controller.executarOperacao("-", 10, 3);
        assertEquals(7.0, resultado, 0.001);
    }
    
    @Test
    public void testeControllerMultiplicacao() {
        double resultado = controller.executarOperacao("*", 6, 7);
        assertEquals(42.0, resultado, 0.001);
    }
    
    @Test
    public void testeControllerDivisao() {
        double resultado = controller.executarOperacao("/", 30, 6);
        assertEquals(5.0, resultado, 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testeOperacaoInvalida() {
        controller.executarOperacao("%", 10, 5);
    }
    
    @Test
    public void testeOperacoesCarregadas() {
        assertFalse(controller.getOperacoes().isEmpty());
        assertEquals(4, controller.getOperacoes().size());
    }
    
    @Test
    public void testeDescricaoOperacao() {
        assertEquals("Adição", controller.getDescricaoOperacao("+"));
        assertEquals("Subtração", controller.getDescricaoOperacao("-"));
        assertEquals("Multiplicação", controller.getDescricaoOperacao("*"));
        assertEquals("Divisão", controller.getDescricaoOperacao("/"));
    }
    
    @Test
    public void testeDescricaoOperacaoInvalida() {
        assertNull(controller.getDescricaoOperacao("%"));
    }
}
