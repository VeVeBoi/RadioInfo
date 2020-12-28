/*
XMLChannelParse
Author: Vedad Coric
*/
package XML;

import model.ChannelList;
import model.ChannelModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Iterator;

/**
 * XMLChannelParse Class
 *
 * This class is used to parse the channel list from
 * "Sveriges Radio" web API v2 obtained by a XMLReader instance.
 */
public class XMLChannelParse implements Iterable<ChannelModel>{

    private NodeList nodeList;
    private ChannelList channelList;
    private String CHANNEL_URL = "http://api.sr.se/api/v2/channels";

    /**
     * XMLChannelParse
     * Constructor that reads from the hardcoded
     * default web API address.
     * @param numberChannels int maximum number of channels to read
     */
    public XMLChannelParse(int numberChannels){
        StringBuilder urlBuilder = new StringBuilder(CHANNEL_URL);
        urlBuilder.append("/?size=" + numberChannels);
        this.ChannelParser(urlBuilder.toString());
    }

    /**
     * XMLChannelParse Constructor
     * Used for JUnit tests with local xml file
     * @param channelFile
     */
    public XMLChannelParse(String channelFile) {
        this.ChannelParser(channelFile);
    }

    /**
     * ChannelParser
     * Actual parser method for 'Sveriges Radio'
     * web API xml format 2
     * @param channelUrl string with the url to the web API
     *                   or a respective resource
     */
    public void ChannelParser(String channelUrl){
        XMLReader xmlReader = new XMLReader(channelUrl);
        this.channelList = new ChannelList();
        nodeList = xmlReader.doc.getElementsByTagName("channel");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getAttribute("id"));
                String name = element.getAttribute("name");
                if(element.getElementsByTagName("scheduleurl").getLength()!=0){
                    String scheduleUrl = element.getElementsByTagName("scheduleurl").item(0).getTextContent();
                    this.channelList.add(new ChannelModel(id, name, scheduleUrl));
                }
            }
        }
    }

    /**
     * Iterator method
     * @return Iterator that iterates over ChannlListModel
     * of obtained channels.
     */
    public Iterator<ChannelModel> iterator() {
        return channelList.iterator();
    }

}