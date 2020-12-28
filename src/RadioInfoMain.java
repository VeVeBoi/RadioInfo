import controller.MainControll;
import model.ChannelList;
import model.ProgramList;
import view.GUI;

import javax.swing.*;

/**
 * RadioInfoMain Class
 * This class contains the main class to
 * be run. It starts a new thread with the
 * gui and the MainController instance. The
 * main data containers channels and programs
 * are declerad and assigned.
 *
 */
public class RadioInfoMain {

    /**
     * main
     * method to be invoked for starting
     * the application.
     * @param args
     */
    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChannelList channels = new ChannelList();
                ProgramList programs = new ProgramList();
                GUI gui = new GUI("Radio Info", channels, programs);
                MainControll main = new MainControll(gui, channels, programs);
            }
        });
    }
}
