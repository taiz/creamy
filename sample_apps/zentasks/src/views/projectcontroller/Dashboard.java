package views.projectcontroller;

import creamy.activity.AvailableActivity;
import creamy.annotation.Template;
import creamy.scene.control.CFHyperlink;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import views.applicationcontroller.Main;

@Template(Main.class)
public class Dashboard extends AvailableActivity {
    @Override
    public void initialize() {
        for (final CFHyperlink link : lookupAll(CFHyperlink.class)) {
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    ((Main)getParent()).showProject(link.getPath());
                    cancelRequest();
                }
            });
        }
    }
}
