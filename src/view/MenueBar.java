/*
MenueBar
Author: Vedad Coric
*/

package view;

import model.ChannelList;
import javax.swing.*;

/**
 * ManueBar Class
 *
 * This class extends JMenuBar and
 * contains two Menus, the File
 * and the Channel menu. It is doe
 * really easy to add more Menus.
 */
public class MenueBar extends JMenuBar{

    public FileManue fileMenu;
    public ChannelMenue channelMenu;

    /**
     * MenuBar
     * Constructor
     * This is where you can easily add more menus
     * @param channels ChannelListModel
     */
    public MenueBar(ChannelList channels){

        this.fileMenu = new FileManue();
        this.channelMenu = new ChannelMenue(channels);

        // By typing this.add(sting) you can add more menus.
        this.add(fileMenu);
        this.add(channelMenu);
    }
}