/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.app;

import creamytools.entity.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * creamy ツールのパラメータパーサークラス
  * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class ParamParser implements IConstants{
    
    /**
     * パラメータ数のチェック
     * @param params入力パラメータ群
     * @return 正常／エラー
     */
    public static boolean checkNumberParam(String params[], final int numberParam) {

        // 指定の数より少なかった場合エラー
        if(params.length < numberParam) {
            System.out.println("ERROR! Number of parameters is invalid. At least " 
                                + String.valueOf(numberParam) + " parameters are needed");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * カラム指定のパラメータ群のパース
     * @param params入力パラメータ群
     * @return key: カラム名　value: カラムの型 のマップ。パース時エラーの場合 null
     */
    public static List<Field> parseColumnParams(String params[]){
        
        // カラム指定の入力パラメータをパースし、カラム名とその型のマップを作成
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<String, String>();
        List<Field> fields             = new ArrayList<Field>();
        for(int i = IDX_COLUMN1; i < params.length; i++){
            int delimiter       = params[i].indexOf(":");

            //カラム名 + 型に : が含まれない場合エラー
            if(delimiter == ERR_COLON) {
                System.out.println("ERROR! Column type of \"" + params[i] + "\" doesn't contain \":\"");
                return null;
            }

            // カラム名とカラム型の取り出し
            String columnName   = params[i].substring(0, delimiter);
            String columnType   = params[i].substring(delimiter + 1);
            
            // カラム名の重複チェック
            if(columnMap.containsKey(columnName)){
                System.out.println("ERROR! Column \"" + columnName + "\" is duplicated");
                return null;
            }
            
            // カラム型のチェック。大文字／小文字の差異は無視する
            if(!checkColumnType(columnType.toLowerCase())){
                System.out.println(
                    "ERROR! Type of " + columnName +" is \"" + columnType + 
                    "\". This type is not allowed. Only integer, short, long, byte, char, float, double, boolean, string, date are OK.");
                return null;
            }
            
            // 重複チェックのためにマップに確保
            columnMap.put(columnName, columnType);
            
            // パラメータクラスに確保
            Field field     = new Field();
            field.setName(columnName);
            field.setType(getClassType(columnType.toLowerCase()));
            fields.add(field);
        }
        
        return fields;
    }

    /**
     * カラム型のチェック
     * @param type カラム型の文字列
     * @return 正常／エラー
     */
    private static boolean checkColumnType(String type){
        
        // premitive, string, date 型ならOK
        if( (type.equals("integer"))  ||
            (type.equals("short"))    ||
            (type.equals("long"))     ||
            (type.equals("byte"))     ||
            (type.equals("char"))     ||
            (type.equals("float"))    ||
            (type.equals("double"))   ||
            (type.equals("boolean"))  ||
            (type.equals("string"))   ||
            (type.equals("date"))    
          ) {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * カラム型のClassクラスの取得
     * @param type カラム型
     * @return カラム型のClassクラス
     */
    private static Class getClassType(String type){
        if(type.equals("integer")){
            return Integer.class;
        }
        else if(type.equals("short")){
            return Short.class;
        }
        else if(type.equals("long")){
            return Long.class;
        }
        else if(type.equals("byte")){
            return Byte.class;
        }
        else if(type.equals("char")){
            return Character.class;
        }
        else if(type.equals("float")){
            return Float.class;
        }
        else if(type.equals("double")){
            return Double.class;
        }
        else if(type.equals("boolean")){
            return Boolean.class;
        }
        else if(type.equals("string")){
            return String.class;
        }
        else if(type.equals("date")){
            return Date.class;
        }
        else {
            return null;
        }
    }
}
