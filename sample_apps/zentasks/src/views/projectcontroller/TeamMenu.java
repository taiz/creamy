package views.projectcontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class TeamMenu extends AvailableActivity {
    private Integer projectId;
    @FXML private VBox memberList;
    @FXML private VBox remainList;
    
    void memberAdded(final TeamItem item) {
        System.out.println("email--------- " + item.getEmail());
        requestActivity("/ProjectController/addUser/" + projectId)
            .params(new HashMap<String,Object>() {{ put("email", item.getEmail()); }} )
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    remainList.getChildren().remove(item.getScene());
                    memberList.getChildren().add(activity.getScene());
                }
            })
            .execute();
    }
    
    void memberRemoved(final TeamItem item) {
        requestActivity("/ProjectController/removeUser/" + projectId)
            .params(new HashMap<String,Object>() {{ put("email", item.getEmail()); }} )
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    memberList.getChildren().remove(item.getScene());
                    remainList.getChildren().add(activity.getScene());
                }
            })
            .execute();
    }
}
