/*
GUI
Author: Vedad Coric
*/
package view;

import model.ChannelList;
import model.ProgramList;

import javax.swing.*;
import java.awt.*;

/**
 * GUI Class
 *
 * This is the main UI class that contains
 * the JFrame as the basic container of the
 * whole application UI. The gui uses a simple
 * three panel design with a GridLayout manager.
 */
public class GUI {
    public JFrame frame;
    public MenueBar menuBar;
    public TablePanel tablePanel;
    public ProgramDescripPanel programDescriptionPanel;
    public ProgramLabel programLabelPanel;

    /**
     * GUI
     * Constructor method that builds
     * the whole ui.
     * @param title String title shown in the UI window
     * @param channels ChanelListModel from here the channel menu
     *                 is populated
     * @param programs ProgramListModel from here the main program
     *                 table is updated
     */
    public GUI(String title, ChannelList channels, ProgramList programs){

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        JPanel InfoContainer = new JPanel();
        InfoContainer.setLayout(new BorderLayout());
        /* construct the parts */
        frame = new JFrame(title);
        frame.setMinimumSize(new Dimension(1280, 720));
        tablePanel = new TablePanel(programs);
        programDescriptionPanel = new ProgramDescripPanel();
        programLabelPanel = new ProgramLabel();
        menuBar = new MenueBar(channels);
        /* assemble parts */
        tablePanel.setPreferredSize(new Dimension(1000, 720));
        container.add(tablePanel, BorderLayout.EAST);
        InfoContainer.add(programDescriptionPanel, BorderLayout.NORTH);
        InfoContainer.add(programLabelPanel, BorderLayout.SOUTH);
        container.add(InfoContainer, BorderLayout.WEST);
        frame.add(container);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * show
     * method to visualize the gui after construction
     */
    public void show(){
        frame.setVisible(true);
    }

}
