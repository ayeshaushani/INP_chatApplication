package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane serverForm = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/server_form.fxml")));
        Pane clientForm = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/client_form.fxml")));
        Pane client2Form = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/client2.fxml")));

        GridPane gridPane = new GridPane();
        gridPane.add(serverForm, 0, 0);
        gridPane.add(clientForm, 1, 0);
        gridPane.add(client2Form, 0, 1);

        gridPane.setHgap(2);
        gridPane.setVgap(5);

        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.setTitle("Server and Client Forms");
        stage.centerOnScreen();
        stage.show();
    }
}
