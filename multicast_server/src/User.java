import java.util.Scanner;

/**
 * Contains various methods which are used for interaction with user.
 */
public class User {
    /* constants - START */
    private static final String DEFAULT_MESSAGE = "Default hello from multicast server!"; //default message used for multicast test
    private static final int MAX_MES_BYTES = 1024; //max length of message (bytes) ; should be same on client + server
    /* constants - END */

    private Scanner userSc; //Scanner used for reading user input

    /**
     * Constructor just inits Scanner object.
     */
    public User(){
        this.userSc = new Scanner(System.in);
    }

    /**
     * Prompts user to enter message which should be used for testing multicast.
     * @return string which was entered by user
     */
    public String readMesToSend(){
        System.out.println("Enter text to send (or leave empty to use default): ");
        String userInp = userSc.nextLine();
        if(userInp.length() == 0){ //use default text
            userInp = DEFAULT_MESSAGE;
        }else if(userInp.getBytes().length > MAX_MES_BYTES){
            System.out.println("Your message exceeds limit " + MAX_MES_BYTES + "! Sending default...");
            userInp = DEFAULT_MESSAGE;
        }
        System.out.println("Using string: " + userInp);
        return userInp;
    }

    /**
     * Used for closing Scanner on app exit.
     */
    public void closeScanner(){
        userSc.close();
    }
}
