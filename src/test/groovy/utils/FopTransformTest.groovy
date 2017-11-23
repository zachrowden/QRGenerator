package utils

import org.junit.After
import org.junit.Before
import org.junit.Test

class FopTransformTest {

    @Before
    void setUp() {
    }

    @After
    void tearDown() {
    }

    @Test
    void testTicketTransform(){

        //TODO: Test the transform and fix the '!' in the generated pdf
        FopTransform.transformTicket(new File("src\\test\\resources\\GeneratedPdf\\transformedTicket.pdf"))
    }


}
