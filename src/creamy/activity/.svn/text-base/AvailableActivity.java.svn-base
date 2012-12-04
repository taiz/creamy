/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.activity;

import creamy.scene.control.*;
import creamy.scene.layout.CFGridForm;
import creamy.scene.layout.CFHForm;
import java.lang.reflect.Field;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.util.Builder;

/**
 *
 * @author ATakahashi
 */
public class AvailableActivity extends Activity implements Available {
    
    private ActivityHelper helper = new ActivityHelper();
    
    public CFGridForm gridForm(String path) {
        return helper.gridForm(path);
    }
    
    public CFHForm hform(String path) {
        return helper.hform(path);
    }
    
    public LabelBuilder<? extends LabelBuilder> label(String text) {
        return helper.label(text);
    }
    
    public CFTextFieldBuilder<? extends CFTextFieldBuilder> text(String name) {
        return helper.text(name);
    }
    
    public CFChoiceBoxBuilder<?, ? extends CFChoiceBoxBuilder> choice(String name) {
        return helper.choice(name);
    }
    
    public CFButtonBuilder<? extends CFButtonBuilder> button(String name) {
        return helper.button(name);
    }
    
    public CFHyperlinkBuilder<? extends CFHyperlinkBuilder> hyperlink(String path) {
        return helper.hyperlink(path);
    }
    
    public CFLinkButtonBuilder<? extends CFLinkButtonBuilder> linkbutton(String path) {
        return helper.linkbutton(path);
    }
    
    public CFSubmitButtonBuilder<? extends CFSubmitButtonBuilder> submit(String text) {
        return helper.submit(text);
    }
    
    public HBoxBuilder<?> hbox(Builder... builders) {
        return helper.hbox(builders);
    }

    public HBoxBuilder<?> hbox(Node... children) {
        return helper.hbox(children);
    }

    @Override
    public void initialize() {}
}
