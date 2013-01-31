package views.wizardcontroller;

import creamy.activity.AvailableActivity;
import creamy.annotation.Template;
import views.wizardcontroller.WizardBase.Status;

/**
 * コンピュータデータ登録画面.
 * @author ahayama
 */
@Template(WizardBase.class)
public class Create extends AvailableActivity {
    
    /**
     * 登録確認画面の初期設定を行う.
     * 第１ステップの画面であることを親アクティビティ(WizardBase)に通知する。<br>
     */
    @Override
    public void initialize() {
        // parent activityに現在のウィンドウの状態を通知する。
        ((WizardBase)this.getParent()).setStatus(Status.FIRST_WINDOW);
    }
}
