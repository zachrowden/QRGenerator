package utils

import org.apache.fop.apps.Fop
import org.apache.fop.apps.FopFactory
import org.apache.fop.apps.MimeConstants

import javax.xml.transform.Result
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.sax.SAXResult
import javax.xml.transform.stream.StreamSource

class FopTransform {

    private FopTransform(){}

    static final void transformTicket(File output){

        // the XSL FO file
        File xsltFile = new File("src\\main\\resources\\XslStyleSheets\\TicketXsl.xsl")
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("src\\test\\resources\\GeneratedXml\\ticketToXml.xml"))
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance()
        // Setup output
        OutputStream out = new java.io.FileOutputStream(output)

        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out)

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance()
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile))

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler())

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res)
        } finally {
            out.close()
        }
    }

}
