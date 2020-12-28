/*
ProgramList
Author: Vedad Coric
*/
package model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * ProgramListModel Class
 *
 * This class provides the container for
 * ProgramModel objects. it contains some helper
 * methods to obtain a 12+- hour collection of
 * programs from the XMLParser.
 * The class implements the ISubject Interface
 */
public class ProgramList extends ArrayList<ProgramModel> implements InterfaceSubject {

    private ArrayList<InterfaceObserver> observers;
    private InterfaceObserver obj;

    /**
     * ProgramList
     * Constructor method initialized the
     * arraylist for the observers
     */
    public ProgramList(){
        observers = new ArrayList<>();
    }

    /**
     * load
     * This method is used with an iterator
     * to fill the list with ProgramModel objects
     * @param iterator for ProgramModel objects, usually obtained
     *                 from XMLScheduleParse
     */
    public void load(Iterator<ProgramModel> iterator){
        this.clear();
        while(iterator.hasNext()){
            add(iterator.next());
        }
    }

    /**
     * prune12Hours
     * This method is used to remove ProgramListModel objects that
     * are outside of a +/- 12 hour range from the current local time
     */
    public void prune12Hours(){
        removeIf(x -> x.getStartTime().isBefore(LocalDateTime.now().minusHours(12)));
        removeIf(x -> x.getEndTime().isAfter(LocalDateTime.now().plusHours(12)));
    }

    /**
     * sortTime
     * This method sorts the ProrgramModel objects according to
     * their start time.
     */
    public void sortTime(){
        Collections.sort(this, new Comparator<ProgramModel>(){
            public int compare(ProgramModel o1, ProgramModel o2){
                if(o1.getStartTime().equals(o2.getStartTime()))
                    return 0;
                return o1.getStartTime().isBefore(o2.getStartTime()) ? -1 : 1;
            }
        });
    }


    /**
     * This method is used to register an observer on
     * the current ProgramList object
     * @param obj
     */
    @Override
    public void register(InterfaceObserver obj) {
        this.obj = obj;
        observers.add(obj);
    }

    /**
     * This method is used to unregister an observer from
     * the current ProgramList object
     * @param obj
     */
    @Override
    public void unregister(InterfaceObserver obj) {
        this.obj = obj;
        observers.remove(obj);
    }

    /**
     * This method is used to notify the registered
     * observers. It calls the update method of the
     * each registered observer.
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