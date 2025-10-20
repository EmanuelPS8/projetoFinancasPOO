package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegraDeTres extends JPanel {
    private JTextField txtValorInicial, txtDesconto, txtR1, txtResultado;
    private JButton btnCalcular;

    public RegraDeTres(){
        setBorder(BorderFactory.createTitledBorder("Regra de Três"));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;


        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("a: "), c);
        c.gridx = 1;
        txtValorInicial = new JTextField(10);
        txtR1 = new JTextField(10);
        add(txtValorInicial, c);

        c.gridx = 2;
        c.gridy = 0;
        add(new Label("r1: "), c);
        c.gridx = 3;
        txtR1 = new JTextField(10);
        add(txtR1, c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("b: "), c);
        c.gridx = 1;
        txtDesconto = new JTextField(10);
        add(txtDesconto, c);

        c.gridx = 2;
        c.gridy = 1;
        add(new JLabel("r2: "), c);
        c.gridx = 3;
        txtResultado = new JTextField(10);
        txtResultado.setEditable(false);
        add(txtResultado, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        btnCalcular = new JButton("Calcular");
        add(btnCalcular, c);



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
            double r1 = Double.parseDouble(txtR1.getText());
            double resultado = (r1 * b) / a;
            txtResultado.setText(String.format("%f", resultado));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
}

