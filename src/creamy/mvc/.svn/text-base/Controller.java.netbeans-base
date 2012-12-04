/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamy.mvc;

import java.util.Map;

/**
 *
 * @author miyabetaiji
 */
public abstract class Controller extends Results {
    
    /**
     * continualData
     * @see WindowData
     */
    private Map<String,Object> continualData;
    private Map<String, Object> requestParams;
    //private String redirectPath;

    /**
     * ContinualDataを取得する
     * @return continualData
     */
    protected Map<String,Object> getContinualData() {
        return this.continualData;
    }

    /**
     * ContinualDataを設定する
     * @param continualData
     * @See Router
     */
    protected void setContinualData(Map<String,Object> continualData) {
        this.continualData = continualData;
    }

    protected void setRequestParams(Map<String, Object> params) {
        requestParams = params;
    }
    
    /*
    protected boolean isRedirect() {
        //TODO:パスとして正しいかチェック
        return redirectPath != null && redirectPath.length() > 0;
    }
    
    protected String getRedirectPath () {
        return redirectPath;
    }
    
    protected void redirect(String path) {
        redirectPath = path;
    }
    */
    
    protected BindingResult bind(Object obj) {
        return MvcUtil.bindMapModel(requestParams, obj);
    }
    
    protected Object params(String key) {
        return requestParams.get(key);
    }
}
