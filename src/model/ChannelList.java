/*
ChannelList
Author: Vedad Coric
*/

package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ChannelListModel Class
 *
 * This class provides the Data container for the
 * ChannelModel objects. It extends the ArrayList and
 * implements the 'InterfaceSubject' interface to function
 * as observable from the design pattern.
 */
public class ChannelList extends ArrayList<ChannelModel> implements InterfaceSubject {
    private final ArrayList<InterfaceObserver> observers;

    /**
     * ChannelList
     * Constructor method
     */
    public ChannelList() {
        observers = new ArrayList<>();
    }

    /**
     * loadList
     * Method to fill the ChannelList
     * with ChannelModels obtained by an iterator
     * from the XMLChannelParser
     * @param iterator
     */
    public void loadList(Iterator<ChannelModel> iterator){
        this.clear();
        while(iterator.hasNext()){
            add(iterator.next());
        }
        notifyObservers();
    }

    /**
     * register
     * method to register Observers
     * of the current instance
     * @param obj
     */
    @Override
    public void register(InterfaceObserver obj) {
        observers.add(obj);
    }

    /**
     * unregister
     * method to unregister Observers
     * from the current instance
     * @param obj
     */
    @Override
    public void unregister(InterfaceObserver obj) {
        observers.remove(obj);
    }

    /**
     * notifyObservers
     * notification method that notifies
     * the registered observers.
     */
    @Override
    public void notifyObservers() {

        if(observers != null){
            for(InterfaceObserver observer : observers){
                observer.update();
            }
        }
    }
}