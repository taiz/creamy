package newcomputerwizard;

import creamy.activity.Activity;
import creamy.browser.control.DefaultBrowserMenuBar;
import creamy.browser.control.DefaultHeader;
import creamy.browser.*;
import creamy.entrypoint.CreamyApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.application.Main;
import views.wizardcontroller.WizardBase;

/**
 *
 * @author miyabetaiji
 */
public class NewComputerWizard extends CreamyApp {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("New Window - page transition");
        
        final TabBrowser browser = new TabBrowser("/EditableController/editableIndex");
        DefaultBrowserMenuBar menubar = new DefaultBrowserMenuBar();
        menubar.setUseSystemMenuBar(true);
        browser.setMenuBar(menubar);
        browser.setHeader(new DefaultHeader());
        primaryStage.setScene(browser);
        
        // メニューからダイアログを表示する
        MenuItem item = new MenuItem("コンピュータを登録...");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                // 新規ウィンドウを生成
                Activity window = Activity.createWindow(
                        browser, "/WizardController/create", Modality.APPLICATION_MODAL);
            }
        });
        // 'コンピュータを登録...' メニューを追加
        menubar.getMenus().get(0).getItems().add(0, item);
        
        primaryStage.show();   
    }
}
