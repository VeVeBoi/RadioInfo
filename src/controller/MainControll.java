/*
MainControll
Author: Vedad Coric
*/

package controller;

import XML.XMLChannelParse;
import XML.XMLScheduleParse;
import model.ChannelList;
import model.ChannelModel;
import model.ProgramList;
import view.GUI;

import javax.swing.*;
import java.time.LocalDate;

/**
 * MainController Class
 * this class holds together the application, hosting the
 * main data containers (the channel list
 * and the program list). The MainControll class is
 * also in charge of setting up additional
 * services such as menu listeners, initial loading
 * of data etc. It also starts the timed background
 * updater routine.
 */
public class MainControll {

    private ChannelList channel;
    private ChannelModel currentChannel;
    private ProgramList program;
    private FileMenueListener fileMenuListener;
    private TimeProgUpdate timedUpdater;
    private volatile ProgBackgroundUpdate programBackgroundUpdater;
    private GUI gui;


    /**
     * MainController
     * Constructor method for MainController.
     * @param gui Gui stores a reference to the gui object
     * @param channels ChannelListModel stores a reference to the channel list
     * @param programs ProgramListModel stores a reference to the program list
     */
    public MainControll(GUI gui, ChannelList channels, ProgramList programs){

        this.gui = gui;
        this.channel = channels;
        this.program = programs;

        /* (re)load Channels List */
        XMLChannelParse channelGetter = new XMLChannelParse(100);
        this.channel.loadList(channelGetter.iterator());

        /* initially set current channel */
        this.currentChannel = channels.get(0);
        fileMenuListener = new FileMenueListener(this);


        /* (re)load Programs List */
        XMLScheduleParse parser = new XMLScheduleParse(this.currentChannel.getId(), LocalDate.now());
        this.program.load(parser.iterator());
        this.program.prune12Hours();
        this.program.sortTime();


        /* add listeners in file menu */
        for(JMenuItem item : gui.menuBar.fileMenu.menuItems){
            item.addActionListener(fileMenuListener);
        }

        /* add listeners in Channel menu */
        for (int channel_no = 0; channel_no < channels.size(); channel_no++){
            ChannelMenueListener tempListener;
            tempListener = new ChannelMenueListener(this);
            gui.menuBar.channelMenu.menuItems.get(channel_no).addActionListener(tempListener);
        }

        /* add click listener in Table */
        ProgSelectionListener programListener;
        programListener = new ProgSelectionListener(gui.tablePanel.getTable(), this);
        gui.tablePanel.addSelectionListener(programListener);
        gui.show();

        timedUpdater = new TimeProgUpdate(this);
        timedUpdater.execute();

    }

    /**
     * getChannels
     * property accessor
     * @return ChannelListModel reference to the channel list
     */
    public ChannelList getChannels(){
        return channel;
    }

    /**
     * getPrograms
     * property accessor
     * @return ProgramListModel reference to the program list
     */
    public ProgramList getPrograms(){
        return program;
    }

    /**
     * getGui
     * property accessor
     * @return Gui reference to the gui instance
     */
    public GUI getGui(){
        return gui;
    }

    /**
     * getCurrentChannel
     * property accessor
     * @return ChannelModel a reference to the currently selected channel
     */
    public ChannelModel getCurrentChannel(){
        return currentChannel;
    }

    /**
     * setCurrentChannel
     * property setter
     * @param currentChannel ChannelModel sets the currently selected channel
     */
    public void setCurrentChannel(ChannelModel currentChannel){
        this.currentChannel = currentChannel;
    }

    /**
     * getProgramBackgroundUpdater
     *
     */
    public synchronized ProgBackgroundUpdate getProgramBackgroundUpdater(){
        return this.programBackgroundUpdater;
    }

    /**
     * setProgramBackgroundUpdater
     *
     */
    public synchronized void setProgramBackgroundUpdater(ProgBackgroundUpdate updater){
        this.programBackgroundUpdater = updater;
    }

    /**
     * get time updater
     */
    public synchronized TimeProgUpdate getTimedUpdater(){
        return this.timedUpdater;
    }

    /**
     * set TimedProgramUpdater
     */
    public synchronized void setTimedUpdater(TimeProgUpdate updater){
        this.timedUpdater = updater;
    }

    /**
     * set setEnable
     */
    public void setEnable(Boolean state){this.setEnable(state);}
}