package graphic;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;

        JComponent[] cards = new JComponent[] {
                new DescontoPanel(),
                new IncrementarPanel(),
                new AmostragemPanel(),
                new AmostragemDoisPanel(),
                new ValorAntigoNovo(),
                new DeltaPanel(),
                new ValorOriginal(),
                new RegraDeTres(),
                new GeradoSenhaPanel()
        };

        Dimension cardSize = new Dimension(400, 200);
        for (JComponent c : cards) c.setPreferredSize(cardSize);

        int cols = 3;

        for (int i = 0; i < cards.length; i++) {
            gbc.gridx = i % cols;
            gbc.gridy = i / cols;
            grid.add(cards[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = (int) Math.ceil(cards.length / (double) cols);
        gbc.gridwidth = cols;
        gbc.weightx = 1;
        gbc.weighty = 1;
        JPanel filler = new JPanel(); // vazio
        filler.setOpaque(false);
        grid.add(filler, gbc);

        JScrollPane scroll = new JScrollPane(grid);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scroll);

        setSize(1250, 800);
        setLocationRelativeTo(null);
    }
    }

