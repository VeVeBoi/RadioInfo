/*
XMLReader
Author: Vedad Coric
*/
package XML;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * XMLReader Class
 *
 * This class provides the DOM document read from an URL that provides
 * the XML data. It is used in conjunction with the Channel and the ScheduleParser.
 */
public class XMLReader {

    Document doc = null;

    /**
     * XMLReader
     *
     * Constructor method that sets up the XML DOM parser
     * with a URL and provides access to the parsed DOM
     * document.
     * @param stringUrl
     */
    public XMLReader(String stringUrl){
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(String.valueOf(stringUrl));
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}