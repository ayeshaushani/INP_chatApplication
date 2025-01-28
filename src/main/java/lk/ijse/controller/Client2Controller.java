package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2Controller {

    @FXML
    private Button btnDisconnect;

    @FXML
    private TextArea clientTextArea;

    @FXML
    private TextField clientTextField;

    @FXML
    private TextField clientUserName;

    @FXML
    private Label lblClient;

    @FXML
    private Button sendbtn;

    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String msg = "";

    public void initialize() {
       /* new Thread(()-> {
            try {
                socket = new Socket("localhost",3000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                clientTextArea.appendText(msg + "Client started. \n");


                while (true) {
                    try {
                        msg = dataInputStream.readUTF();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    clientTextArea.appendText("Server: "+msg + "\n");
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }


        }).start();*/
    }

    @FXML
    void btnDisconnectOnAction(ActionEvent event) {

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

}
