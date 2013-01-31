/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.command;

import creamytools.app.IConstants;
import creamytools.app.ParamParser;
import creamytools.app.VelocityWrapper;
import creamytools.entity.Field;
import creamytools.entity.ScaffoldParam;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.velocity.Template;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * Scaffold コマンド
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class ScaffoldCommand implements ICommand, IConstants {

    /**
     * テーブル指定の入力パラメータ
     */
    private String table;

    /**
     * Scaffoldツールが受け取るパラメータのメタデータ
     */
    private ScaffoldParam scprm                         = new ScaffoldParam();
    
    /**
     * カラム指定の入力パラメータ群（templatePath: カラム名　value: カラムの型）
     */
    private List<Field> colums                          = new ArrayList<Field>();

    /**
     * templatePath; テンプレートファイルのパス, value: 出力ファイルのパス　のマップ
     */
    private LinkedHashMap<String, String> inOutFiles    = new LinkedHashMap<String, String>();
    
    /**
     * scaffold コマンド機能
     * @param args  実行時に渡されるパラメータ群
     */
    @Override
    public void exec(String[] args) {

        // 入力パラメータのチェック　パラメータは最低４つ（例：c:\cremy scaffoldCommand table id:int）
        if(!ParamParser.checkNumberParam(args, NUMBER_PARAM_4)) {
            System.exit(ENDCODE_ERROR_PARAM);
        }
        
        // テーブル指定入力パラメータの確保（先頭のみ大文字、残りは小文字にする）
        table   = args[IDX_TABLE].substring(FIRST_CHARA, SECOND_CHARA).toUpperCase() + 
                                    args[IDX_TABLE].substring(SECOND_CHARA).toLowerCase();
        
        // カラム指定入力パラメータのパース
        colums  = ParamParser.parseColumnParams(args);
        if(colums == null) {
            System.exit(ENDCODE_ERROR_PARAM);
        }
      
        // テンプレートに展開する値を変数に格納
        String controllerName       =  table + "Controller";
        scprm.setPackageName("views." + controllerName.toLowerCase());
        scprm.setBasePath(controllerName);
        scprm.setModelName(table);
        scprm.setModelVariable(table.toLowerCase());
        scprm.setFields(colums);


        // 出力先ディレクトリ生成
        for(int i = 0; i < ELEMENTS.length; i++) {
            createOutputDir(ELEMENTS[i]);
        }

        // テンプレートファイルからファイル出力
        prepareInOutFiles();
        Iterator<String> iteInOut   = inOutFiles.keySet().iterator();
        while(iteInOut.hasNext()) {
            // Verocity ラッパークラスを使用し、テンプレートに値を展開してファイル出力
            VelocityWrapper velocity    = new VelocityWrapper();
            velocity.put("scprm", scprm);

            String templatePath = (String)iteInOut.next();
            File fileOut        = new File(inOutFiles.get(templatePath));
            Template template;
            try{
                template   = velocity.getTemplate(templatePath);
            } catch(ResourceNotFoundException ex) {
                continue;
            }
            velocity.setTemplate(template);
            String content      = velocity.merge();
            outputFile(fileOut, content);
        }
    }

    /**
     * 出力先ディレクトリ生成
     * @param mvc  "controllers" or "models" or "views"
     */
    private void createOutputDir(String element){
        File outputDir  = new File(new File(".").getAbsolutePath() + SEP + "src" + SEP + element);
        if(!outputDir.exists()){
            outputDir.mkdir();
        }
        
        // view の場合、その配下のパッケージディレクトリも作成する
        if(element.equals(ELEMENTS[VIEW])){
            File packageDir =  new File(outputDir.getAbsolutePath() + SEP + scprm.getModelName().toLowerCase() + "controller");
            packageDir.mkdir();
        }
    }
    
    /**
     * templatePath; テンプレートファイルのパス, value: 出力ファイルのパス　のマップの準備
     */    
    private void prepareInOutFiles(){
        String packageName   = scprm.getPackageName().replace(".", "\\");
        inOutFiles.put("template/controllers/Controller.java.vm",   "src" + SEP + "controllers"   + SEP + table + "Controller.java");
        inOutFiles.put("template/models/Model.java.vm",             "src" + SEP + "models"        + SEP + table + ".java");
        inOutFiles.put("template/views/Edit.fxml.vm",               "src" + SEP + packageName     + SEP + "Edit.vm.fxml");
        inOutFiles.put("template/views/Edit.java.vm",               "src" + SEP + packageName     + SEP + "Edit.java");
        inOutFiles.put("template/views/Form.fxml.vm",               "src" + SEP + packageName     + SEP + "Form.vm.fxml");
        inOutFiles.put("template/views/Form.java.vm",               "src" + SEP + packageName     + SEP + "Form.java");
        inOutFiles.put("template/views/List.fxml.vm",               "src" + SEP + packageName     + SEP + "List.vm.fxml");
        inOutFiles.put("template/views/List.java.vm",               "src" + SEP + packageName     + SEP + "List.java");
        inOutFiles.put("template/views/Make.fxml.vm",               "src" + SEP + packageName     + SEP + "Make.vm.fxml");
        inOutFiles.put("template/views/Make.java.vm",               "src" + SEP + packageName     + SEP + "Make.java");
        inOutFiles.put("template/views/Show.fxml.vm",               "src" + SEP + packageName     + SEP + "Show.vm.fxml");
        inOutFiles.put("template/views/Show.java.vm",               "src" + SEP + packageName     + SEP + "Show.java");
    }
    
    /**
     * 生成ファイル出力
     * @param file 生成ファイル
     * @param output 出力内容
     * @throws IOException 
     */
    private void outputFile(File file, String output) {
        //生成対象のファイルが存在する場合は先に削除する
        if(file.exists()){
            file.delete();
        }
        
        //ファイル出力
        PrintWriter pw  = null;
        try {
            pw  = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            file.createNewFile();
            pw.print(output);
            pw.flush();
        } catch (IOException ex) {
            System.out.println("ERROR! Output file: " + file.getAbsolutePath() + " failed");
            System.exit(ENDCODE_ERROR_OUTPUT);
        } finally {
            pw.close();
        }
    }
}
