package views.projectcontroller;

import creamy.activity.AvailableActivity;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class TeamItem extends AvailableActivity {
    @FXML private String email;
    
    public String getEmail() { return email; }
    
    @FXML private void addMember(MouseEvent event) {
        ((TeamMenu)getParent()).memberAdded(this);
    }
 
    @FXML private void removeMember(MouseEvent event) {
        ((TeamMenu)getParent()).memberRemoved(this);
    }
}
