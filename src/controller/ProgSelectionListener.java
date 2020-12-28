/*
ProgSelectionListener
Author: Vedad Coric
*/


package controller;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ProgramSelectionListener Class
 *
 * This class implements a ListSelectionListener
 * and is used to access the actual data container when
 * the user selects a row in the GUI table.
 */
public class ProgSelectionListener implements ListSelectionListener {

    JTable table;
    MainControll main;

    /**
     * ProgSelectionListener
     *
     * Constructor method that takes the GUI element JTable and
     * the main controller to get access to the data container,
     * ProgramListContainer.
     * @param table
     * @param main
     */
    public ProgSelectionListener(JTable table, MainControll main){
        this.table = table;
        this.main = main;
    }


    /**
     * On selecting a row in the GUI table, the given data
     * is obtained from the ProgramListContainer and updated in the
     * GUI description and image panel for the user.
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() ){
            String description = null;
            String stringUrl = null;
            int selectedRow = table.getSelectedRow();
            description = main.getPrograms().get(selectedRow).getDescription();
            if (main.getPrograms().get(selectedRow).getImageUrl() == null){
                stringUrl = "";
            } else {
                stringUrl =  main.getPrograms().get(selectedRow).getImageUrl();
            }
            main.getGui().programDescriptionPanel.setDescription(description);
            main.getGui().programLabelPanel.setProgramLabel(stringUrl);
        }
    }
}
