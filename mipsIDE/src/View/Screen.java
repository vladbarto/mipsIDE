package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    private JPanel main;
    private final MenuBars menuBar;
    private final BorderLayout borderLayout;
    private final CodeArea codeArea;
    private final Structure structure;
    private boolean isDayMode;

    public Screen(String t) {
        this.main = new JPanel();
        this.menuBar = new MenuBars();
        this.codeArea = new CodeArea(t);
        this.borderLayout = new BorderLayout();
        this.structure = new Structure();
        isDayMode = false;

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
        main.add(structure, BorderLayout.WEST);
        structure.setBackground(Color.magenta);
        menuBar.addListenerDayNightButton((e) -> {
            if(!isDayMode) {
                menuBar.dayMode();
                codeArea.dayMode();
            } else {
                menuBar.nightMode();
                codeArea.nightMode();
            }
            this.isDayMode = !this.isDayMode;
            menuBar.setDayOrNightMode(this.isDayMode);
            codeArea.setDayOrNightMode(this.isDayMode);
        });
        addListenerForSave();
    }

    private void addListenerForSave() {
        menuBar.getSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CodeArea.saveDocumentProgress(codeArea.getCoding(), "document.mips");
            }
        });
    }
}
