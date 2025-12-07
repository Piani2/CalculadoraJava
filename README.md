# Calculadora MVC com Polimorfismo e Reflection

Uma calculadora completa desenvolvida em Java com arquitetura MVC (Model-View-Controller), demonstrando boas práticas de engenharia de software, incluindo polimorfismo e uso avançado de Reflection.

## 📋 Características

### Arquitetura MVC
- **Model**: Classes de operações matemáticas (Adicao, Subtracao, Multiplicacao, Divisao)
- **View**: Interface gráfica desenvolvida em Swing
- **Controller**: Gerencia as operações e comunica com a View

### Conceitos de Programação Aplicados

#### 1. **Polimorfismo**
- Interface `Operacao` que define o contrato para operações
- Classe abstrata `OperacaoAbstrata` implementando a interface
- Múltiplas implementações concretas que herdam da classe abstrata
- Permite adicionar novas operações sem modificar o código existente (Open/Closed Principle)

```java
public interface Operacao {
    double executar(double a, double b);
    String getSimbolo();
    String getDescricao();
}

public abstract class OperacaoAbstrata implements Operacao {
    // Implementação comum
}

public class Adicao extends OperacaoAbstrata {
    // Implementação específica
}
```

#### 2. **Reflection**
- Carregamento dinâmico de classes em tempo de execução
- Instanciação automática usando `Class.forName()` e `getDeclaredConstructor()`
- Descoberta de classes que implementam `Operacao`
- Permite criar novas operações sem modificar o Controller

```java
Class<?> classe = Class.forName(nomeClasse);
Operacao operacao = (Operacao) classe.getDeclaredConstructor().newInstance();
operacoes.put(operacao.getSimbolo(), operacao);
```

#### 3. **Padrões de Projeto**
- **MVC**: Separação clara entre Model, View e Controller
- **Template Method**: Estrutura comum em `OperacaoAbstrata`
- **Factory**: Carregamento dinâmico de operações

## 🚀 Como Executar

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6 ou superior

### Compilação

```bash
mvn clean compile
```

### Testes

```bash
mvn test
```

### Execução

```bash
mvn clean compile exec:java -Dexec.mainClass="com.calculadora.Main"
```

Ou gerar um JAR executável:

```bash
mvn clean package
java -jar target/calculadora-mvc-1.0.0.jar
```

## 📁 Estrutura do Projeto

```
src/
├── main/
│   └── java/com/calculadora/
│       ├── model/
│       │   ├── Operacao.java           # Interface
│       │   ├── OperacaoAbstrata.java   # Classe abstrata
│       │   ├── Adicao.java
│       │   ├── Subtracao.java
│       │   ├── Multiplicacao.java
│       │   └── Divisao.java
│       ├── view/
│       │   └── CalculadoraView.java    # Interface Swing
│       ├── controller/
│       │   └── CalculadoraController.java # Lógica com Reflection
│       └── Main.java                   # Entrada da aplicação
└── test/
    └── java/com/calculadora/
        ├── OperacoesTest.java          # Testes das operações
        └── CalculadoraControllerTest.java # Testes do controller

pom.xml                                  # Configuração Maven
```

## 🔧 Configuração Maven

### Dependências Principais
- **JUnit 4.13.2**: Para testes unitários
- **Maven Shade Plugin**: Para criar JAR executável

### Compilação
- Fonte: Java 11
- Alvo: Java 11

## 💡 Como Adicionar Novas Operações

O sistema foi projetado para ser extensível. Para adicionar uma nova operação:

### 1. Criar uma classe que estenda `OperacaoAbstrata`

```java
package com.calculadora.model;

public class Potencia extends OperacaoAbstrata {
    
    public Potencia() {
        super("^", "Potência");
    }
    
    @Override
    public double executar(double a, double b) {
        return Math.pow(a, b);
    }
}
```

### 2. Adicionar na lista de classes no Controller

```java
private void carregarOperacoes() {
    String[] nomeClasses = {
        "com.calculadora.model.Adicao",
        "com.calculadora.model.Subtracao",
        "com.calculadora.model.Multiplicacao",
        "com.calculadora.model.Divisao",
        "com.calculadora.model.Potencia"  // Nova operação
    };
    // ...
}
```

A Reflection automaticamente:
- Carrega a classe
- Verifica se implementa `Operacao`
- Instancia a classe
- Registra no mapa de operações

## 🧪 Testes

O projeto inclui testes unitários abrangentes:

### OperacoesTest.java
- Testes de cada operação matemática
- Testes de casos especiais (divisão por zero, números negativos)
- Testes de símbolos e descrições

### CalculadoraControllerTest.java
- Testes do Controller com Reflection
- Testes de operações inválidas
- Verificação do carregamento dinâmico

Executar testes:
```bash
mvn test
```

## 📚 Conceitos Demonstrados

### Boas Práticas

1. **Encapsulamento**: Atributos privados com métodos de acesso
2. **Herança**: `OperacaoAbstrata` estende `Operacao`
3. **Polimorfismo**: Diferentes implementações da interface `Operacao`
4. **Interface Segregation**: Interface `Operacao` com responsabilidades bem definidas
5. **Open/Closed Principle**: Aberto para extensão, fechado para modificação

### Reflection Avançada

- `Class.forName()`: Carregamento dinâmico de classes
- `isAssignableFrom()`: Verificação de tipos
- `getDeclaredConstructor()`: Acesso a construtores
- `newInstance()`: Instanciação dinâmica
- `InvocationTargetException`: Tratamento de exceções

### Interface Gráfica

- Componentes Swing: `JFrame`, `JButton`, `JTextField`, `JPanel`
- Layout Managers: `BorderLayout`, `GridLayout`, `FlowLayout`
- Event Handlers: `ActionListener`
- Customização visual: Cores, fontes, borders

## 🎓 Aprendizado

Este projeto demonstra:

1. Como estruturar uma aplicação Java seguindo o padrão MVC
2. Como usar Reflection para criar sistemas extensíveis
3. Como aplicar polimorfismo efetivamente
4. Como criar interfaces gráficas com Swing
5. Como estruturar testes unitários com JUnit
6. Como configurar e usar Maven em projetos Java

## 📝 Licença

Este projeto é um exemplo educacional e pode ser usado livremente.

## ✅ Checklist de Implementação

- [x] Arquitetura MVC
- [x] Interface com Swing
- [x] Polimorfismo (Interface + Classe Abstrata)
- [x] Reflection (Carregamento dinâmico)
- [x] Configuração Maven
- [x] Testes Unitários
- [x] Documentação
- [x] Tratamento de Exceções
- [x] Extensibilidade para novas operações

## 🤝 Próximas Melhorias Possíveis

- [ ] Mais operações matemáticas (raiz, exponencial, logaritmo)
- [ ] Histórico de cálculos
- [ ] Notação RPN (Reverse Polish Notation)
- [ ] Modo científico com funções trigonométricas
- [ ] Persistência de histórico
- [ ] Interface escura/clara
- [ ] Suporte a múltiplas operações em cadeia
