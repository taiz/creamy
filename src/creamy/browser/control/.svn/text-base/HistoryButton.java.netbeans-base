package creamy.browser.control;

import creamy.mvc.Response;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

/**
 * BackButton, ForwardButtonの基底クラス
 * 
 * @author Taiji Miyabe (Professor Akiguchi's PBL 2012, AIIT)
 */
public abstract class HistoryButton extends Button implements ListChangeListener<Response> {
    /**
     * HistoryButtonを生成する
     */
    public HistoryButton() { 
        super();
        setDisable(true);
    }
    
    /**
     * HistoryButtonを生成する
     * @param text Text
     */
    public HistoryButton(String text) {
        super(text);
        setDisable(true);
    }
    
    /**
     * Historyの状態が変更された際にコールバックされる
     * @param change Changeオブジェクト
     */
    @Override
    public void onChanged(Change<? extends Response> change) {
        ObservableList<Response> list = (ObservableList<Response>) change.getList();
        if (list.isEmpty())
            onHistoryCleared();
        else
            onHistoryChanged(list);
    }
    
    abstract protected void onHistoryCleared();
    
    abstract protected void onHistoryChanged(ObservableList<Response> history);
}
