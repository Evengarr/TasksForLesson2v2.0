package TaskForLesson6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public ChatServer(){
        ServerSocket serverSocket = null;
        Socket socket;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            new Client(socket, "Сервер");
            while(true){
                if(socket.isClosed()){
                    System.out.println("Клиент отключился");
                    break;
                }
            }
            serverSocket.close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка сервера");
        } finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
