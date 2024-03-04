package Controller;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlAll {
    private Screen screen;
    public CtrlAll(Screen screen) {
        this.screen = screen;
        //this.screen.getMenuBars().listeners(new SwitchDayNight());
    }
//    public class SwitchDayNight implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Pressed");
//        }
//    }
}
