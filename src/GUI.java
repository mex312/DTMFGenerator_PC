import javax.swing.*;
import java.awt.event.ActionEvent;

public class GUI {
    private JButton buttonStar;
    private JButton button7;
    private JButton button4;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton buttonA;
    private JButton button5;
    private JButton button6;
    private JButton buttonB;
    private JButton button8;
    private JButton button9;
    private JButton buttonC;
    private JButton button0;
    private JButton buttonHash;
    private JButton buttonD;
    public JPanel root;
    private JTextField inputField;
    private JButton buttonBack;
    private JButton buttonPlay;
    private JButton buttonClear;
    private JButton buttonStop;

    private void numpadListener(ActionEvent action)
    {
        inputField.setText(inputField.getText() + action.getActionCommand());
    }

    public GUI()
    {
        button0.addActionListener(this::numpadListener);
        button1.addActionListener(this::numpadListener);
        button2.addActionListener(this::numpadListener);
        button3.addActionListener(this::numpadListener);
        button4.addActionListener(this::numpadListener);
        button5.addActionListener(this::numpadListener);
        button6.addActionListener(this::numpadListener);
        button7.addActionListener(this::numpadListener);
        button8.addActionListener(this::numpadListener);
        button9.addActionListener(this::numpadListener);
        buttonA.addActionListener(this::numpadListener);
        buttonB.addActionListener(this::numpadListener);
        buttonC.addActionListener(this::numpadListener);
        buttonD.addActionListener(this::numpadListener);
        buttonStar.addActionListener(this::numpadListener);
        buttonHash.addActionListener(this::numpadListener);

        button0.setFocusable(false);
        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);
        buttonA.setFocusable(false);
        buttonB.setFocusable(false);
        buttonC.setFocusable(false);
        buttonD.setFocusable(false);
        buttonStar.setFocusable(false);
        buttonHash.setFocusable(false);
        buttonBack.setFocusable(false);
        buttonStop.setFocusable(false);
        buttonPlay.setFocusable(false);
        buttonClear.setFocusable(false);

        buttonBack.addActionListener(action -> inputField.setText(inputField.getText().subSequence(0, Math.max(0, inputField.getText().length() - 1)).toString()));
        buttonClear.addActionListener(action -> inputField.setText(""));

        try {
            DTMFGenerator generator = new DTMFGenerator(Main.SAMPLE_RATE);

            buttonPlay.addActionListener(action -> generator.play(inputField.getText()));

            buttonStop.addActionListener(action -> generator.stop());
        } catch (Exception ignored) {}
    }
}
