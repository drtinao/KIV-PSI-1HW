import java.io.IOException;
import java.net.*;

/**
 * Contains various methods which contains application logic - sending messages etc.
 */
public class Logic {
    /* constants - START */
    private static final String MULTICAST_ADDR = "230.0.0.0"; //group address which is used for multicasting
    private static final int MULTICAST_PORT = 5000; //port used for multicasting
    /* constants - END */

    private InetAddress multiAddrInet; //object which represents IP address used for multicast

    /**
     * Constructor inits InetAddress object, which is later used for message multicast.
     */
    public Logic(){
        this.multiAddrInet = getGroupAddr();
        if(this.multiAddrInet == null){ //err while getting multicast addr
            return;
        }
    }

    /**
     * Returns initialized InetAddress object, which will be used in order to multicast messages to network.
     * @return initialized InetAddress object
     */
    public InetAddress getGroupAddr(){
        try {
            return InetAddress.getByName(MULTICAST_ADDR);
        } catch (UnknownHostException e) {
            System.out.println("Error while getting multicast address...");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Used for multicasting given message to network.
     * @param messageToSend message which should be multicasting to network
     */
    public void sendMulticastMessage(String messageToSend){
        System.out.println("Sending " + messageToSend + " to multicast IP: " + MULTICAST_ADDR + ", port: " + MULTICAST_PORT);
        DatagramSocket dgSock = null;
        try {
            dgSock = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Error while creating socket...");
            e.printStackTrace();
            return;
        }

        byte[] mesToSendBytes = messageToSend.getBytes();
        DatagramPacket dgPacket = new DatagramPacket(mesToSendBytes, mesToSendBytes.length, multiAddrInet, MULTICAST_PORT);
        try {
            dgSock.send(dgPacket);
            dgSock.close();
        } catch (IOException e) {
            System.out.println("Error while sending packet...");
            e.printStackTrace();
            return;
        }
    }
}
