package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.security.SecureRandom;

public class GeradoSenhaPanel extends JPanel {

    private final JCheckBox cbMaiusculas;
    private final JCheckBox cbMinusculas;
    private final JCheckBox cbNumeros;
    private final JCheckBox cbSimbolos;
    private final JSpinner spinnerTamanho;
    private final JButton btnGerar;
    private final JTextField txtResultado;

    private static final String MAIUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUM = "0123456789";
    private static final String SIMB = "!@#$%^&*()-_=+[]{};:,.<>?/|";

    private final SecureRandom random = new SecureRandom();

    public GeradoSenhaPanel() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Gerador de Senha"),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)
        ));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        cbMaiusculas = new JCheckBox("Maiúsculas", false);
        cbMinusculas = new JCheckBox("Minúsculas", false);
        cbNumeros = new JCheckBox("Números", false);
        cbSimbolos = new JCheckBox("Símbolos", false);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(cbMaiusculas, c);

        c.gridx = 1;
        add(cbMinusculas, c);

        c.gridx = 2;
        add(cbNumeros, c);

        c.gridx = 3;
        add(cbSimbolos, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(new JLabel("Tamanho"), c);

        spinnerTamanho = new JSpinner(new SpinnerNumberModel(8, 4, 64, 1));
        c.gridx = 1;
        add(spinnerTamanho, c);

        btnGerar = new JButton("Gerar");
        c.gridx = 2;
        c.gridwidth = 2;
        add(btnGerar, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(new JLabel("Senha:"), c);

        txtResultado = new JTextField(20);
        txtResultado.setEditable(false);
        c.gridx = 1;
        c.gridwidth = 2;
        add(txtResultado, c);


        btnGerar.addActionListener((ActionEvent e) -> gerarSenha());

    }

    private void gerarSenha() {
        int tamanho = (Integer) spinnerTamanho.getValue();

        StringBuilder pool = new StringBuilder(64);
        if (cbMaiusculas.isSelected()) pool.append(MAIUS);
        if (cbMinusculas.isSelected()) pool.append(MINUS);
        if (cbNumeros.isSelected()) pool.append(NUM);
        if (cbSimbolos.isSelected()) pool.append(SIMB);

        if (pool.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione pelo menos um tipo de caractere (maiúsculas, minúsculas, números ou símbolos).",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder senha = new StringBuilder(tamanho);

        if (cbMaiusculas.isSelected()) senha.append(randomCharFrom(MAIUS));
        if (cbMinusculas.isSelected()) senha.append(randomCharFrom(MINUS));
        if (cbNumeros.isSelected()) senha.append(randomCharFrom(NUM));
        if (cbSimbolos.isSelected()) senha.append(randomCharFrom(SIMB));

        for (int i = senha.length(); i < tamanho; i++) {
            senha.append(randomCharFrom(pool.toString()));
        }

        String resultado = shuffleString(senha.toString());
        txtResultado.setText(resultado);
    }

    private char randomCharFrom(String s) {
        int idx = random.nextInt(s.length());
        return s.charAt(idx);
    }

    private String shuffleString(String input) {
        char[] arr = input.toCharArray();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return new String(arr);
    }


}
