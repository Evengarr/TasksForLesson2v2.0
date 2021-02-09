import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public TextField input;
    public ListView<String> listView;
    private Network network;

    public void sendMessage() throws IOException {
        network.writeMessage(input.getText());
        input.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = Network.getInstance();

        new Thread(() -> {
            try {
                while (true) {
                    String message = network.readMessage();
                    if (message.equals("/выход")) {
                        network.close();
                        break;
                    }
                    Platform.runLater(() -> listView.getItems().add(message));
                }
            } catch (IOException ioException) {
                System.err.println("Сервер отключен");
                Platform.runLater(() -> listView.getItems().add("Сервер отключен"));
            }
        }).start();
    }

}
