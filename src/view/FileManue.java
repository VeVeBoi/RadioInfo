/*
FileMenue
Author: Vedad Coric
*/
package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ArrayList;

/**
 * FileMenue
 *
 * Constructing a file menu and adding
 * menu items to it.
 */
public class FileManue extends JMenu {

    public ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();

    /**
     * FileMenu
     * Constructor method
     */
    public FileManue(){
        super("File");
        menuItems.add(new JMenuItem("Update"));
        menuItems.add(new JMenuItem("Quit"));
        menuItems.add(new JMenuItem("Info"));

        for (JMenuItem item : menuItems){
            this.add(item);
        }

    }


}