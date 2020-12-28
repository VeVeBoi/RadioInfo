/*
ProgramDescripPanel
Author: Vedad Coric
*/

package view;

import javax.swing.*;
import java.awt.*;

/**
 * ProgramDescripPanel Class
 *
 * This class extends JPanel and constructs
 * the UI panel which contains the program
 * description text in a JTextArea
 */
public class ProgramDescripPanel extends JPanel {

    JTextArea textArea;
    String infoText;
    Font f = new Font("serif", Font.PLAIN, 13);

    /**
     * ProgramDescriptionPanel
     * Constructor method
     */
    public ProgramDescripPanel() {
        textArea = new JTextArea(15,32);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(f);
        textArea.setMinimumSize(new Dimension(280, 240));
        add(textArea);
    }

    /**
     * setDescription
     * method to set the program description.
     * The text area is emptied before a new
     * text is set.
     * @param infoText
     */
    public void setDescription(String infoText){
        this.infoText = infoText;
        this.textArea.setText(null);
        if (this.infoText.length() == 0){
            this.infoText = "No Description available";
        }
        this.textArea.append(this.infoText);
    }

}
