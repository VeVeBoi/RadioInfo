/*
ChannelMenueListener
Author: Vedad Coric
*/
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ChannelMenueListener Class
 *
 * This class implements the ActionListener Class and is
 * responsible for catching events from the channel menue
 * in the program. On a given selection of a channel menue
 * entry it will init the update of the instance of Programlist.
 * Update is synchronized
 */
public class ChannelMenueListener implements ActionListener {
    MainControll main;

    /**
     * ChannelMenuListener
     * Constructor takes a MainController instance as argument.
     * @param main
     */
    public ChannelMenueListener(MainControll main){
        this.main = main;
    }

    /**
     * This method will start a ProgBackgroundUpdate
     * @param e
     */
    @Override
    public void actionPerformed(final ActionEvent e){
        int cIndex;
        for(cIndex = 0; cIndex < main.getChannels().size(); cIndex++ ){
            if(main.getChannels().get(cIndex).getName().equals(e.getActionCommand())){
                main.setCurrentChannel(main.getChannels().get(cIndex));
            }
        }
        if(e.getActionCommand().equals(main.getCurrentChannel().getName())) {
            main.setProgramBackgroundUpdater(new ProgBackgroundUpdate(main.getCurrentChannel(),
                    main.getPrograms(),
                    main.getGui().tablePanel,
                    main));
            main.getProgramBackgroundUpdater().execute();
        }
    }

}