package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import lk.ijse.controller.*;

public class ServerFormController {

    @FXML
    private Button sendBtn;

    @FXML
    private AnchorPane serverRoot;

    @FXML
    private TextArea serverTextArea;

    @FXML
    private TextField serverTextField;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String msg= "";


    public void initialize() throws IOException {
       new Thread(()-> {
           try {
               serverSocket = new ServerSocket(3000);
               serverTextArea.appendText("\n");
               socket = serverSocket.accept();
               dataOutputStream = new DataOutputStream(socket.getOutputStream());
               dataInputStream = new DataInputStream(socket.getInputStream());

               while (!socket.isClosed()) {
                       msg = dataInputStream.readUTF();
                       serverTextArea.appendText("client: "+ msg + "\n");

               }

           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }).start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {

        String text = serverTextField.getText();
        serverTextArea.appendText("you: "+ text + "\n");
        dataOutputStream.writeUTF(text);
        dataOutputStream.flush();
        serverTextField.clear();



    }

}
