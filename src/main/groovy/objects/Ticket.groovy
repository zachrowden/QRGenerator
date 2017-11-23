package objects

final class Ticket {

    final String id, firstName, lastName
    final Date   orderDate, showDate

    Ticket(TicketBuilder ticketBuilder){
        this.id        = ticketBuilder.id
        this.firstName = ticketBuilder.firstName
        this.lastName  = ticketBuilder.lastName
        this.orderDate = ticketBuilder.orderDate
        this.showDate  = ticketBuilder.showDate
    }

    @Override
    String toString(){
        new String("${id}\n${firstName}\n${lastName}\n${orderDate.toString()}\n${showDate.toString()}")
    }

    static class TicketBuilder{

        String id, firstName, lastName
        Date   orderDate, showDate

        TicketBuilder(){}

        TicketBuilder setId(String value){
            this.id = value
            this
        }

        TicketBuilder setFirstName(String value){
            this.firstName = value
            this
        }

        TicketBuilder setLastName(String value){
            this.lastName = value
            this
        }

        TicketBuilder setOrderDate(Date value){
            this.orderDate = value
            this
        }

        TicketBuilder setShowDate(Date value){
            this.showDate = value
            this
        }

        Ticket build(){
            new Ticket(this)
        }
    }
}
