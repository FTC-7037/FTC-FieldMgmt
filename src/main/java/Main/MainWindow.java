/**
 * 
 */
package Main;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Matthew Glennon (mglennon@virginiafirst.org)
 * https://github.com/VirginiaFIRST/FTC-FieldMgmt
 */
public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    final String AppTitle = "FTC Field Mgmt Overview System";
    final Logger logger = LoggerFactory.getLogger(Main.class);
    
    private Field Field1 = new Field(1);
    private Field Field2 = new Field(2);
    
    public MainWindow(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logger.info("Main Window is closing.");
                Main.Quit();
            }
        });
        this.setTitle(AppTitle);
        this.setSize(1000, 500);
        this.setLayout(new GridLayout(0, 2, 0, 0));
        
        this.getContentPane().add(Field1);
        this.getContentPane().add(Field2);
        this.invalidate();
    }
    public void UpdateField(FCSMsg msg){
        if(msg.iKeyPart1 == 1) Field1.UpdateField(msg);
        if(msg.iKeyPart1 == 2) Field2.UpdateField(msg);
        Field1.repaint();
        Field2.repaint();
    }
}
