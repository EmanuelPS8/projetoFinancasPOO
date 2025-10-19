package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValorOriginal extends JPanel {
    private JTextField txtValorInicial, txtDesconto, txtResultado;
    private JButton btnCalcular;

    public ValorOriginal(){
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Qual era o valor original?"),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)
        ));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;


        //CAMPO 1
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Valor Final R$ (a) "), c);
        c.gridx = 1;
        txtValorInicial = new JTextField(10);
        add(txtValorInicial, c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("%Desconto (b) "), c);
        c.gridx = 1;
        txtDesconto = new JTextField(10);
        add(txtDesconto, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2; //
        c.weightx = 1; //
        c.fill = GridBagConstraints.HORIZONTAL; //
        btnCalcular = new JButton("Calcular");
        add(btnCalcular, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Valor inicial: "), c);

        c.gridx = 1;
        txtResultado = new JTextField(10);
        txtResultado.setEditable(false);
        add(txtResultado, c);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            double a = Double.parseDouble(txtValorInicial.getText());
            double b = Double.parseDouble(txtDesconto.getText());
            double resultado = (a*100 / (100 - b));
            txtResultado.setText(String.format("%f", resultado));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}

