package MgrMain;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckOption extends JPanel {

    private class sTask extends TimerTask {
        @Override
        public void run() {
            CheckOption.this.UnBlink();
        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel                    lbl              = new JLabel("Uninitialized");

    JCheckBox                 val              = new JCheckBox();

    private final Color       bg               = this.getBackground();

    public CheckOption(final String name, boolean StartValue) {
        lbl.setText(name);
        val.setOpaque(true);
        val.setSelected(StartValue);
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

    public void SetValue(boolean value){
        val.setSelected(value);
    }
    
    public boolean GetValue(){
        return val.isSelected();
    }
}
