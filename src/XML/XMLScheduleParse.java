/*
XMLScheduleParse
Author: Vedad Coric
*/
package XML;

import model.ProgramList;
import model.ProgramModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

/**
 * XMLScheduleParse Class
 *
 * This class parses the program obtained by a
 * XMLReader instance from the 'Sveriges Radio' web
 * API V2.
 */
public class XMLScheduleParse implements Iterable<ProgramModel>{

    NodeList nodeList;
    ProgramList programList = new ProgramList();

    /**
     * XMLScheduleParse
     * Constructor method
     * @param channelId int channel Id from SR's web API V2
     * @param localDate localDate object, date of program to be loaded
     */
    public XMLScheduleParse(int channelId, LocalDate localDate){
        ArrayList<LocalDate> threeDates = this.threeDateRange(localDate);
        for(LocalDate date : threeDates){
            String SCHEDULE_URL = "http://api.sr.se/api/v2/scheduledepisodes?channelid=";
            StringBuilder urlBuilder = new StringBuilder(SCHEDULE_URL);
            urlBuilder.append(channelId);
            /* add date as string */
            String formattedString = date.format(ISO_LOCAL_DATE);
            urlBuilder.append("&date=" + formattedString);
            /* choose large size to include all programs */
            urlBuilder.append("&size=1000");
            this.ScheduleParser(urlBuilder.toString());
        }
    }

    /**
     * ScheduleParser
     * Actual parse method that will
     * fill a ProgramListModel with ProgramModel
     * objects.
     * @param stringUrl
     */
    private void ScheduleParser(String stringUrl){
        XMLReader xmlReader = new XMLReader(stringUrl);
        nodeList = xmlReader.doc.getElementsByTagName("scheduledepisode");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementEp = (Element) node;
                Element elementProg;
                ProgramModel tempProgram;
                elementProg = (Element) elementEp.getElementsByTagName("program").item(0);
                int programid = Integer.parseInt(elementProg.getAttribute("id"));
                String name = elementProg.getAttribute("name");
                /* Check if there is a more detailed title tag available */
                Node titleNode = elementEp.getElementsByTagName("title").item(0);
                if (titleNode != null){
                    name = titleNode.getTextContent();
                }
                tempProgram = new ProgramModel(programid, name);
                /* parse Description, if available */
                Node descriptionNode = elementEp.getElementsByTagName("description").item(0);
                if (descriptionNode != null){
                    tempProgram.setDescription(descriptionNode.getTextContent());
                }
                /* parse image url, if available */
                Node imageUrlNode = elementEp.getElementsByTagName("imageurl").item(0);
                if(imageUrlNode != null){
                    tempProgram.setImageUrl(imageUrlNode.getTextContent());
                }
                /* parse start time */
                LocalDateTime start = new UTCLocalConvo(
                        elementEp.getElementsByTagName("starttimeutc").item(0).getTextContent()).getDate();
                /* parse end time */
                LocalDateTime end = new UTCLocalConvo(
                        elementEp.getElementsByTagName("endtimeutc").item(0).getTextContent()).getDate();
                tempProgram.setStartTime(start);
                tempProgram.setEndTime(end);
                programList.add(tempProgram);
            }
        }
    }

    public Iterator<ProgramModel> iterator() {
        return programList.iterator();
    }

    /**
     * threeDateRange
     * Helper method that returns an ArrayList with a range
     * of three LocalDate objects from one date before to
     * one day after the input argument middleDate. This is used
     * to guarantee a complete set of programs for 12 hours before and
     * after the current time.
     * @param middleDate LocalDate middelDate
     * @return ArrayList that contains three LocalDate objects
     */
    private ArrayList<LocalDate> threeDateRange(LocalDate middleDate){
        ArrayList<LocalDate> threeDateRange = new ArrayList<>();
        LocalDate dateToday = middleDate;
        LocalDate dateTomorrow = dateToday.plusDays(1);
        LocalDate dateYesterday = dateToday.minusDays(1);
        threeDateRange.add(dateYesterday);
        threeDateRange.add(dateToday);
        threeDateRange.add(dateTomorrow);
        return threeDateRange;
    }


}