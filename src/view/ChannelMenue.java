/*
ChannelMenue
Author: Vedad Coric
*/

package view;

import model.ChannelList;
import model.ChannelModel;
import model.InterfaceObserver;
import javax.swing.*;
import java.util.ArrayList;

/**
 * ChannelMenu Class
 *
 * This UI class extends JMenu and implements the
 * InterfaceObserver interface. It is dynamically filled
 * with channels from a ChannelListModel object.
 * It can be updated automatically by registering
 * the instance to a InterfaceSubject implementing instance,
 * and by pressing the refresh button
 */
public class ChannelMenue extends JMenu implements InterfaceObserver {

    public ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();
    public ChannelList channels;

    /**
     * ChannelMenue
     * Constructor method
     * @param channels ChannelListModel
     */
    public ChannelMenue(ChannelList channels){

        super("Channels");

        this.channels = channels;

        for(ChannelModel channel : channels){
            menuItems.add(new JMenuItem(channel.getName()));
        }

        for (JMenuItem item : menuItems){
            this.add(item);
        }

        channels.register(this);

    }

    /**
     * updateChannels
     * This method re-reads the ChannelListModel
     * object and updates as such it's menu items.
     */
    public void updateChannels(){
        for(ChannelModel channel : channels){
            menuItems.add(new JMenuItem(channel.getName()));
        }

        for (JMenuItem item : menuItems){
            this.add(item);
        }
    }

    /**
     * This method is called from the ISubject
     * implementing instance to provoke the
     * update of the channels.
     */
    @Override
    public void update() {
        updateChannels();
        this.repaint();
    }

}