package views.projectcontroller;

import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import views.applicationcontroller.Main;

public class Item extends TreeItemActivity {
    @FXML ImageView deleteButton;
    
    @FXML private void showDeleteButton(MouseEvent evnet) {
        replaceStyleClass(deleteButton, "delete-button-on");
    }
    
    @FXML private void hideDeleteButton(MouseEvent evnet) {
        replaceStyleClass(deleteButton, "delete-button-off-opa");
    }
    
    @Override
    protected String getRenamePath() {
        return "/ProjectController/rename/" + projectId + "/";
    }
    
    @FXML Integer projectId;

    @FXML private void showProject(ActionEvent evnet) {
        ((Main)getParent().getParent()).showProject("/TaskController/index/" + projectId);
    }
    
    @FXML private void deleteProject(MouseEvent event) {
        requestData("/ProjectController/delete/" + projectId)
            .onSuccess(new CallBack<Object>() {
                @Override
                public void call(Object data, Status status) {
                    ((Group)getParent()).deleteProject(getTreeItem());
                }
            }).execute();
    }
}
