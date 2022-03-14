import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Contains various methods which contains application logic - receiving messages etc.
 */
public class Logic {
    /* constants - START */
    private static final String MULTICAST_ADDR = "230.0.0.0"; //group address which is used for multicasting
    private static final int MULTICAST_PORT = 5000; //port used for multicasting
    private static final int MAX_MES_BYTES = 1024; //size of buffer for messages ; should be same on client + server
    private static final String STOP_MULTICAST_WORD = "END"; //when keyword is received, client will end
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
     * Receives messages from multicast address until keyword defined by STOP_MULTICAST_WORD is received.
     */
    public void receiveMessages(){
        System.out.println("Attempting to receive multicast messages from IP: " + MULTICAST_ADDR + ", port: " + MULTICAST_PORT);

        MulticastSocket joinedSock = joinMulticastGroup();
        if(joinedSock == null){ //err while joining multicast group
            return;
        }

        DatagramPacket dgPacket = new DatagramPacket(new byte[MAX_MES_BYTES], MAX_MES_BYTES);
        while(true){
            System.out.println("Waiting for message - send \"" + STOP_MULTICAST_WORD + "\" to stop client");
            try {
                joinedSock.receive(dgPacket);
                String receivedMes = new String(dgPacket.getData(), dgPacket.getOffset(), dgPacket.getLength());
                System.out.println("Received message: " + receivedMes);
                if(receivedMes.equals(STOP_MULTICAST_WORD)){
                    joinedSock.leaveGroup(multiAddrInet);
                    return;
                };
            } catch (IOException e) {
                System.out.println("Error while receiving multicast message...");
                e.printStackTrace();
                return;
            }
        }
    }

    /**
     * Used for joining multicast group.
     * @return initialized MulticastSocket object
     */
    public MulticastSocket joinMulticastGroup(){
        try {
            MulticastSocket multiSock = new MulticastSocket(MULTICAST_PORT);
            multiSock.joinGroup(multiAddrInet);
            return multiSock;
        } catch (IOException e) {
            System.out.println("Error while joining multicast group...");
            e.printStackTrace();
            return null;
        }
    }
}
