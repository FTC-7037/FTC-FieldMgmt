package MgrMain;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenericDisplay extends JPanel {

    private class sTask extends TimerTask {
        @Override
        public void run() {
            GenericDisplay.this.UnBlink();
        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel                    lbl              = new JLabel("Uninitialized");

    JLabel                    val              = new JLabel("Uninitialized");

    private final Color       bg               = this.getBackground();

    public GenericDisplay(final String name) {
        lbl.setText(name);
        val.setOpaque(true);
        this.setLayout(new GridLayout(0, 2, 0, 0));
        this.add(lbl);
        this.add(val);
    }

    public void Blink(final Color clr, final int delay) {
        this.setBackground(clr);
        final Timer timer = new Timer();
        timer.schedule(new sTask(), delay);
    }

    private void UnBlink() {
        this.setBackground(bg);
    }

    public void UpdateDisplay(final int value) {
        this.UpdateDisplay(value, this.getBackground());
    }

    public void UpdateDisplay(final int value, final Color clr) {
        val.setText(String.valueOf(value));
        val.setBackground(clr);
    }

    public void UpdateDisplay(final String value) {
        this.UpdateDisplay(value, this.getBackground());
    }

    public void UpdateDisplay(final String value, final Color clr) {
        val.setText(value);
        val.setBackground(clr);
    }
}
