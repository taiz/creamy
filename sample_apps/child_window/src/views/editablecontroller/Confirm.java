package views.editablecontroller;

import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * 確認ダイアログ.
 * @author ahayama
 * @param <T> 
 */
public class Confirm<T> extends AvailableActivity {
    
    private EditableItem owner;
    
    @FXML private void cancelHandler(ActionEvent event) {
        ((Stage)this.scene.getScene().getWindow()).close();
    }
    
    @FXML private void deleteHandler(ActionEvent event) {
        ((EditableItem)ownerActivity).deleteAction();
        ((Stage)this.scene.getScene().getWindow()).close();
    }
    }
