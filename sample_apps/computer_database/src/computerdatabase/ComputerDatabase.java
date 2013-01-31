/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computerdatabase;

import creamy.browser.TabBrowser;
import creamy.browser.control.*;
import creamy.entrypoint.CreamyApp;
import javafx.stage.Stage;

/**
 *
 * @author miyabetaiji
 */
public class ComputerDatabase extends CreamyApp {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        TabBrowser browser = new TabBrowser("/Application/index");
        browser.setMenuBar(new DefaultBrowserMenuBar());
        browser.setHeader(new DefaultHeader());
        primaryStage.setScene(browser);
        primaryStage.show();
    }
}
