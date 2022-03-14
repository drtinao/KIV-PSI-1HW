/**
 * Class contains entry point of application (main method) and therefore is used for application start.
 */
public class Main {
    /**
     * Method is responsible for application start and calls methods from other classes to achieve message multicast - receiver.
     * @param args arguments not used
     */
    public static void main(String[] args){
        Logic logic = new Logic(); //contains app logic

        System.out.println("***UDP multicast client - START***");
        logic.receiveMessages(); //listen for messages on multicast address in a loop
        System.out.println("***UDP multicast client - END***");
    }
}
