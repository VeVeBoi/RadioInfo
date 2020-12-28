/*
FileMenueListener
Author: Vedad Coric
*/
package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileMenuListener Class
 *
 * This class implements the ActionListener class and is
 * responsible for catching events from the FileMenu.
 * Currently, it reacts on "quit", "reload" and "info"
 */
public class FileMenueListener implements ActionListener {

    MainControll main;

    /**
     * FileMenuListener
     * Constructor method takes a MainController instance as argument
     * @param main
     */
    public FileMenueListener(MainControll main) {
        this.main = main;

    }

    /**
     * This method will start a ProgBackgroundUpdate. It uses
     * for this a synchronized statement.
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e){
        if(e.getActionCommand().equals("Quit")) {
            /* graceful exit of the background swing worker thread for updates */
            main.getTimedUpdater().cancel(true);
            System.exit(0);
        }

        if(e.getActionCommand().equals("Update")){
            main.setEnable(false);
            main.setProgramBackgroundUpdater(new ProgBackgroundUpdate(main.getCurrentChannel(),
                    main.getPrograms(),
                    main.getGui().tablePanel,
                    main));
            main.getProgramBackgroundUpdater().execute();
            main.setEnable(true);
            //This is optional. Not necessary at all--->
            JOptionPane.showMessageDialog(null, "Updated");
        }

        if(e.getActionCommand().equals("Info")){
                    JOptionPane.showMessageDialog(null, "" +
                            "This App is an app that shows you when your next " +
                            "favorite show on all of Sweden's radio!\n" +
                            "when you press on the on one of the programs on the " +
                            "table then you'll get more information " +
                            "about the program and maybe a picture will show up.\n " +
                            "This programs starts on the 'P1' channel. " +
                            "\n\nAuthor: Vedad Coric");
        }

    }


}