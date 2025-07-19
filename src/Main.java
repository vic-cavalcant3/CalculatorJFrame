import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {



// Criando a janela principal
        JFrame frame = new JFrame("Calculadora Elegante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        // Campo de texto para exibir a entrada e resultado
        JTextField display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 4, 5, 5));

        // Array com os textos dos botões
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Variáveis para armazenar o estado
        final double[] numeros = {0};
        final String[] operador = {""};

        // Adicionando botões ao painel
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            painelBotoes.add(botao);

            botao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String comando = e.getActionCommand();

                    switch (comando) {
                        case "C": // Limpar o display
                            display.setText("");
                            numeros[0] = 0;
                            operador[0] = "";
                            break;
                        case "=": // Calcular resultado
                            try {
                                double numeroAtual = Double.parseDouble(display.getText());
                                switch (operador[0]) {
                                    case "+":
                                        numeros[0] += numeroAtual;
                                        break;
                                    case "-":
                                        numeros[0] -= numeroAtual;
                                        break;
                                    case "*":
                                        numeros[0] *= numeroAtual;
                                        break;
                                    case "/":
                                        if (numeroAtual != 0) {
                                            numeros[0] /= numeroAtual;
                                        } else {
                                            display.setText("Erro: Divisão por zero");
                                            return;
                                        }
                                        break;
                                }
                                display.setText(String.valueOf(numeros[0]));
                                operador[0] = "";
                            } catch (NumberFormatException ex) {
                                display.setText("Entrada inválida");
                            }
                            break;
                        case "+": case "-": case "*": case "/": // Operadores
                            try {
                                numeros[0] = Double.parseDouble(display.getText());
                                operador[0] = comando;
                                display.setText("");
                            } catch (NumberFormatException ex) {
                                display.setText("Entrada inválida");
                            }
                            break;
                        default: // Números
                            display.setText(display.getText() + comando);
                    }
                }
            });
        }

        // Adicionando componentes ao frame
        frame.add(display, BorderLayout.NORTH);
        frame.add(painelBotoes, BorderLayout.CENTER);

        // Exibindo a janela
        frame.setVisible(true);    }
}