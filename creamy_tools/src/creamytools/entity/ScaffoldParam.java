/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.entity;

import creamytools.app.IConstants;
import java.util.List;

/**
 * Scaffoldツールが受け取るパラメータのメタデータクラス
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class ScaffoldParam implements IConstants {
    
    /**
     * 生成するFXML/Acitivtyのパッケージ名
     */
    private String packageName;
    
    /**
     * get/post先のベースとなるパス。Controller名と同一
     */
    private String basePath;
    
    /**
     * 生成するModelクラスの名称(SimpleName:パッケージ名は含まない)
     */
    private String modelName;
    
    /**
     * FXML内で使用するModelインスタンスの変数名。Modelクラスの先頭を小文字にしたもの
     */
    private String modelVariable;
    
    /**
     * 生成するModelのフィールド変数(カラム)をリスト化したもの
     */
    private List<Field> fields;
    
    /**
     * エントリーポイントのクラス名
     * ※当変数は New コマンドで使用
     */
    private String entryPackageName;
            
     /**
     * エントリーポイントのパッケージ名
     * ※当変数は New コマンドで使用
     */
    private String entryClassName;

    /**
     * get/post先のベースとなるパスの取得
     * @return get/post先のベースとなるパス
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * get/post先のベースとなるパスの設定
     * @param basePath  get/post先のベースとなるパス
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * 生成するModelのフィールド変数(カラム)のリストの取得
     * @return 生成するModelのフィールド変数(カラム)のリスト
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * 生成するModelのフィールド変数(カラム)のリストの設定
     * @param fields  生成するModelのフィールド変数(カラム)のリスト
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * 生成するModelクラスの名称の取得
     * @return 生成するModelクラスの名称
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 生成するModelクラスの名称の設定
     * @param modelName  生成するModelクラスの名称
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * FXML内で使用するModelインスタンスの変数名の取得
     * @return FXML内で使用するModelインスタンスの変数名
     */
    public String getModelVariable() {
        return modelVariable;
    }

    /**
     * FXML内で使用するModelインスタンスの変数名の設定
     * @param modelVariable  FXML内で使用するModelインスタンスの変数名
     */
    public void setModelVariable(String modelVariable) {
        this.modelVariable = modelVariable;
    }

    /**
     * 生成するFXML/Acitivtyのパッケージ名の取得
     * @return 生成するFXML/Acitivtyのパッケージ名
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 生成するFXML/Acitivtyのパッケージ名の設定
     * @param packageName  生成するFXML/Acitivtyのパッケージ名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * エントリーポイントのパッケージ名の取得
     * @return 
     */
    public String getEntryPackageName(){
        return entryPackageName;
    }
    
    /**
     * エントリーポイントのパッケージ名の設定
     */
    public void setEntryPackageName(String entryPackageName){
        this.entryPackageName   = entryPackageName;
    }


    /**
     * エントリーポイントのクラス名の取得
     * @return 
     */
    public String getEntryClassName(){
        return entryClassName;
    }
    
    /**
     * エントリーポイントのクラス名の設定
     */
    public void setEntryClassName(String entryClassName){
        this.entryClassName   = entryClassName;
    }
}
