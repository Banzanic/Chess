package Chess;

import javax.swing.*;
public class Chess {
    public static void main(String args[]){
        App gui = new App();
        gui.init();
        JFrame frame=new JFrame("Chess");
        frame.add(gui);
        frame.setSize(528, 551);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
