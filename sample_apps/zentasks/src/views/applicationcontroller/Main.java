package views.applicationcontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import views.projectcontroller.Group;
import views.projectcontroller.Item;

public class Main extends AvailableActivity {    
    @Override
    public void initialize() {
        buildProjectTree();
    }
    
    @FXML private TreeView<Pane> projectsTree;
    private HashMap<String,List<Integer>> projectGroups;
    
    private void buildProjectTree() {
        TreeItem<Pane> root = new TreeItem<Pane>();
        for (Entry<String,List<Integer>> entry : projectGroups.entrySet()) {
            Activity group = getGroup(entry.getKey());
            if (group == null) continue;
            root.getChildren().add(((Group)group).getTreeItem());
            for (Integer projectId : entry.getValue()) {
                Activity project = getProject(projectId);
                if (project == null) continue;
                ((Item)project).setParent(group);
                ((Group)group).getTreeItem().getChildren().add(((Item)project).getTreeItem());
            }
        }
        projectsTree.setRoot(root);
    }
    
    private Activity getGroup(String group) { 
        return getNode("/ProjectController/group/" + group);
    }
    
    private Activity getProject(Integer projectId) {
        return getNode("/ProjectController/item/" + projectId);
    }

     private Activity getNode(String path) {
        final SimpleObjectProperty<Activity> act = new SimpleObjectProperty<Activity>();
         requestActivity(path)
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    act.set(activity);
                }
            })
            .execute();
        return act.get();
    }   
    
    @FXML private void addNewProjectGroup(ActionEvent event) {
        Activity group = getNode("/ProjectController/addGroup");
        if (group == null) return;
        ((Group)group).beEditable();
        projectsTree.getRoot().getChildren().add(((Group)group).getTreeItem());
    }
        
    @FXML private void reload(MouseEvent event) {
        moveTo("/ProjectController/index");
    }
    
    @FXML private void logout(ActionEvent event) {
        moveTo("/ApplicationController/logout");
    }
    
    @FXML Pane body;
    
    public void showProject(String path) {
        requestActivity(path).onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    body.getChildren().clear();
                    body.getChildren().add(activity.getScene());                    
                }
            }).execute();
    }
    
    public void deleteGroup(TreeItem<Pane> group) {
        projectsTree.getRoot().getChildren().remove(group);
    }
}
