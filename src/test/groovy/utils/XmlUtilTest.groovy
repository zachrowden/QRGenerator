package utils

import objects.Ticket
import org.junit.After
import org.junit.Before
import org.junit.Test

class XmlUtilTest{

    @Before
    void setUp() {
    }

    @After
    void tearDown() {
    }

    @Test
    void testTicketContentsToXml(){
        String xmlKey = new String(
                "<ticket>"                                 +"\n"+
                    "<id>#1234</id>"                       +"\n"+
                    "<firstname>Zach</firstname>"          +"\n"+
                    "<lastname>Rowden</lastname>"          +"\n"+
                    "<orderdate>${new Date()}</orderdate>" +"\n"+
                    "<showdate>${new Date(2017, 12, 01).toString()}</showdate>" +"\n"+
                "</ticket>")

        Ticket ticket = new Ticket.TicketBuilder()
                .setOrderDate(new Date())
                .setId       ("#1234")
                .setFirstName("Zach")
                .setLastName ("Rowden")
                .setShowDate (new Date(2017, 12, 01))
                .build()


        String xmlToCreateByTicket = XmlUtil.ticketContentsToXml(ticket)
        String xmlToCreateByString = XmlUtil.ticketContentsToXml(ticket.toString())


        assert xmlToCreateByTicket == xmlKey
        assert xmlToCreateByString == xmlKey
    }

    @Test
    void testXmlWriteToFile(){
        String fileName = "ticketToXml.xml"

        File fileToWrite = new File("src\\test\\resources\\GeneratedXml\\${fileName}")
        fileToWrite.createNewFile()

        String xmlToWrite = XmlUtil.ticketContentsToXml(new Ticket.TicketBuilder()
                .setOrderDate(new Date())
                .setId       ("#1234")
                .setFirstName("Zach")
                .setLastName ("Rowden")
                .setShowDate (new Date(2017, 12, 01))
                .build())

        boolean isWriteSuccessful = XmlUtil.writeXmlToFile(xmlToWrite, fileToWrite)

        assert isWriteSuccessful == true

        fileToWrite.delete()
    }

}
