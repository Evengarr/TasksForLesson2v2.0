import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server server;
    private DataOutputStream out;
    private final boolean running;
    private final String nickName;
    private static int cnt = 0;
    private static final String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
        cnt++;
        nickName = "Гость#" + cnt;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("[ОТЛАДЧИК] Клиент начинает работу");
            while (running) {
                String msg = in.readUTF();
                if (msg.equalsIgnoreCase("/выход")) {
                    sendMessage("/Клиент отключился");
                    //out.writeUTF(msg);
                    break;
                }
                if (msg.startsWith("/w")) {      //отслеживание написания в личку
                    String[] words = msg.split(" ");
                    String nickOfTheReceiver = words[1];
                    String message = msg.substring(11);
                    server.privateMessage(timeStamp + "\n" + " [ЛИЧНОЕ СООБЩЕНИЕ] " + nickName + ": " + message, nickOfTheReceiver);
                } else {
                    server.broadCastMessage(timeStamp + "\n" + nickName + ": " + msg);
                }
                System.out.println("[ОТЛАДЧИК] Сообщение от клиента: " + msg);
            }
        } catch (Exception e) {
            System.err.println("Соединение было разорвано");
            server.removeClient(this);
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }


    public Object getNick() {
        return nickName;
    }
}
