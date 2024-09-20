import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * A custom control representing a dialog box consisting of a Label for text and an ImageView for picture.
 * The dialog box can be styled differently based on the command type.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * Loads the FXML layout for the dialog box and sets the provided text and image.
     *
     * @param text The text to display in the dialog.
     * @param img  The image to be displayed in the dialog.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        dialog.setMaxWidth(Double.MAX_VALUE);
        dialog.setWrapText(true);
        HBox.setHgrow(dialog, Priority.ALWAYS);

        this.setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * This is used for Natsbot's replies to the user.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns a DialogBox configured as a user dialog with the specified text and image.
     *
     * @param text The text to display in the user's dialog.
     * @param img  The image to be displayed in the user's dialog.
     * @return A DialogBox instance configured as a user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Changes the style of the dialog box based on the command type.
     *
     * @param commandType The type of command to determine the style of the dialog box.
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }

    /**
     * Returns a DialogBox configured as a Natsbot dialog with the specified text, image, and command type.
     * The dialog box is flipped so that the Natsbot's image is on the left, and the text is on the right.
     * The style of the dialog is adjusted based on the command type.
     *
     * @param text        The text to display in Natsbot's dialog.
     * @param img         The image to display in Natsbot's dialog.
     * @param commandType The type of command executed, used to adjust the dialog style.
     * @return A DialogBox instance configured as a Natsbot dialog.
     */
    public static DialogBox getNatsbotDialog(String text, Image img, String commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}

