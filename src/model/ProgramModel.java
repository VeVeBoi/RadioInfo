/*
ProgramModel
Author: Vedad Coric
*/

package model;

import java.time.LocalDateTime;

/**
 * ProgramModel Class
 *
 * This class represents a
 * radio program and functions
 * mostly as a data storage container.
 */
public class ProgramModel {

    private int programId;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageUrl;


    /**
     * ProgramModel
     *
     * The constructor method, just stores the compulsory
     * arguments into property variables.
     * @param programId int the Id of a radio program from 'Sveriges Radio' web API V2
     * @param name String the name of a radio program from 'Sveriges Radio' web API V2
     */
    public ProgramModel(int programId, String name){
        this.programId = programId;
        this.name = name;
    }

    /**
     * setDescription
     * setter method for description of radio program
     * @param description String from 'Sveriges Radio' web API V2
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * setStartTime
     * setter method for startTime of radio program
     * @param startTime LocalDate from 'Sveriges Radio' web API V2
     */
    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    /**
     * setEndTime
     * setter method for end time of radio program
     * @param endTime LocalDate from 'Sveriges Radio' web API V2
     */
    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }

    /**
     * setImageUrl
     * @param imageUrl String, image url from 'Sveriges Radio' web API V2
     */
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    /**
     * getName
     * getter method for name of radio program
     * @return String
     */
    public String getName(){
        return this.name;
    }

    /**
     * getStartTime
     * getter method for start time of radio program
     * @return LocalDateTime
     */
    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    /**
     * getEndTime
     * getter method for end time of radio program
     * @return LocalDateTime
     */
    public LocalDateTime getEndTime(){
        return this.endTime;
    }

    /**
     * getDescription
     * getter method for description of radio program
     * @return String
     */
    public String getDescription() { return this.description; }

    /**
     * getImageUrl
     * getter method for image url of radio program
     * @return String
     */
    public String getImageUrl() { return this.imageUrl; }
}