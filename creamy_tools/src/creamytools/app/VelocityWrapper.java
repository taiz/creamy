/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.app;

import java.io.StringWriter;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * Verocity ラッパークラス
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class VelocityWrapper{

    /**
     * Verocityにおけるテンプレートファイルを司るクラス
     */
    private Template template 		= null;
    
    /**
     * Velocity の context
     */
    private VelocityContext context	= new VelocityContext();
    
    /**
     * Velocity の実行エンジン
     */
    private VelocityEngine engine	= new VelocityEngine();

    
    public VelocityWrapper(){
        Properties prop = new Properties();
        prop.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        prop.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.init(prop);
    }
    
    /**
     * テンプレートファイル設定
     * @param templateFile 　テンプレートファイル
     */
	public void setTemplate(Template template) {
        this.template    = template;
    }
    
    /**
     * テンプレートファイル取得
     * @param path テンプレートファイルのパス（jarファイル内）
     * @return  テンプレートファイル
     */
    public Template getTemplate(String path){
        return engine.getTemplate(path);
    }

	/**
	 * テンプレートで展開するコンテンツの context への配置
	 * @param key キー項目名
	 * @param value 値
	 */
	public void put(String key, Object value) {
		context.put(key, value);
	}

	/**
     * テンプレートへ展開
	 * @return テンプレートへ展開済みのコンテンツの文字列
	 */
	public String merge() {
		StringWriter sw		= new StringWriter();
		template.merge(context, sw);
		return sw.toString();
	}
}
