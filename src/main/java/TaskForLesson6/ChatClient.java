package TaskForLesson6;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {

    public ChatClient() {
        try {
            Socket clientSocket = new Socket("localhost", 8189);
            new Client(clientSocket, "Пользователь");
            while(true){
                if(clientSocket.isClosed()){
                    break;
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
