package e002_challenge;

public class Client {
    public static void main(String[] args) {
        System.out.println("Chain responsibility demo");

        // making the chain first: Fax -> email
        ReceiverInterface faxHandler, emailHandler;

        // end of chain
        emailHandler = new EmailErrorHandler();

        // fax handler is before email
        faxHandler = new FaxErrorHandler();
        faxHandler.setNextChain(emailHandler);

        // starting point: raiser will raise issues and set the first handler
        IssueRaiser raiser = new IssueRaiser(faxHandler);

        Message m1 = new Message("Fax afsafasfasfa", MessagePriority.Normal);
        Message m2 = new Message("Email afsafasfasfa", MessagePriority.High);
        Message m3 = new Message("afsafaFaxsfasfa", MessagePriority.Normal);
        Message m4 = new Message("afsafaEmailsfasfa", MessagePriority.High);

        raiser.raiseMessage(m1);
        raiser.raiseMessage(m2);
        raiser.raiseMessage(m3);
        raiser.raiseMessage(m4);
    }
}
