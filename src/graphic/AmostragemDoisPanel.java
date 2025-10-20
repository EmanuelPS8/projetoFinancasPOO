package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmostragemDoisPanel extends JPanel {
    private JTextField txtValorInicial, txtDesconto, txtResultado;
    private JButton btnCalcular;

    public AmostragemDoisPanel() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Amostragem 2 - Quando X representa de Y"),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)
        ));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 1; c.weightx = 0; c.fill = GridBagConstraints.NONE;
        add(new JLabel("Total (a) "), c);

        c.gridx = 1;
        c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        txtValorInicial = new JTextField(10);
        add(txtValorInicial, c);

        c.gridx = 0; c.gridy = 1;
        c.weightx = 0; c.fill = GridBagConstraints.NONE;
        add(new JLabel("Parte (b) "), c);

        c.gridx = 1;
        c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        txtDesconto = new JTextField(10);
        add(txtDesconto, c);

        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        btnCalcular = new JButton("Calcular");
        add(btnCalcular, c);

        c.gridwidth = 1;

        c.gridx = 0; c.gridy = 3;
        c.weightx = 0; c.fill = GridBagConstraints.NONE;
        add(new JLabel("Correnponde a % "), c);

        c.gridx = 1;
        c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        txtResultado = new JTextField(10);
        txtResultado.setEditable(false);
        add(txtResultado, c);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular(){
        try {
            double a =  Double.parseDouble(txtValorInicial.getText());
            double b = Double.parseDouble(txtDesconto.getText());
            double resultado = (b/a) * 100;
            txtResultado.setText(String.format("%f", resultado));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
