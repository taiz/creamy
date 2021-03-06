package views.editablecontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.mvc.Status;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author miyabetaiji
 */
public class EditableItem extends AvailableActivity {
    @FXML private Integer computerId;
    @FXML private TextField computerName;
    @FXML private TextField introduced;
    @FXML private TextField discontinued;
    @FXML private Label companyName;
    
    private EditableList parent;
    @Override
    public void initialize() {
        parent = (EditableList)getParent();
        messageAnimation.setNode(message);
    }
    
    @FXML private Button showBtn;
    
    @FXML private void showCompany(ActionEvent event) {
        String path = "/EditableController/testCompanyName/" + computerId;
        requestData(path)
                .onSuccess(new CallBack<Object>() {
                    @Override
                    public void call(Object data, Status status) {
                        companyName.setText(data.toString());
                    }
                })
                .execute();
    }
    
    @FXML private Button updateBtn;
    
    // 課題：Formから一度にバインドしたい
    @FXML private void updateComputer(ActionEvent event) {
        Map<String,Object> params = new HashMap<String,Object>() {{
           put("id", computerId);
           put("name", computerName.getText());
           put("introduced", introduced.getText());
           put("discontinued", discontinued.getText());
        }};
        requestData("/EditableController/updateComputer")
                .params(params)
                .onSuccess(new CallBack<Object>() {
                    @Override
                    public void call(Object data, Status status) {
                        message.setText("updated");
                        messageAnimation.play();
                    }      
                })
                .execute();
    }
    
    @FXML private Button deleteBtn;
    
    // 削除する確認ダイアログ
    protected final Stage stage = new Stage();
    
    @FXML private void deleteComputer(ActionEvent event) {
        // 確認ウィンドウを表示して、OKなら続行、Cancelなら中断
        Activity window = createWindow("/EditableController/confirm", Modality.WINDOW_MODAL);
    }
    
    // 削除するボタンアクション
    protected void deleteAction() {
        String path = "/EditableController/deleteComputer/" + computerId;
        requestData(path)
                .onSuccess(new CallBack<Object>() {
                    @Override
                    public void call(Object data, Status status) {
                        messageAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                //((VBox)scene.getParent()).getChildren().remove(scene);
                                parent.removed(EditableItem.this);
                            }
                        });
                        message.setText("deleted");
                        messageAnimation.play();
                    }
                })
                .execute();
        
        stage.close();
    }
    
    @FXML private Label message;
    
    private SequentialTransition messageAnimation = SequentialTransitionBuilder.create()
            //.node(message)
            .children(
                FadeTransitionBuilder.create()
                    .fromValue(0).toValue(1.0)
                    .duration(Duration.seconds(1.0))
                    .build(),
                FadeTransitionBuilder.create()
                    .fromValue(1.0).toValue(0.0)
                    .duration(Duration.seconds(1.0))
                    .build()
            ).build();
}
