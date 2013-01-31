/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.entity;

import creamytools.app.IConstants;

/**
 * カラム指定のパラメータクラス
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class Field implements IConstants {
    
    /**
     * カラム名
     */
    private String name;
    
    /**
     * 型
     */
    private Class<?> type;

    /**
     * カラム名の取得
     * @return カラム名
     */
    public String getName() {
        return name;
    }

    /**
     * カラム名の設定
     * @param name  カラム型
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * カラム名（name）の先頭一文字を大文字に変換して返す
     * @return 
     */
    public String getUpName(){
        return name.substring(FIRST_CHARA, SECOND_CHARA).toUpperCase() + name.substring(SECOND_CHARA);
    }

    /**
     * カラム型の取得
     * @return カラム型
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * カラム型の設定
     * @param type 
     */
    public void setType(Class<?> type) {
        this.type = type;
    }
    
}
