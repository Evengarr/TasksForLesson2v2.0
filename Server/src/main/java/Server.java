import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Server {
    private static final int DEFAULT_PORT = 8189;

    private final ConcurrentLinkedDeque<ClientHandler> clients;

    public Server(int port) {
        clients = new ConcurrentLinkedDeque<>();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("[ОТЛАДЧИК] Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = server.accept();
                System.out.println("[ОТЛАДЧИК] Клиент подключен");
                ClientHandler handler = new ClientHandler(socket, this);
                addClient(handler);
                new Thread(handler).start();
            }
        } catch (Exception e) {
            System.err.println("Сервер неисправен");
        }
    }

    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        System.out.println("[ОТЛАДЧИК] Клиент подключен в очередь вещания");
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("[ОТЛАДЧИК] Клиент удален из очереди вещания");
    }

    public void broadCastMessage(String msg) throws IOException {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

    public void privateMessage(String msg, String nick) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNick().equals(nick)) {
                client.sendMessage(msg);
            }
        }
    }

    public static void main(String[] args) {
        int port = -1;
        if (args != null && args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        if (port == -1) {
            port = DEFAULT_PORT;
        }
        new Server(port);
    }


}
