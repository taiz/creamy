package creamy.mvc;

import creamy.browser.Browser;
import java.util.HashMap;
import java.util.Map;

/**
 * Requestオブジェクト
 * 
 * @author Taiji Miyabe (Professor Akiguchi's PBL 2012, AIIT)
 */
public class Request {
    public static final String GET = "GET";
    public static final String POST = "POST";
    
    /**
     * Browser
     */
    private Browser browser;
    /**
     * Method(GET/POST)
     */
    private String method;
    /**
     * Path(/xxx/xxx/xxx)
     */
    private String path;
    /**
     * Form paramter
     */
    private Map<String, Object> params;

    /**
     * Requestを生成する
     */
    public Request() {}
    
    /**
     * Requestを生成する
     * @param method
     * @param path
     */
    public Request(Browser browser, String method, String path) {
        this(browser, method, path, new HashMap<String, Object>());
    }

    /**
     * Requestを生成する
     * @param method
     * @param path
     * @param params
     */
    public Request(Browser browser, String method, String path, Map params) {
        this.browser = browser;
        this.method = method;
        this.path = path;
        this.params = params;   
    }
    
    /**
     * Browserを取得する
     * @return browser
     */
    public Browser getBrowser() {
        return browser;
    }

    /**
     * Browserを取得する
     * @return browser
     */
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }
    
    /**
     * Methodを取得する
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Methodを設定する
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Parametersを取得する
     * @return params
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * Parametersを設定する
     * @return params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
 
    /**
     * Pathを取得する
     * @return path
     */
    public String getPath(){
        return path;
    }
    
    /**
     * Pathを設定する
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }
}
