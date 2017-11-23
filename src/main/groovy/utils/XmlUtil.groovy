package utils

import objects.Ticket

import java.util.logging.Logger

final class XmlUtil {

    static final Logger logger = Logger.getLogger(this.getClass().name)

    private XmlUtil(){}

    static final String ticketContentsToXml(Ticket ticket){
        String id        = ticket.id
        String firstName = ticket.firstName
        String lastName  = ticket.lastName
        Date   orderDate = ticket.orderDate
        Date   showDate  = ticket.showDate

        new String(
                        "<ticket>"                                +"\n"+
                            "<id>${id}</id>"                      +"\n"+
                            "<firstname>${firstName}</firstname>" +"\n"+
                            "<lastname>${lastName}</lastname>"    +"\n"+
                            "<orderdate>${orderDate}</orderdate>" +"\n"+
                            "<showdate>${showDate}</showdate>"    +"\n"+
                        "</ticket>"
        )
    }

    static final String ticketContentsToXml(String ticketContents){

        List   contents  = []

        ticketContents.eachLine {
            contents.add(it)
        }

        new String(
            "<ticket>"                                  +"\n"+
                "<id>${contents[0]}</id>"               +"\n"+
                "<firstname>${contents[1]}</firstname>" +"\n"+
                "<lastname>${contents[2]}</lastname>"   +"\n"+
                "<orderdate>${contents[3]}</orderdate>" +"\n"+
                "<showdate>${contents[4]}</showdate>"   +"\n"+
            "</ticket>"
        )
    }

    static final boolean writeXmlToFile(String xml, File file){
        if(file.exists() && xml.isEmpty() == false){
            file.write(xml)
        }
        else{
            logger.warning("File exist: ${file.exists()} XML empty: ${xml.empty}")
            return false
        }
        true
    }

}
