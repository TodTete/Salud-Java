package op;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*Creado por Ricardo Vallejo Sánchez*/

public class PsaludJava extends JFrame {

    // Variables de entrada
    private final JTextField nombreField;
    private final JTextField pesoField;
    private final JTextField alturaField;
    private final JTextField edadField;
    private final JComboBox<String> generoCombo;
    private final JTextField oxigenoField;
    private final JTextField presionSistolicaField;
    private final JTextField presionDiastolicaField;
    private final JTextField frecuenciaCardiacaField;

    // Variables de salida
    private final JLabel resultadoLabel;
    private final JLabel resultadoTmbLabel;
    private final JLabel resultadoOxigenoLabel;
    private final JLabel resultadoPresionLabel;
    private final JLabel resultadoFrecuenciaLabel;

    public PsaludJava() {
        super("Calculadora de Salud");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(211, 224, 233));
        panel.setLayout(new GridLayout(8, 4, 10, 10));
        add(panel);

        JLabel nombreLabel = new JLabel("Nombre:");
        panel.add(nombreLabel);
        nombreField = new JTextField();
        panel.add(nombreField);

        JLabel pesoLabel = new JLabel("Peso (kg):");
        panel.add(pesoLabel);
        pesoField = new JTextField();
        panel.add(pesoField);

        JLabel alturaLabel = new JLabel("Altura (m):");
        panel.add(alturaLabel);
        alturaField = new JTextField();
        panel.add(alturaField);

        JLabel edadLabel = new JLabel("Edad:");
        panel.add(edadLabel);
        edadField = new JTextField();
        panel.add(edadField);

        JLabel generoLabel = new JLabel("Género:");
        panel.add(generoLabel);
        String[] generos = {"Masculino", "Femenino"};
        generoCombo = new JComboBox<>(generos);
        panel.add(generoCombo);

        JLabel oxigenoLabel = new JLabel("Saturación de Oxígeno (%):");
        panel.add(oxigenoLabel);
        oxigenoField = new JTextField();
        panel.add(oxigenoField);

        JLabel presionSistolicaLabel = new JLabel("Presión Arterial Sistólica (mmHg):");
        panel.add(presionSistolicaLabel);
        presionSistolicaField = new JTextField();
        panel.add(presionSistolicaField);

        JLabel presionDiastolicaLabel = new JLabel("Presión Arterial Diastólica (mmHg):");
        panel.add(presionDiastolicaLabel);
        presionDiastolicaField = new JTextField();
        panel.add(presionDiastolicaField);

        JLabel frecuenciaCardiacaLabel = new JLabel("Frecuencia Cardíaca (lpm):");
        panel.add(frecuenciaCardiacaLabel);
        frecuenciaCardiacaField = new JTextField();
        panel.add(frecuenciaCardiacaField);

        JButton calcularButton = new JButton("Calcular Salud");
        panel.add(calcularButton);

        JButton limpiarButton = new JButton("Limpiar Campos");
        panel.add(limpiarButton);

        resultadoLabel = new JLabel();
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(resultadoLabel);

        resultadoTmbLabel = new JLabel();
        resultadoTmbLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(resultadoTmbLabel);

        resultadoOxigenoLabel = new JLabel();
        resultadoOxigenoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(resultadoOxigenoLabel);

        resultadoPresionLabel = new JLabel();
        resultadoPresionLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(resultadoPresionLabel);

        resultadoFrecuenciaLabel = new JLabel();
        resultadoFrecuenciaLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(resultadoFrecuenciaLabel);

        calcularButton.addActionListener((ActionEvent e) -> {
            mostrarResultado();
        });

        limpiarButton.addActionListener((ActionEvent e) -> {
            limpiarCampos();
        });

        setVisible(true);
    }

    private String calcularTMB(double peso, double altura, int edad, String genero) {
        double tmb;

        if (genero.equals("Masculino")) {
            tmb = 88.362 + (13.397 * peso) + (4.799 * altura * 100) - (5.677 * edad);
        } else {
            tmb = 447.593 + (9.247 * peso) + (3.098 * altura * 100) - (4.330 * edad);
        }

        return String.format("Tu TMB es: %.2f kcal/día", tmb);
    }

    private String verificarOxigenacion(double saturacionOxigeno) {
        if (saturacionOxigeno >= 95 && saturacionOxigeno <= 100) {
            return "Saturación de oxígeno en sangre: Normal";
        } else if (saturacionOxigeno >= 90 && saturacionOxigeno < 95) {
            return "Saturación de oxígeno en sangre: Moderadamente baja. Consulta a un médico.";
        } else if (saturacionOxigeno < 90) {
            return "Saturación de oxígeno en sangre: Baja. Busca atención médica de inmediato.";
        } else {
            return "Ingresa un valor válido para la saturación de oxígeno.";
        }
    }

    private String verificarPresionArterial(int presionSistolica, int presionDiastolica) {
        if (presionSistolica < 90 || presionDiastolica < 60) {
            return "Presión arterial: Muy baja. Consulta a un médico.";
        } else if (presionSistolica >= 90 && presionSistolica <= 120 && presionDiastolica >= 60 && presionDiastolica <= 80) {
            return "Presión arterial: Normal";
        } else if (presionSistolica > 120 && presionSistolica <= 130 && presionDiastolica > 80 && presionDiastolica <= 89) {
            return "Presión arterial: Elevada. Controla tu estilo de vida y hábitos alimenticios.";
        } else if (presionSistolica > 130 && presionSistolica <= 140 || presionDiastolica > 89 && presionDiastolica <= 90) {
            return "Presión arterial: Hipertensión (Etapa 1). Consulta a un médico.";
        } else if (presionSistolica > 140 && presionSistolica <= 180 || presionDiastolica > 90 && presionDiastolica <= 120) {
            return "Presión arterial: Hipertensión (Etapa 2). Busca atención médica de inmediato.";
        } else if (presionSistolica > 180 || presionDiastolica > 120) {
            return "Presión arterial: Crisis hipertensiva. Llama a emergencias médicas.";
        } else {
            return "Ingresa valores válidos para la presión arterial.";
        }
    }

    private String calcularIMC(double peso, double altura) {
        if (altura == 0) {
            return "La altura no puede ser cero. Introduce un valor válido.";
        }

        double imc = peso / (altura * altura);
        String estadoPeso;

        if (imc < 18.5) {
            estadoPeso = "Bajo peso";
        } else if (imc < 25) {
            estadoPeso = "Peso normal";
        } else if (imc < 30) {
            estadoPeso = "Sobrepeso";
        } else if (imc < 35) {
            estadoPeso = "Obesidad (Clase 1)";
        } else if (imc < 40) {
            estadoPeso = "Obesidad (Clase 2)";
        } else {
            estadoPeso = "Obesidad (Clase 3)";
        }

        return String.format("Tu IMC es: %.2f. Estado de Peso: %s", imc, estadoPeso);
    }

    private String verificarFrecuenciaCardiaca(int frecuenciaCardiaca) {
        if (frecuenciaCardiaca >= 60 && frecuenciaCardiaca <= 100) {
            return String.format("Frecuencia cardíaca: Normal (%d lpm)", frecuenciaCardiaca);
        } else if (frecuenciaCardiaca < 60) {
            return String.format("Frecuencia cardíaca: Baja. Consulta a un médico (%d lpm)", frecuenciaCardiaca);
        } else {
            return String.format("Frecuencia cardíaca: Alta. Consulta a un médico (%d lpm)", frecuenciaCardiaca);
        }
    }

    private void mostrarResultado() {
        double peso, altura, oxigeno;
        int edad, presionSistolica, presionDiastolica, frecuenciaCardiaca;

        try {
            peso = Double.parseDouble(pesoField.getText().trim());
            altura = Double.parseDouble(alturaField.getText().trim());
            oxigeno = Double.parseDouble(oxigenoField.getText().trim());
            edad = Integer.parseInt(edadField.getText().trim());
            presionSistolica = Integer.parseInt(presionSistolicaField.getText().trim());
            presionDiastolica = Integer.parseInt(presionDiastolicaField.getText().trim());
            frecuenciaCardiaca = Integer.parseInt(frecuenciaCardiacaField.getText().trim());
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Por favor, introduce valores numéricos válidos.");
            resultadoTmbLabel.setText("");
            resultadoOxigenoLabel.setText("");
            resultadoPresionLabel.setText("");
            resultadoFrecuenciaLabel.setText("");
            return;
        }

        if (altura == 0) {
            resultadoLabel.setText("La altura no puede ser cero. Introduce un valor válido.");
            resultadoTmbLabel.setText("");
            resultadoOxigenoLabel.setText("");
            resultadoPresionLabel.setText("");
            resultadoFrecuenciaLabel.setText("");
            return;
        }

        String genero = (String) generoCombo.getSelectedItem();

        // Cálculos
        String resultadoIMC = calcularIMC(peso, altura);
        String resultadoTMB = calcularTMB(peso, altura, edad, genero);
        String resultadoOxigeno = verificarOxigenacion(oxigeno);
        String resultadoPresion = verificarPresionArterial(presionSistolica, presionDiastolica);
        String resultadoFrecuencia = verificarFrecuenciaCardiaca(frecuenciaCardiaca);

        // Mostrar resultados
        resultadoLabel.setText(resultadoIMC);
        resultadoTmbLabel.setText(resultadoTMB);
        resultadoOxigenoLabel.setText(resultadoOxigeno);
        resultadoPresionLabel.setText(resultadoPresion);
        resultadoFrecuenciaLabel.setText(resultadoFrecuencia);
    }

    private void limpiarCampos() {
        nombreField.setText("");
        pesoField.setText("");
        alturaField.setText("");
        edadField.setText("");
        oxigenoField.setText("");
        presionSistolicaField.setText("");
        presionDiastolicaField.setText("");
        frecuenciaCardiacaField.setText("");
        resultadoLabel.setText("");
        resultadoTmbLabel.setText("");
        resultadoOxigenoLabel.setText("");
        resultadoPresionLabel.setText("");
        resultadoFrecuenciaLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PsaludJava psaludJava = new PsaludJava();
        });
    }
}
