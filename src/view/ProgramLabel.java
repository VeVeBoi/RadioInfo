/*
ProgramLabel
Author: Vedad Coric
*/
package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ProgramLable Class
 *
 * This UI class extends JPanel and constructs
 * a panel to visualize the image from the
 * radio program. The image is loaded dynamically
 * from the web. If there is no image then a default
 * image will be loaded instead.
 */
public class ProgramLabel extends JPanel {

    JLabel programLabel;
    String programLabelUrl;
    //init for the default image
    private final ImageIcon defaultImage = new ImageIcon("src/resources/default.png");

    /**
     * ProgramLabelPanel
     * Constructor
     */
    public ProgramLabel() {
        programLabel = new JLabel();
        add(programLabel);
    }

    /**
     * setProgramLabel
     * Method to set the image. The image
     * is provided through a string that
     * contains the url to the image.
     * @param stringUrl String
     */
    public void setProgramLabel(String stringUrl){
        this.programLabelUrl = stringUrl;
        Image image = null;
        ImageIcon programLabel;
        if (stringUrl.length()== 0){
            // if the imgUrl is null then the default image is set.
            programLabel = defaultImage;
        } else {
            try {
                URL url = new URL(programLabelUrl);
                image = ImageIO.read(url);
            } catch (MalformedURLException e) {
                System.out.println("Image could not be loaded: Malformed URL");
            } catch (IOException e) {
                System.out.println("Image could not be loaded: IOException");
            }
            Image scaledImage = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
            programLabel = new ImageIcon(scaledImage);
        }
        this.programLabel.setIcon(programLabel);
    }
}