/*
InterfaceSubject
Author: Vedad Coric
*/

package model;

/**
 * InterfaceSubject interface
 *
 * This interface is used to implement
 * the observer-observable pattern.
 * It is implemented by the observable.
 */
public interface InterfaceSubject {

    /**
     * register
     * Method used to register observers
     * on the observable.
     */
    public void register(InterfaceObserver obj);

    /**
     * unregister
     * Method used to unregister observers
     * from the observable.
     * @param obj
     */
    public void unregister(InterfaceObserver obj);

    /**
     * notify
     * method to notify the observers
     */
    public void notifyObservers();

}