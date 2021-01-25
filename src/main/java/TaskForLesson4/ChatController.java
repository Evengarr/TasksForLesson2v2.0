package TaskForLesson4;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class ChatController {
    public TextField output;        //поле Чата
    public TextField input;        //поел ввода текста сообщения
    private final ArrayList<String> TextChat = new ArrayList<>();

    public void inputEnter() {   //кнопка ввода текста
        if (!input.getText().isEmpty()) {
            TextChat.add(input.getText());
            output.setText(String.format("%s %n", TextChat));
            input.clear();
        }

    }

    public void Enter(KeyEvent keyEvent) {                              // НЕ РАБОТАЕТ
        input.setOnKeyPressed(event -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                output.requestFocus();
            }
        });
    }
}


