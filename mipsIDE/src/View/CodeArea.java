package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CodeArea extends JPanel {
    private final JTextPane coding;

    public CodeArea() {
        coding = new JTextPane();
        area();
        initialiseUI();
        addCodingAreaListenerForText();
    }
    public void area() {
        coding.setEditable(true);
        coding.setOpaque(false);
        //setFontSize(17);
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17));
        coding.setForeground(Color.decode("#ffd59a"));
        coding.setContentType("text/plain");
    }

    public void initialiseUI() {
        this.setBackground(Color.decode("#3e3d3e"));
        this.setLayout(new BorderLayout());
        this.add(coding, BorderLayout.CENTER);
        this.setVisible(true);
    }
    private void addCodingAreaListenerForText() {
        coding.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highlightKeywords(coding);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                highlightKeywords(coding);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // Style changes not needed for plain text
            }
        });
    }
    private static void highlightKeywords(JTextPane textPane) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = textPane.getStyledDocument();
            String text;
            try {
                text = doc.getText(0, doc.getLength());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            List<String> instructionKeywords = Arrays.asList(
                    "add", "addi", "beq", "lw", "sw", "j");
            List<String> registerFileAddressKeywords = Arrays.asList(
                    "$0", "$1", "$2", "$3", "$4", "$5",
                    "$6", "$7", "$8", "$9", "$10",
                    "$11", "$12", "$13", "$14", "$15");
            SimpleAttributeSet instructionKeywordStyle = new SimpleAttributeSet();
            StyleConstants.setForeground(instructionKeywordStyle, Color.BLUE);

            SimpleAttributeSet registerFileAddressKeywordStyle = new SimpleAttributeSet();
            StyleConstants.setForeground(registerFileAddressKeywordStyle, Color.green);

            SimpleAttributeSet normalStyle = new SimpleAttributeSet();

            int start = 0;
            int length = 0;

            for (String keyword : instructionKeywords) {
                int index = text.indexOf(keyword, start);
                while (index >= 0) {
                    length = keyword.length();
                    doc.setCharacterAttributes(index, length, instructionKeywordStyle, false);
                    start = index + length;
                    index = text.indexOf(keyword, start);
                }
                start = 0;
            }
            for (String keyword : registerFileAddressKeywords) {
                int index = text.indexOf(keyword, start);
                while (index >= 0) {
                    length = keyword.length();
                    doc.setCharacterAttributes(index, length, registerFileAddressKeywordStyle, false);
                    start = index + length;
                    index = text.indexOf(keyword, start);
                }
                start = 0;
            }
        });
    }

    //TODO: a JPanel with line counting
    public void setFontSize(int size) {
        MutableAttributeSet attrs = coding.getInputAttributes();
        StyleConstants.setFontSize(attrs, size);
        coding.setCharacterAttributes(attrs, true);
    }
    public void setDayOrNightMode(boolean isDay) {
        //TODO: implement day and night mode
        String message = isDay ? "Is day" : "Is night";
        message += " In code area";
        System.out.println(message);
    }
    public void dayMode() {
        changeBgColour("#dae0f2");
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17)); //TODO: in-/decrease size as I like
        coding.setForeground(Color.decode("#700548"));
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
    protected JTextPane getCoding() {
        return coding;
    }
}
