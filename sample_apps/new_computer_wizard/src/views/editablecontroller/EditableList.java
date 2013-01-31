package views.editablecontroller;

import creamy.activity.AvailableActivity;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 *
 * @author miyabetaiji
 */
public class EditableList extends AvailableActivity {
    @FXML private VBox listPane;
    
    @Override
    public void initialize() {
        //createList();
    }
    
    public void removed(EditableItem item) {
        listPane.getChildren().remove(item.getScene());
    }
}
