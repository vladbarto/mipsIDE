package App;

import Controller.*;
import View.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen();
        CtrlAll ctrlAll = new CtrlAll(screen);
    }
}