package com.calculadora;

import com.calculadora.controller.CalculadoraController;
import com.calculadora.view.CalculadoraView;

/**
 * Classe principal da aplicação.
 * Inicializa o Controller e a View seguindo o padrão MVC.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("Calculadora com MVC, Polimorfismo e Reflection");
        System.out.println("====================================\n");
        
        // Executa a interface gráfica na thread de evento da GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // Cria o Controller
                CalculadoraController controller = new CalculadoraController();
                
                System.out.println();
                
                // Cria e exibe a View
                new CalculadoraView(controller);
                
                System.out.println("\n✓ Aplicação iniciada com sucesso!");
            } catch (Exception e) {
                System.err.println("✗ Erro ao iniciar a aplicação: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
