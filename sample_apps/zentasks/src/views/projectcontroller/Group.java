package views.projectcontroller;

import creamy.activity.Activity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import views.applicationcontroller.Main;

public class Group extends TreeItemActivity {
    @FXML private ImageView settingsButton;
    
    @FXML private void strongDeleteButton(MouseEvent evnet) {
        replaceStyleClass(settingsButton, "options-button-on");
    }
    
    @FXML private void weekDeleteButton(MouseEvent evnet) {
        replaceStyleClass(settingsButton, "options-button-off");
    }
    
    @Override
    protected String getRenamePath() {
        return "/ProjectController/renameGroup/" + titleLabel.getText() + "/";
    }
            
    void deleteProject(TreeItem<Pane> project) {
        getTreeItem().getChildren().remove(project);
    }

    @FXML private void showSettingMenu(MouseEvent t) {
        ContextMenu menu = new ContextMenu();
        menu.getItems().addAll(
            MenuItemBuilder.create()
                .text("New Project")
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        addNewProject();
                    }
                })
                .build(),
            MenuItemBuilder.create()
                .text("Remove Group")
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        removeGroup();
                    }
                })
                .build()
        );
        menu.show(settingsButton, t.getScreenX(), t.getScreenY());
    }

    private void addNewProject() {
        requestActivity("/ProjectController/add/" + titleLabel.getText())
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    getTreeItem().getChildren().add((((Item)activity).getTreeItem()));
                    ((Item)activity).beEditable();
                }
            })
            .execute();
    }
    
    private void removeGroup() {
        requestData("/ProjectController/deleteGroup/" + titleLabel.getText())
            .onSuccess(new CallBack<Object>() {
                @Override
                public void call(Object data, Status status) {
                    ((Main)getParent()).deleteGroup(getTreeItem());
                }
            })
            .execute();   
    }
}
