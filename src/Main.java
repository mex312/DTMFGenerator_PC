import javax.swing.*;

public class Main {
    public final static float SAMPLE_RATE = 2048 * 16;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        GUI gui = new GUI();

        frame.setContentPane(gui.root);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}