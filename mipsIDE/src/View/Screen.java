package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    private JPanel main;
    private final MenuBars menuBars;
    private final CodeArea codeArea;
    private final Structure structure;

    public Screen() {
        this.main       = new JPanel();
        this.menuBars   = new MenuBars();
        this.codeArea   = new CodeArea();
        this.structure  = new Structure();
        detailsOnMain();
        this.setTitle("mips32 IDE - v1.0");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 400));
        this.setLocationRelativeTo(null);
        this.setContentPane(main);
        this.setVisible(true);
        listeners();
    }
    private void detailsOnMain() {
        main.setLayout(new BorderLayout());
        main.add(new MenuBars(), BorderLayout.NORTH);
        main.setBackground(Color.decode("#5d5b5d"));
        main.add(new CodeArea(), BorderLayout.CENTER);
        main.add(new Structure(), BorderLayout.WEST);
    }
    public MenuBars getMenuBars() {
        return menuBars;
    }

    public CodeArea getCodeArea() {
        return codeArea;
    }

    public Structure getStructure() {
        return structure;
    }
    public void listeners(){
        menuBars.getSwDayNight().setText("X");
        System.out.println(menuBars.getSwDayNight().getText());
    }
}
