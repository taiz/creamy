package creamy.activity;

import creamy.activity.requestor.Requestor;
import creamy.browser.Broker;
import creamy.browser.Browser;
import creamy.global.ApplicationData;
import creamy.scene.layout.ChildPane;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Activityの基底クラス.
 * 生成時に自身を初期化する.また基本的なメソッドを提供する
 *
 * @author Taiji Miyabe
 */
public abstract class Activity extends Requestor implements Initializeble {
    // title
    private static final String DELIMITER = ":";
    @FXML protected String title;
    
    /**
     * FXMLに記述されたタイトルを取得する
     * Child Activityが存在する場合は、Child Activityのタイトルを取得し、
     * 「:」区切りで結合して返す
     * @return title
     */
    public String getTitle() {
        String childTitle = null;
        for (List<Activity> children : childActivities.values()) {
            Activity child = children.get(0);
            childTitle = child.getTitle();
        }
        if (this.title == null) {
            if (childTitle == null) return "";
            else return childTitle;
        } else {
            if (childTitle == null) return title;
            else return title + DELIMITER + childTitle;
        }
    }
    
    /**
     * タイトルを設定する
     * @return title
     */
    public void setTitle(String title) { this.title = title; }
    
    // root node
    @FXML protected Pane scene;

    protected Activity parentActivity;
    protected ObservableMap<Class<? extends Activity>, List<Activity>>
            childActivities = FXCollections.observableHashMap();    
    
    public Pane getScene() { return scene; }
    
    public void setScene(Pane scene) { this.scene = scene; }
    
    protected void initialize(Activity parent, Map<Field,Object> fieldParams,
            Map<String,Object> takeoverParams) {
        setParent(parent);
        setFieldsValue(fieldParams);
        setTakeoverValues(takeoverParams);
        applyStyle();
        initialize();
    };

    public void setParent(Activity parent) {this.parentActivity = parent;}

    public Activity getParent() {return parentActivity;}
    
    private void setFieldsValue(Map<Field, Object> params) {
        if (params.isEmpty()) return;
        //TODO:スーパクラスのFieldコピー
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field source : params.keySet()) {
            for (Field destination : fields) {
                if (source.getName().equals(destination.getName()) &&
                    //params.get(name).getClass().isAssignableFrom(f.getType())) {
                    destination.getType().isAssignableFrom(source.getType()) ) {
                    try {
                        destination.setAccessible(true);
                        destination.set(this, params.get(source));
                        break;
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private void setTakeoverValues(Map<String,Object> params) {
        if (params.isEmpty()) return;
        //TODO:スーパクラスのFieldコピー
        Field[] fields = this.getClass().getDeclaredFields();
        for (Entry<String,Object> source : params.entrySet()) {
            for (Field destination : fields) {
                if (source.getKey().equals(destination.getName()) &&
                    destination.getType().isInstance(source.getValue())) {
                    try {
                        destination.setAccessible(true);
                        destination.set(this, source.getValue());
                        break;
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    synchronized void createChildren(Map<Field,Object> fieldParams, RenderParameter renderParam) {
        List<Node> childNodes = getNodesByTag(ChildPane.class);
        for (Node childNode : childNodes) {
            try {
                ChildPane childPane = (ChildPane)childNode;
                if (childPane.getChild() == null) { continue; }
                // 子Activityを生成する
                String className = getPackageName() + "." + childPane.getChild();
                String fxId = childPane.getId();
                Class<Activity> clazz = (Class<Activity>)Class.forName(className);
                Activity childActivity = ActivityFactory.getInstance().
                        createActivity(clazz, this, fieldParams, renderParam.getParams(fxId));
                // 子のrootノードをChildPaneに追加する
                childPane.getChildren().add(childActivity.getScene());
                childActivity.setScene(childPane);
                addChildActivity(childActivity);
            } catch (ClassNotFoundException ex) {
                throw new FXMLLoadException(ex);
            }
        }
    }

    protected List<Node> getNodesByTag(Class<? extends Node> clazz) {
        List<Node> nodes = new ArrayList<Node>();

        if (scene == null || !(scene instanceof Pane)) return nodes;

        Queue<Pane> queue = new LinkedList<Pane>();
        queue.add((Pane)scene);

        while (!queue.isEmpty()) {
            Pane pane = queue.poll();

            for (Node node : pane.getChildren()) {
                if (clazz.isAssignableFrom(node.getClass()))
                    nodes.add(node);
                if (node instanceof Pane)
                    queue.add((Pane)node);
            }
        }
        return nodes;
    }

    private String getPackageName() {
        String className = getClass().getName();
        int idx = className.lastIndexOf('.');

        return className.substring(0, idx);
    }
    
    protected void addChildActivity(Activity childActivity) {
        Class<? extends Activity> clazz = childActivity.getClass();
        
        if (childActivities.get(clazz) == null) {
            List children = new ArrayList<Activity>();
            children.add(childActivity);
            childActivities.put(clazz, children);   
        } else {
            List children = childActivities.get(clazz);
            children.add(childActivity);
        }        
    }
    
    protected List<Activity> getChildActivities(Class<? extends Activity> clazz) {
        return childActivities.get(clazz);
    }
    
    private void applyStyle() {
        URL url = getClass().getResource(getCssName());
        if (url == null) return;
        
        scene.getStylesheets().add(url.toExternalForm());
    }
    
    private String getCssName() {
        String className = getClass().getName();
        int idx = className.lastIndexOf('.') + 1;

        return className.substring(idx, className.length()) + ".css";
    }
    
    /**
     * 動的リクエストで使用するBrokerを生成する
     * BrokerにはこのActivityが表示されているBrowserPanelのインスタンスをセットする
     * 
     * @return broker
     */
    @Override
    protected Broker createBroker() {
        //TODO:Browserのnewを廃止
        if (getBrowser() != null) return new Broker(getBrowser().getBrowserPanel());
        Browser browser = new Browser();
        return new Broker(browser.getBrowserPanel());
    }
    
    private Browser getBrowser() {
        try {
            return (Browser)scene.getScene();
        } catch (ClassCastException ex) {
            throw new RuntimeException("Browser not found.", ex);
        }
    }
    
    /**
     * ApplicationData(グローバルオブジェクト)を取得する
     * @see ApplicationData
     */
    protected Map<String,Object> getApplicationData() {
        return ApplicationData.getInstance().getData();
    }

    private void showParams(Map<Field, Object> params) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("----- params detail -----\n");
        for (Field key : params.keySet()) {
            builder.append(key.getName() + "\t"  + params.get(key) + "\n");
        }
        Logger.getLogger(getClass().getName()).log(Level.INFO, builder.toString());
    }
}
