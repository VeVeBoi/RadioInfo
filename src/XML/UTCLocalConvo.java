/*
UTCLocalConvo
Author: Vedad Coric
*/

package XML;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * UTCLocalConvo
 *
 * This class parses a string that contains
 * dateTime in UTC and converts it to localtime
 */
public class UTCLocalConvo {

    LocalDateTime outDateTime;

    /**
     * UTCLocalConvo
     *
     * Constructor method that takes a string which has to
     * contain a datetime in UTC which is then parsed, converted
     * and stored in the instance.
     * @param dateTime
     */
    public UTCLocalConvo(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("GMT"));
        ZonedDateTime test = ZonedDateTime.parse(dateTime, formatter).withZoneSameInstant(ZoneId.systemDefault());
        outDateTime = test.toLocalDateTime();
    }

    /**
     * getDate()
     * Property accessor method.
     * @return LocaldateTime the parsed and converted localtime
     */
    public LocalDateTime getDate(){
        return outDateTime;
    }

}