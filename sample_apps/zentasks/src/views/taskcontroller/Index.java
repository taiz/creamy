package views.taskcontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Index extends AvailableActivity {
    @FXML Integer projectId;
    @FXML VBox foldersPane;
    
    @FXML private void addNewFolder(ActionEvent event) {
        requestActivity("/TaskController/addFolder/" + projectId)
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    ((Folder)activity).beEditable();
                    foldersPane.getChildren().add(activity.getScene());
                }
            }).execute();
    }
    
    void folderRemoved(Folder folder) {
        foldersPane.getChildren().remove(folder.getScene());
        getChildren(Folder.class).remove(folder);
    }
}
