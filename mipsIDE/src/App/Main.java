package App;

import Controller.*;
import View.*;

import java.awt.*;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        String fileName = "document.mips"; // Replace with the actual file name

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String documentContent = content.toString();
            System.out.println("Document Content:");
            System.out.println(documentContent);

            Screen screen = new Screen(documentContent);
            CtrlAll ctrlAll = new CtrlAll(screen);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}