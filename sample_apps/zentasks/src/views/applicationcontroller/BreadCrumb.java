package views.applicationcontroller;

import creamy.activity.AvailableActivity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class BreadCrumb extends AvailableActivity {
    @FXML private Button teamBtn;
    private Integer projectId;
    
    @FXML private void showTeamMenu(MouseEvent evnet) {
        Popup teamMenu = createPopup("/ProjectController/teamMenu/" + projectId, true);
        teamMenu.show(teamBtn, getScene().getScene().getWidth() - teamBtn.getPrefWidth() - 200, 130);
        //teamMenu.show(getScene().getScene().getWindow());
    }
}
