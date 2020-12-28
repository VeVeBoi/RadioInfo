/*
ChannelModel
Author: Vedad Coric
*/
package model;

/**
 * ChannelModel Class
 *
 * This class represents a radio
 * channel specified by id, name and url.
 * from the 'Sveriges Radio' web API V2
 */
public class ChannelModel {
    int id;
    String name;
    String scheduleUrl;

    /**
     * ChannelModel
     * Constructor method that stores method arguments
     * in the new instance.
     * @param id
     * @param name
     * @param scheduleUrl
     */
    public ChannelModel(int id, String name, String scheduleUrl ){
        this.id = id;
        this.name = name;
        this.scheduleUrl = scheduleUrl;
    }

    /**
     * getId
     * property accessor for the channel id
     * according 'Sveriges Radio' web API V2
     * @return int channel id
     */
    public int getId(){
        return id;
    }

    /**
     * getName
     * property accessor for the channel name
     * according 'Sveriges Radio' web API V2
     * @return String channel name
     */
    public String getName(){
        return name;
    }

    /**
     * getScheduleUrl
     * property accessor for the Schedule Url
     * according 'Sveriges Radio' web API V2.
     * @return String schedul url
     */
    public String getScheduleUrl(){
        return scheduleUrl;
    }

}