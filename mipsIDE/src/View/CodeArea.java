package View;

import javax.swing.*;
import java.awt.*;

public class CodeArea extends JPanel {
    private final JTextPane coding;
    public CodeArea() {
        coding = new JTextPane();

        area();
        initialiseUI();
    }
    public void area() {
        coding.setEditable(true);
        coding.setOpaque(false);
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17)); //TODO: in-/decrease size as I like
        coding.setForeground(Color.decode("#ffd59a"));
        coding.setContentType("text/plain");
    }

    public void initialiseUI() {

        this.setBackground(Color.decode("#3e3d3e"));
        this.setLayout(new BorderLayout());
        this.add(coding, BorderLayout.CENTER);
        this.setVisible(true);
    }
    //TODO: a JPanel with line counting
    public void setDayOrNightMode(boolean isDay) {
        //TODO: implement day and night mode
        String message = isDay ? "Is day" : "Is night";
        message += " In code area";
        System.out.println(message);
    }
    public void dayMode() {
        changeBgColour("#dae0f2");
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17)); //TODO: in-/decrease size as I like
        coding.setForeground(Color.decode("#ffd59a"));
    }
    public void nightMode() {
        this.setBackground(Color.decode("#3e3d3e"));
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17)); //TODO: in-/decrease size as I like
        coding.setForeground(Color.decode("#ffd59a"));
    }
    private void changeBgColour(String colour) {
        this.setBackground(Color.decode(colour));
    }
    protected CodeArea getCodeArea () {
        return this;
    }
}
