package views.editablecontroller;

import creamy.activity.Activity;
import creamy.activity.AvailableActivity;
import creamy.activity.requestor.CallBack;
import creamy.annotation.Template;
import creamy.mvc.Status;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.application.Main;

/**
 *
 * @author miyabetaiji
 */
@Template(Main.class)
public class EditableIndex extends AvailableActivity {
    @FXML private AnchorPane listArea;
    
    @FXML private void refresh(ActionEvent event) {
        requestActivity("/EditableController/editableList/0/name/asc")
                .onSuccess(new CallBack<Activity>() {
                    @Override
                    public void call(Activity activity, Status status) {
                        listArea.getChildren().clear();
                        listArea.getChildren().add(activity.getScene());
                    }
                })
                .execute();
    }
    
    @FXML private void search(ActionEvent event) {
        
        // 検索画面を表示して、Searchなら続行、Cancelなら中断
        Activity window = createWindow("/EditableController/search", Modality.NONE);
    }

    /**
     * Searhボタンアクション.
     * 新規コンピュータ登録、検索処理の結果を元画面に反映させる処理。
     * 
     * @param data 再表示のためのパラメータMap
     */
    public void searchAction(Map data) {
        
        requestActivity("/EditableController/editableList/0/name/asc")
                .params(data)
                .onSuccess(new CallBack<Activity>() {
                    @Override
                    public void call(Activity activity, Status status) {
                        // 検索結果を反映したアクティビティに置き換える。
                        listArea.getChildren().clear();
                        listArea.getChildren().add(activity.getScene());
                    }
                })
                .execute();
    }
    
    @FXML private void newComputer(ActionEvent event) {
        
        // 新規ウィンドウを表示
        Activity window = createWindow("/ChildWindowController/commonPart", Modality.APPLICATION_MODAL);
    }
}
