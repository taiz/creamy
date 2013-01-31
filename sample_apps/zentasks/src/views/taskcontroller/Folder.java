package views.taskcontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import java.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Folder extends AvailableActivity {
    @Override
    public void initialize() {
        allCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (getChildren(Item.class) == null) return;
                for (Item item : getChildren(Item.class)) item.setDone(t1);
            }
        });
        updateRemainCount();
    }
    
    @FXML private CheckBox allCheckBox;
    
    @FXML private Label remainLabel;
    
    void updateRemainCount() {
        Integer count = 0;
        for (Item item : getListOfChild(Item.class)) if (!item.isDone()) count++;
        remainLabel.setText(count.toString());
    }
    
    @FXML private ImageView taskOperationBtn;
    
    @FXML private void showMenu(MouseEvent event) {
        ContextMenuBuilder.create()
            .items(
                MenuItemBuilder.create()
                    .text("Remove complete tasks")
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            for (Item item : getListOfChild(Item.class)) if (item.isDone()) item.removeTask(null);
                       }
                    })
                    .build(),
                MenuItemBuilder.create()
                    .text("Remove all tasks")
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            for (Item item : getListOfChild(Item.class)) item.removeTask(null);
                       }
                    })
                    .build(),
                MenuItemBuilder.create()
                    .text("Delete folder")
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            for (Item item : getListOfChild(Item.class)) item.removeTask(null);
                            ((Index)getParent()).folderRemoved(Folder.this);
                       }
                    })
                    .build()
            )
            .build()
            .show(taskOperationBtn, event.getScreenX(), event.getScreenY());
    }
    
    @FXML private void changeOperationBtnOn(MouseEvent event) {
        replaceStyleClass(taskOperationBtn, "options-button-on");
    }
    
    @FXML private void changeOperationBtnOff(MouseEvent event) {
        replaceStyleClass(taskOperationBtn, "options-button-off");
    }
    
    @FXML Integer projectId;
    @FXML String folder;
    @FXML VBox taskItemsPane;
    @FXML TextField newTaskText;
    
    @FXML private void addNewTask(ActionEvent envet) {
        requestActivity("/TaskController/add/" + projectId + "/" +folder)
            .params(new HashMap<String,Object>() {{ put("title",newTaskText.getText()); }})
            .onSuccess(new CallBack<Activity>() {
                @Override
                public void call(Activity activity, Status status) {
                    taskItemsPane.getChildren().add(((Item)activity).getScene());
                    newTaskText.clear();
                }
            }).execute();
    }
    
    void taskRemoved(Item item) {
        taskItemsPane.getChildren().remove(item.getScene());
        getChildren(Item.class).remove(item);
    }
    
    @FXML private StackPane titlePane;
    @FXML private Label titleLabel;
    
    @FXML private void renameFolder(MouseEvent event) {
        if(!event.getButton().equals(MouseButton.PRIMARY)) return;
        if(event.getClickCount() != 2) return;
        beEditable();
    }
    
    public void beEditable() {
        final TextField textField = new TextField(titleLabel.getText());
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                requestData("/TaskController/renameFolder/" + projectId + "/" + folder)
                    .params(new HashMap<String,Object>() {{ put("name", textField.getText()); }})
                    .onSuccess(new CallBack<Object>() {
                        @Override
                        public void call(Object data, Status status) {
                            titleLabel.setText(textField.getText());
                            titlePane.getChildren().remove(textField);
                            folder = textField.getText();
                        }
                    }).execute();
            }
        });
        titlePane.getChildren().add(textField);
    }
    
    private <T extends Activity> List<T> getListOfChild(Class<T> clazz) {
        List<T> children = getChildren(clazz);
        if (children == null) return new ArrayList<T>(); else return children;
    }
}
