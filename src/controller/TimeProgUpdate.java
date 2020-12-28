/*
TimeProgUpdate
Author: Vedad Coric
*/
package controller;

import model.ChannelModel;
import model.ProgramList;
import view.TablePanel;
import javax.swing.*;

/**
 * TimeProgUpdate
 *
 * This class extends SwingWorker and is used as timer for a
 * hourly automatic update processes. Timing is obtained by
 * thread.sleep. After the waiting time, ProgBackgroundUpdate
 * is called for the update. A new instance of the timer is started
 * after the previous timer.
 */
public class TimeProgUpdate extends SwingWorker<Void, Object>{


    TablePanel tablePanel;
    ChannelModel channel;
    ProgramList programs;
    MainControll main;

    int BACKGROUND_WAIT_SECONDS = 3600;


    /**
     * TimeProgramUpdater
     * Constructor method that sets up the instance.
     * A new swing worker thread is started by calling
     * the execute method.
     * @param main MainController, gives access to all needed instances
     */

    public TimeProgUpdate(MainControll main){

        this.main = main;

    }


    /**
     * {@inheritDoc}
     * This method runs in the background as separate thread.
     * Here it is used as a timer with the granularity of a
     * second as time unit.
     * @return
     */
    @Override
    public Void doInBackground() {
        int counter = 0;
        while(!isCancelled()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Background Updater interrupted");
            }
            counter++;
            if(counter == BACKGROUND_WAIT_SECONDS) {
                break;
            }

        }

        return null;
    }

    /**
     * {@inheritDoc}
     * This method runs after the above timer thread quits. It
     * calls the actual updater and restarts the background
     * timer in a new thread.
     */
    @Override
    protected void done() {
        try {
            main.setProgramBackgroundUpdater(new ProgBackgroundUpdate(main.getCurrentChannel(), programs, tablePanel, main));
            main.getProgramBackgroundUpdater().execute();
            main.getTimedUpdater().cancel(true);
            main.setTimedUpdater(new TimeProgUpdate(main));
            main.getTimedUpdater().execute();

        } catch (Exception ignore) {

        }
    }

}