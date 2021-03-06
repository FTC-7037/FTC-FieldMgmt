/**
 * 
 */
package SoundGenerator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import MgrMain.CheckOption;
import MgrMain.Main;

/**
 * @author Matthew Glennon (mglennon@virginiafirst.org)
 * https://github.com/VirginiaFIRST/FTC-FieldMgmt
 */
public class SoundTestWindow extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    final public static Logger logger = LoggerFactory.getLogger(Main.class);
    
    CheckOption loopChk = new CheckOption("Loop 5 times", false);
    
    public SoundTestWindow() {
        //this.setSize(1000, 500);
        this.setLayout(new GridLayout(0, 2, 0, 0));

        String files;
        File folder = new File(System.getProperty("user.dir")+SoundGen.sPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                if (files.endsWith("."+SoundGen.sFormat)) {
                    String filename = files.replace("."+SoundGen.sFormat, "");
                    logger.info("Buiding Test Btn: {}",filename);
                    JButton j = new JButton(filename);
                    j.addActionListener(new TestBtnPress());
                    this.add(j);
                }
            }
        }
        this.add(loopChk);
        
        JButton stop = new JButton("STOP");
        stop.addActionListener(new StopBtnPress());
        stop.setEnabled(false);
        this.add(stop);
        this.invalidate();
    }
    private class TestBtnPress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton j = (JButton)e.getSource();
            logger.info("Test Playing: {}",j.getText());
            // TODO: Turn this into a number selection so user can choose how many times we loop.
            int loop = 0;
            if(loopChk.GetValue()) loop = 5; 
            SoundGen.playSound(j.getText(),loop);
        }
        
    }
    private class StopBtnPress implements ActionListener {
        @Override
        // TODO: Actually make this work.
        public void actionPerformed(ActionEvent e) {
            logger.info("Stopping All Sound");
            SoundGen.StopAll();
        }
        
    }
}
