package View;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    private JPanel main;
    private final MenuBars menuBar;
    private final BorderLayout borderLayout;
    private final CodeArea codeArea;
    private final Structure comp;
    private boolean isDayMode;

    public Screen() {
        this.main = new JPanel();
        this.menuBar = new MenuBars();
        this.codeArea = new CodeArea();
        this.borderLayout = new BorderLayout();
        this.comp = new Structure();
        isDayMode = true;

        detailsOnMain();
        this.setTitle("mips32 IDE - v1.0");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setContentPane(main);
        this.setVisible(true);
    }

    private void detailsOnMain() {
        main.setLayout(borderLayout);
        main.add(menuBar, BorderLayout.NORTH);
        main.setBackground(Color.decode("#5d5b5d"));
        main.add(codeArea, BorderLayout.CENTER);
        main.add(comp, BorderLayout.WEST);
        menuBar.addListenerDayNightButton((e) -> {
            boolean nextModeDayOrNight = !isDayMode;
            this.isDayMode = nextModeDayOrNight;

            menuBar.setDayOrNightMode(nextModeDayOrNight);
            codeArea.setDayOrNightMode(nextModeDayOrNight);
        });
    }
}
