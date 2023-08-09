package View;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private JPanel main;
    public Screen() {
        this.main = new JPanel();

        detailsOnMain();
        this.setTitle("mips32 IDE - v1.0");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setContentPane(main);
        this.setVisible(true);
    }

    private void detailsOnMain() {
        main.setLayout(new BorderLayout());
        main.add(new MenuBars(), BorderLayout.NORTH);
        main.setBackground(Color.decode("#5d5b5d"));
        main.add(new CodeArea(), BorderLayout.CENTER);
        main.add(new Structure(), BorderLayout.WEST);
    }
}
