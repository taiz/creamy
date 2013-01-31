/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.command;

import creamytools.app.IConstants;
import creamytools.app.ParamParser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 * 生成ファイル削除コマンド
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class DestroyCommnad implements ICommand, IConstants{

    /**
     * コマンド実行
     * @param args  実行時に渡されるパラメータ群
     */
    @Override
    public void exec(String[] args) {
        
        // 入力パラメータのチェック　パラメータは最低２つ（例：destroy Company）
        if(!ParamParser.checkNumberParam(args, NUMBER_PARAM_2)) {
            System.exit(ENDCODE_ERROR_PARAM);
        }

        String table    = args[IDX_TABLE].toLowerCase();
        
        // contoroller ファイルの削除（例：./controllers/CompanyController.java）
        File delDir = new File("." + SEP + "src" + SEP + "controllers");
        if(delDir.exists()){
            File[] files   = delDir.listFiles();
            for(int i = 0; i < files.length; i++) {
                if(files[i].getName().toLowerCase().indexOf(table) != STRING_NOT_FOUND){
                    files[i].delete();
                }
            }
            
            // 配下にファイルが存在しないなら controllers ディレクトリ削除
            delDir = new File("." + SEP + "src" + SEP + "controllers");
            if(delDir.listFiles().length == FILE_NUMBER_0) {
               delDir.delete();
            }
        }   
        
        // model ファイルの削除（例：./models/Company.java）
        delDir = new File("." + SEP + "src" + SEP + "models");
        if(delDir.exists()){
            File[] files   = delDir.listFiles();
            for(int i = 0; i < files.length; i++) {
                if(files[i].getName().toLowerCase().indexOf(table) != STRING_NOT_FOUND){
                    files[i].delete();
                }
            }
            
            // 配下にファイルが存在しないなら modelsディレクトリ削除
            delDir = new File("." + SEP + "src" + SEP + "models");
            if(delDir.listFiles().length == FILE_NUMBER_0) {
               delDir.delete();
            }
        }

        // view ファイルの削除（例：./views/companycontroller/Edit.java etc.）
        delDir = new File("." + SEP + "src" + SEP + "views");
        if(delDir.exists()){
            File[] packageDirs   = delDir.listFiles();
            for(int i = 0; i < packageDirs.length; i++) {
                if(packageDirs[i].isDirectory()){
                    if(packageDirs[i].getName().toLowerCase().indexOf(table + "controller") != STRING_NOT_FOUND){
                        try {
                            FileUtils.deleteDirectory(packageDirs[i]);
                        } catch (IOException ex) {
                            Logger.getLogger(DestroyCommnad.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            
            // 配下にパッケージが存在しないなら views ディレクトリ削除
            delDir = new File("." + SEP + "src" + SEP + "views");
            if(delDir.listFiles().length == FILE_NUMBER_0) {
               delDir.delete();
            }
        }
    }
}
