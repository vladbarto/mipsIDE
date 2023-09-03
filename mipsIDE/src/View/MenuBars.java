package View;

import Controller.CtrlAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBars extends JPanel {
    private final JPanel leftSide, rightSide;
    private JButton swDayNight;
    private final JMenuBar menuBar;
    private final JMenu file, tools;
    private final JMenu newSubm;
    private final JMenuItem run, debug;
    private final JMenuItem newFile, newProj;

    public MenuBars() {
        leftSide    = new JPanel();
        rightSide   = new JPanel();
        swDayNight  = new JButton("Day");
        menuBar = new JMenuBar();
        file    = new JMenu("File");
        tools   = new JMenu("Tools");
        run     = new JMenuItem("Run");
        debug   = new JMenuItem("Debug");
        newSubm = new JMenu("New...");
        newFile = new JMenuItem(" file");
        newProj = new JMenuItem(" project");

        menuBarContainer();
        leftSide.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftSide.setOpaque(false);
        rightSide.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightSide.setOpaque(false);

        this.setLayout(new BorderLayout());
        this.add(leftSide, BorderLayout.WEST);
        this.add(rightSide, BorderLayout.EAST);
        this.setBackground(Color.decode("#4e5166"));
        leftSide.add(menuBar);
        rightSide.add(swDayNight);
        this.setVisible(true);
    }
    public JButton getSwDayNight() {
        return swDayNight;
    }

    private void menuBarContainer() {
        menuBar.setPreferredSize(new Dimension(200, 35));
        menuBar.add(file);
        menuBar.add(tools);

        tools.add(run);
        tools.add(debug);

        file.add(newSubm);
        newSubm.add(newFile);
        newSubm.add(newProj);
    }

    public void dayMode() {
        swDayNight.setBackground(Color.decode("#4e5166"));
        swDayNight.setText("Day");

    }
    public void nightMode() {
        swDayNight.setBackground(Color.decode("#ede6d6"));
        swDayNight.setText("Night");
    }

}
