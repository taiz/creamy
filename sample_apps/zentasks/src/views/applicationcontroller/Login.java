package views.applicationcontroller;

import creamy.activity.AvailableActivity;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Login extends AvailableActivity {

    @Override
    public void initialize() {
        if (getApplicationData().get("message") != null)
            showInfoMessage((String)getApplicationData().get("message"));
        if (getApplicationData().get("errormessage") != null)
            showErrorMessage((String)getApplicationData().get("errormessage"));
        getApplicationData().clear();
    }

    @FXML StackPane messagePane;
    
    private void showInfoMessage(String message) {
        showMessage(LabelBuilder.create()
                    .text(message)
                    .styleClass("message-info")
                    .build());
    }

    private void showErrorMessage(String message) {
        showMessage(LabelBuilder.create()
                    .text(message)
                    .styleClass("message-error")
                    .build());
    }
    
    private void showMessage(Label label) {
        messagePane.getChildren().clear();
        messagePane.getChildren().add(label);
        VBox.setMargin(messagePane, new Insets(10,0,10,0));
    }
    
    @FXML private void reload(MouseEvent event) {
        moveTo("/ApplicationController/login");
    }
}
