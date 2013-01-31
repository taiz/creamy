package views.wizardcontroller;

import creamy.activity.AvailableActivity;
import creamy.annotation.Template;
import creamy.scene.control.CFListView;
import javafx.fxml.FXML;
import models.Company;

/**
 * メーカ選択画面.
 * @author ahayama
 */
@Template(WizardBase.class)
public class Select extends AvailableActivity {
    
    @FXML private CFListView companyList;
    
    /**
     * メーカ選択画面の初期設定を行う.
     * 第２ステップの画面であることを親アクティビティ(WizardBase)に通知する。<br>
     * Companyリストを表示する。
     */
    @Override
    public void initialize() {
        // parent activityに現在のウィンドウの状態を通知する。
        ((WizardBase)this.getParent()).setStatus(WizardBase.Status.SECOND_WINDOW);
        
        // Companyリストを表示する。
        companyList.items(Company.options().entrySet());
    }    

}
