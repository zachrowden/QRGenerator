import net.glxn.qrgen.javase.QRCode
import org.junit.After
import org.junit.Before
import org.junit.Test

class EncryptionTest{

    @Before
    void setUp() {

    }

    @After
    void tearDown() {
    }

    @Test
    void testEncode() {
        String stringToEncode = "Encode Me Please"
        String encodedString = Encryption.encrypt(stringToEncode)

        assert encodedString != stringToEncode
    }

    @Test
    void testDecode(){
        String stringToEncode = "Decode Me Please"
        String encodedString = Encryption.encrypt(stringToEncode)
        String decryptedString = Encryption.decrypt(encodedString)

        assert decryptedString == stringToEncode

    }

    @Test
    void testTicketContentsEncryptedDecrypted(){

        String qrContents = new Ticket.TicketBuilder()
                .setOrderDate(new Date())
                .setId("#1234")
                .setFirstName("Zach")
                .setLastName("Rowden")
                .setShowDate(new Date(2017, 12, 01))
                .build().toString()

        String encryptedQRContent = Encryption.encrypt(qrContents)

        assert encryptedQRContent != qrContents

        String decryptedQRContents = Encryption.decrypt(encryptedQRContent)

        assert decryptedQRContents == qrContents
    }

    @Test
    void testGeneratesEncodedAndDecodedQrCode(){
        String dirPath    = "C:\\Users\\Zach\\Desktop\\Test\\"
        String qrCodeName = "qrImage.png"
        String encodedQrCodeName = "qrImageEncoded.png"
        int    qrWidth    = 250
        int    qrHeight   = 250
        String qrContents = new Ticket.TicketBuilder()
                .setOrderDate(new Date())
                .setId("#1234")
                .setFirstName("Zach")
                .setLastName("Rowden")
                .setShowDate(new Date(2017, 12, 01))
                .build().toString()

        File qrFile= new File("${dirPath}${qrCodeName}")
        OutputStream output = new FileOutputStream(qrFile)

        new QRCode(qrContents).withSize(qrWidth, qrHeight).writeTo(output)

        output.close()

        String encodedQrContents = Encryption.encrypt(qrContents)

        qrFile= new File("${dirPath}${encodedQrCodeName}")
        output = new FileOutputStream(qrFile)

        new QRCode(encodedQrContents).withSize(qrWidth, qrHeight).writeTo(output)

        output.close()



    }



}
