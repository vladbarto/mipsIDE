package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBars extends JPanel {
    private final JPanel leftSide, rightSide;
    private final JToggleButton switchDayNight;
    private final JMenuBar menuBar;
    private final JMenu file, tools;
    private final JMenu submenu;
    private final JMenuItem run, debug;
    private final JMenuItem newFile, newProj;

    public MenuBars() {
        leftSide = new JPanel();
        rightSide = new JPanel();
        switchDayNight = new JToggleButton("Day");
        menuBar = new JMenuBar();
        file = new JMenu("File");
        tools = new JMenu("Tools");
        run = new JMenuItem("Run");
        debug = new JMenuItem("Debug");
        submenu = new JMenu("New...");
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
        rightSide.add(switchDayNight);
        this.setVisible(true);
    }

    public void addListenerDayNightButton(ActionListener actionListener) {
        switchDayNight.addActionListener(actionListener);
    }

    public void setDayOrNightMode(boolean isDay) {
        //TODO: implement day and night mode
        String message = isDay ? "Is day" : "Is night";
        message += " in menu bar";
        System.out.println(message);
    }

    private void menuBarContainer() {
        menuBar.setPreferredSize(new Dimension(200, 35));
        menuBar.add(file);
        menuBar.add(tools);

        tools.add(run);
        tools.add(debug);

        file.add(submenu);
        submenu.add(newFile);
        submenu.add(newProj);
    }

    public void dayMode() {
        switchDayNight.setBackground(Color.decode("#4e5166"));
        switchDayNight.setText("Day");

    }
    public void nightMode() {
        switchDayNight.setBackground(Color.decode("#ede6d6"));
        switchDayNight.setText("Night");
    }

}
