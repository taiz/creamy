package creamy.activity;

import creamy.annotation.Template;
import creamy.mvc.Controller;
import creamy.scene.layout.ChildPane;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * ActivityのFactoryクラス.
 * Singletonクラス.
 * 公開メソッドrenderによりActivityインスタンスを生成する.
 *
 * @author Taiji Miyabe
 */
public class ActivityFactory {    
    private static ActivityFactory factory = new ActivityFactory();
    private FXMLGenerator generator;
    
    private ActivityFactory() {
        generator = new FXMLGenerator();
    }
    
    /**
     * ActivityFactoryのインスタンスを取得する
     * @return 
     */
    public static ActivityFactory getInstance() {
        return factory;
    }
    
    /**
     * 
     * @param activityClass
     * @param controller
     * @return 
     */
    public synchronized Activity createActivity(Class<? extends Activity> activityClass,
            Controller controller) {
        Map<Field, Object> fieldsMap = collectParams(controller);

        Activity mainActivity = null;
        List<Node> childPanes = null;
        
        // check @Template annotation
        if (activityClass.isAnnotationPresent(Template.class)) {
            Class<? extends Activity> template = activityClass.getAnnotation(Template.class).value();
            // build main activity
            mainActivity = buildActivity(template, null, fieldsMap);
            childPanes = mainActivity.getNodesByTag(ChildPane.class);
        }
        
        // build entity activity
        Activity entityActivity = buildActivity(activityClass, mainActivity, fieldsMap);
        
        if (childPanes != null && childPanes.isEmpty() == false) {
            ChildPane childPane = (ChildPane) childPanes.get(0);       
            if (childPane == null) { System.err.println("childPane is null"); }
            childPane.getChildren().add(entityActivity.getScene());
            mainActivity.addChildActivity(entityActivity);
        }
        else {
            return entityActivity;
        }
        return mainActivity;
    }
    
    /**
     * Acitivityを生成する. Acitivity#renderChildrenから呼出される
     * @param activityClass 生成するAcitivityクラス
     * @param parent 親Activity
     * @param takeoverParams 親FXMLで定義されたパラメータ
     * @return Activityインスタンス
     */
    synchronized Activity createActivity(Class<? extends Activity> activityClass,
            Activity parent, Map<Field,Object> fieldParams, Map<String,Object> takeoverParams) {
        Map<Field, Object> fieldsMap = duplicateMap(fieldParams);
        fieldsMap.putAll(collectParams(parent));
        if (takeoverParams == null)
            return buildActivity(activityClass, parent, fieldsMap);
        else
            return buildActivity(activityClass, parent, fieldsMap, takeoverParams);
    }
    
    private Activity buildActivity(Class<? extends Activity> activityClass,
            Activity parent, Map<Field,Object> fieldParams) {
        return buildActivity(activityClass, parent, fieldParams, new HashMap<String,Object>());
    }
    
    private Activity buildActivity(Class<? extends Activity> activityClass,
            Activity parent, Map<Field,Object> fieldParams, Map<String,Object> takeoverParams) {
        // VTL入りFXMLをレンダリング
        URL fxmlURL = getFXMLLocation(activityClass);
        if (fxmlURL == null)
            throw new FXMLLoadException("FXML not found (" + activityClass.getName() + ")");
        RenderParameter renderParam = new RenderParameter();
        String fxmlContent = generator.generate(fxmlURL, fieldParams, takeoverParams, renderParam);
        // Activityを生成(FXMLをロード)
        Activity activity = load(fxmlURL, fxmlContent);
        // Activityを初期化
        activity.initialize(parent, fieldParams, takeoverParams);
        activity.createChildren(fieldParams, renderParam);
        return activity;
    }

    private Activity load(URL fxmlURL, String fxmlContent) {
        InputStream fxmlStream = new ByteArrayInputStream(fxmlContent.getBytes());  
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        try {
            Pane root = (Pane)loader.load(fxmlStream);
            Activity activity = (Activity)loader.getController();
            activity.setScene(root);
            return activity;
        } catch (IOException | ClassCastException ex) {
            System.err.println(fxmlContent);
            throw new FXMLLoadException("Failed to load " + fxmlURL.getFile(), ex);
        }
    }
    
    private static Map<Field, Object> collectParams(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<Field, Object> params = new HashMap<Field, Object>();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                params.put(f, f.get(obj));
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return params;
    }

    private static URL getFXMLLocation(Class<? extends Activity> activityClass) {
        String path = activityClass.getName().replace('.','/') +  ".vm.fxml";
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
    
    private static <K,V> Map<K,V> duplicateMap(Map<K,V> source) {
        Map<K,V> dup = new HashMap<K,V>();
        for (Entry<K,V> e : source.entrySet()) dup.put(e.getKey(), e.getValue());
        return dup;
    }
}
