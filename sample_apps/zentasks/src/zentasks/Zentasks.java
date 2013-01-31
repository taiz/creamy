package zentasks;

import creamy.browser.Browser;
import creamy.entrypoint.CreamyApp;
import javafx.stage.Stage;

public class Zentasks extends CreamyApp {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Browser browser = new Browser("/ApplicationController/login");
        primaryStage.setScene(browser);
        primaryStage.show();
    }
}
