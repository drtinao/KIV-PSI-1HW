/**
 * Class contains entry point of application (main method) and therefore is used for application start.
 */
public class Main {
    /**
     * Method is responsible for application start and calls methods from other classes to achieve message multicast - sender.
     * @param args arguments not used
     */
    public static void main(String[] args){
        User user = new User(); //for user interaction
        Logic logic = new Logic(); //contains app logic

        System.out.println("***UDP multicast server - START***");
        String mesToSend = user.readMesToSend();
        logic.sendMulticastMessage(mesToSend);
        System.out.println("***UDP multicast server - END***");
        user.closeScanner();
    }
}
