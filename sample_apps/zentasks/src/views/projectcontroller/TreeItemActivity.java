package views.projectcontroller;

import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class TreeItemActivity extends AvailableActivity{
    private TreeItem<Pane> treeItem = new TreeItem<Pane>();
    
    @Override
    public void initialize() { treeItem.setValue(getScene()); }

    public TreeItem<Pane> getTreeItem() { return treeItem; }

    public void setTreeItem(TreeItem<Pane> treeItem) { this.treeItem = treeItem; }

    @FXML protected StackPane titlePane;
    @FXML protected Labeled titleLabel;

    @FXML protected void rename(MouseEvent event) {
        if(!event.getButton().equals(MouseButton.PRIMARY)) return;
        if(event.getClickCount() != 2) return;
        beEditable();
    }
    
    protected abstract String getRenamePath();
    
    public void beEditable() {
        final TextField textField = new TextField(titleLabel.getText());
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                requestData(getRenamePath() + textField.getText())
                    .onSuccess(new CallBack<Object>() {
                        @Override
                        public void call(Object data, Status status) {
                            titleLabel.setText(textField.getText());
                            titlePane.getChildren().remove(textField);
                        }
                    }).execute();
            }
        });
        titlePane.getChildren().add(textField);
    }
}
