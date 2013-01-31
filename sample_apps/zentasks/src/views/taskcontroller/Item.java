package views.taskcontroller;

import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Item extends AvailableActivity {
    @FXML Integer taskId;
    
    @Override
    public void initialize() {
        if (doneCheckBox == null) return;
        doneCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, final Boolean t1) {
                requestData("/TaskController/update/" + taskId)
                    .params(new HashMap<String,Object>() {{ put("done", t1); }})
                    .onSuccess(new CallBack<Object>() {
                        @Override
                        public void call(Object data, Status status) { ((Folder)getParent()).updateRemainCount(); }
                    }).execute();   
            }
        });
    }
    
    @FXML private CheckBox doneCheckBox;
    
    boolean isDone() { return doneCheckBox.isSelected(); }
    
    void setDone(Boolean done) { doneCheckBox.setSelected(done); }
    
    @FXML private ImageView removeBtn;
    
    @FXML void removeTask(MouseEvent event) {
        requestData("/TaskController/delete/" + taskId)
            .onSuccess(new CallBack<Object>() {
                @Override
                public void call(Object data, Status status) {
                    ((Folder)getParent()).taskRemoved(Item.this);
                }
            }).execute();
    }
    
    @FXML private void changeRemoveBtnOn(MouseEvent event) {
        replaceStyleClass(removeBtn, "delete-button-on");
    }

    @FXML private void changeRemoveBtnOff(MouseEvent event) {
        replaceStyleClass(removeBtn, "delete-button-off");
    }
}
