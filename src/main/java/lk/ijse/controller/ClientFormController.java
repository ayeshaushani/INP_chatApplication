package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {

    public Button btnDisconnect;
    public TextField clientUserName;
    @FXML
    private TextArea clientTextArea;

    @FXML
    private TextField clientTextField;

    @FXML
    private Label lblClient;

    @FXML
    private AnchorPane rootclient;

    @FXML
    private Button sendbtn;

    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String msg = "";

    public void initialize() {
        new Thread(()-> {
            try {
                socket = new Socket("localhost",3000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                clientTextArea.appendText(msg + "Client started. \n");


            while (!socket.isClosed()) {

                    msg = dataInputStream.readUTF();
                    clientTextArea.appendText("Server: "+msg + "\n");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }


        }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        String text = clientTextField.getText();
        String username = clientUserName.getText();
        clientTextArea.appendText(username+ ": "+ " "+ text + "\n");
        dataOutputStream.writeUTF(text);
        dataOutputStream.flush();
        clientTextField.clear();

    }

    public void btnDisconnectOnAction(ActionEvent actionEvent) {
        clientTextArea.clear();

    }
}
