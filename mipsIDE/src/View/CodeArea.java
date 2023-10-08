package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CodeArea extends JPanel {
    private final JTextPane coding;
    private final JTextPane lineNumbers;
    private final JScrollPane lineNumbersScrollPane;
    private final JScrollPane codingScrollPane;
    public CodeArea(String t) {
        setLayout(new BorderLayout());

        lineNumbers = createLineNumbersPane();
        lineNumbersScrollPane = new JScrollPane(lineNumbers);
        lineNumbersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        coding = new JTextPane();
        area(t);
        initialiseUI();

        codingScrollPane = new JScrollPane(coding);
        codingScrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            lineNumbersScrollPane.getVerticalScrollBar().setValue(e.getValue());
        });

        add(lineNumbersScrollPane, BorderLayout.LINE_START);
        add(codingScrollPane, BorderLayout.CENTER);
        // We need both lines to make JScrollPane transparent
        codingScrollPane.setOpaque(false);
        codingScrollPane.getViewport().setOpaque(false);

        addCodingAreaListenerForText();
    }

    public static void saveDocumentProgress(JTextPane coding, String docName) {
        StyledDocument doc = (StyledDocument) coding.getDocument();
        try (FileWriter writer = new FileWriter(docName);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(doc.getText(0, doc.getLength()));
            System.out.println("save progress triggered");
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void area(String t) {
        coding.setEditable(true);
        coding.setOpaque(false);
        //setFontSize(17);
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17));
        coding.setForeground(Color.decode("#ffd59a"));
        coding.setContentType("text/plain");
        coding.setText(t);
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
                updateLineNumbers();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                highlightKeywords(coding);
                updateLineNumbers();
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
    private JTextPane createLineNumbersPane() {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.decode("#4A484A"));
        textPane.setForeground(Color.decode("#ffd59a"));
        textPane.setPreferredSize(new Dimension(30, Integer.MAX_VALUE)); // Adjust width as needed
        textPane.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17));

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, 1, center, false);

        return textPane;
    }
    private void updateLineNumbers() {
        String text = coding.getText();
        String[] lines = text.split("\n");
        StringBuilder lineNumbersText = new StringBuilder();
        for (int i = 1; i <= lines.length; i++) {
            lineNumbersText.append(i).append("\n");
        }
        lineNumbers.setText(lineNumbersText.toString());
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
        lineNumbers.setBackground(Color.decode("#BBC2D3"));
        lineNumbers.setForeground(Color.decode("#700548"));
    }
    public void nightMode() {
        this.setBackground(Color.decode("#3e3d3e"));
        coding.setFont(new Font("☞Aktiv Grotesk Medium", Font.PLAIN, 17)); //TODO: in-/decrease size as I like
        coding.setForeground(Color.decode("#ffd59a"));
        lineNumbers.setBackground(Color.decode("#4A484A"));
        lineNumbers.setForeground(Color.decode("#ffd59a"));
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
