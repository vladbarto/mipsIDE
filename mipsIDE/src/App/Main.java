package App;

import Controller.*;
import View.*;

public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen();
        CtrlAll ctrlAll = new CtrlAll(screen);
    }
}