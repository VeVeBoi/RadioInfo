/*
ProgBackgroundUpdate
Author: Vedad Coric
*/


package controller;

import XML.XMLScheduleParse;
import model.ChannelModel;
import model.ProgramList;
import view.TablePanel;

import javax.swing.*;
import java.time.LocalDate;

/**
 * ProgramBackgroundUpdater Class
 *
 * This class extends SwingWorker class and is used
 * to update the ProgramListModel. A reference to the
 * ProgramListModel is expected to be stored in the main
 * controller.
 */
public class ProgBackgroundUpdate extends SwingWorker<ProgramList, Object> {

    TablePanel tablePanel;
    ChannelModel channel;
    ProgramList programs;
    MainControll main;

    /**
     * ProgBackgroundUpdate
     * Constructor method that assigns all arguments.
     * @param channel
     * @param programs
     * @param tablePanel
     * @param main
     */
    public ProgBackgroundUpdate(ChannelModel channel, ProgramList programs, TablePanel tablePanel, MainControll main){
        this.tablePanel = tablePanel;
        this.channel = channel;
        this.programs = programs;
        this.main = main;

    }

    /**
     * The doInBackground method contains the work
     * to be done in a separate thread. This includes
     * here to obtain the programs for the current data
     * using an XML parser and some data prep.
     * @return ProgramListModel
     */
    @Override
    public ProgramList doInBackground() {
        XMLScheduleParse parser = new XMLScheduleParse(channel.getId(), LocalDate.now());
        main.getPrograms().load(parser.iterator());
        main.getPrograms().prune12Hours();
        main.getPrograms().sortTime();
        return programs;
    }

    /**
     * Method executed after the background job is finshed.
     * Here a TableDataChanged event is fired
     */
    @Override
    protected void done() {
        try {
            main.getGui().tablePanel.getTable().updateUI();
            main.getGui().tablePanel.tableModel.renewData(programs);
        } catch (Exception ignore) {
        }
    }
}