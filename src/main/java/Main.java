import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class serves as the entry point for the Natsbot application.
 * It initializes the application and loads the main user interface from FXML.
 * Then, it injects the Natsbot instance into the controller.
 */
public class Main extends Application {

    private Natsbot natsbot = new Natsbot("data/natsbot.txt");

    /**
     * Starts the JavaFX application by setting up the main stage and scene.
     * Loads the FXML layout for the main window and injects the Natsbot instance into the controller.
     *
     * @param stage The primary stage for this application, onto which the application scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setNatsbot(natsbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

