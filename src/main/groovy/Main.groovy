import net.glxn.qrgen.javase.QRCode

class Main {

        static final String dirPath    = "C:\\Users\\Zach\\Desktop\\Test\\"
        static final String qrCodeName = "qrImage.png"
        static final int    qrWidth    = 250
        static final int    qrHeight   = 250
        static final String qrContents = new Ticket.TicketBuilder()
                                            .setOrderDate(new Date())
                                            .setId("#1234")
                                            .setFirstName("Zach")
                                            .setLastName("Rowden")
                                            .setShowDate(new Date(2017, 12, 01))
                                            .build().toString()


    static void main(String[] args){

        File qrFile= new File("${dirPath}${qrCodeName}")
        OutputStream output = new FileOutputStream(qrFile)

        String encodedQrContents = Encryption.encrypt(qrContents)

        new QRCode(encodedQrContents).withSize(qrWidth, qrHeight).writeTo(output)

        output.close()

    }
}
