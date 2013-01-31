/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.command;

import creamytools.app.IConstants;
import creamytools.app.VelocityWrapper;
import creamytools.entity.ScaffoldParam;
import java.io.*;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * プロジェクト新規作成コマンド
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class NewCommand implements ICommand, IConstants {

    /**
     * project.propertiesに追加するライブラリ参照１
     */
    private final String[] EXCLUDES    = {
        "##### ADDED BY creamy_tools #####",
        "excludes=",
        "file.reference.creamy.jar=lib/creamy.jar",
        "file.reference.ebean-2.7.3.jar=lib/ebean-2.7.3.jar",
        "file.reference.hibernate-validator-4.3.0.Final.jar=lib/hibernate-validator-4.3.0.Final.jar",
        "file.reference.javax.validation-1.0.0.GA.jar=lib/javax.validation-1.0.0.GA.jar",
        "file.reference.jsonic-1.2.11.jar=lib/jsonic-1.2.11.jar",
        "file.reference.persistence-api-1.0.jar=lib/persistence-api-1.0.jar",
        "file.reference.sqlite-jdbc-3.7.2.jar=lib/sqlite-jdbc-3.7.2.jar",
        "file.reference.velocity-1.7-dep.jar=lib/velocity-1.7-dep.jar",
        "file.reference.velocity-1.7.jar=lib/velocity-1.7.jar",
    };

    /**
     * project.propertiesに追加するライブラリ参照２
     */
    private String[] CLASSPATHES    = {
        "##### ADDED BY creamy_tools #####",
        "javac.classpath=\\",
        "    ${file.reference.creamy.jar}:\\",
        "    ${file.reference.ebean-2.7.3.jar}:\\",
        "    ${file.reference.hibernate-validator-4.3.0.Final.jar}:\\",
        "    ${file.reference.javax.validation-1.0.0.GA.jar}:\\",
        "    ${file.reference.jsonic-1.2.11.jar}:\\",
        "    ${file.reference.persistence-api-1.0.jar}:\\",
        "    ${file.reference.sqlite-jdbc-3.7.2.jar}:\\",
        "    ${file.reference.velocity-1.7-dep.jar}:\\",
        "    ${file.reference.velocity-1.7.jar}:\\",
    };

    /**
     * new コマンド機能
     * @param args  実行時に渡されるパラメータ群
     */
    @Override
    public void exec(String[] args){
        // ユーザーのカレントディレクトリと、当プログラムの実行時ディレクトリを取得
        String targetDir    = System.getProperty("user.dir");
        String execDir      = args[IDX_EXEC_DIR];
        
        // オプション指定がある場合
        if(args.length == NUMBER_PARAM_3) {
            targetDir       = args[IDX_TARGET_DIR];
            File projDir    = new File(targetDir);
            if(!projDir.exists()){
                System.out.println("ERROR! Target project doesn't exist. Project: " + targetDir);
                System.exit(ENDCODE_ERROR_PARAM);
            }
        }

        // jar ファイル内のコピー元フォルダ／ファイルを解凍する
        Runtime rt  = Runtime.getRuntime();
        
        try {
            rt.exec("jar -xf " + SEP + execDir + SEP + "creamy_tools.jar lib");
            rt.exec("jar -xf " + SEP + execDir + SEP + "creamy_tools.jar prop");
            rt.exec("jar -xf " + SEP + execDir + SEP + "creamy_tools.jar src");
        } catch (IOException ex) {
            System.out.println("ERROR! jar command execution error");
            StackTraceElement[] stacks	= ex.getStackTrace();
            for(int i = 0; i < stacks.length; i++){
            	System.out.println("    "+ stacks[i]);
            }
            System.exit(ENDCODE_ERROR_EXECJAR);
        }
        
        // 解凍したフォルダを移動
        try {
            // フォルダ生成のために配置しておいたダミーファイルを削除
            String delDummy1	= targetDir + SEP + "src" + SEP + "controllers"	+ SEP + "dummy";
            String delDummy2	= targetDir + SEP + "src" + SEP + "models"		+ SEP + "dummy";
            String delDummy3	= targetDir + SEP + "src" + SEP + "views"		+ SEP + "dummy";
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            new File(delDummy1).delete();
            new File(delDummy2).delete();
            new File(delDummy3).delete();
            
            FileUtils.moveDirectory(new File(execDir + SEP + "lib"),    new File(targetDir + SEP + "lib"));
            FileUtils.moveDirectory(new File(execDir + SEP + "prop"),   new File(targetDir + SEP + "prop"));
            FileUtils.moveDirectory(new File(execDir + SEP + "src"),    new File(targetDir + SEP + "src"));
        } catch (IOException e) {

        }
        
        // project.properties ファイルにライブラリ参照の定義を追加
        addLibRef(targetDir);
        
        // エントリーポイントの Java ファイルを書き換え
        String projName         = new File(targetDir).getName();
        File entry              = new File(targetDir + SEP + "src" + SEP + projName.toLowerCase() + SEP + projName + ".java");
        ScaffoldParam scprm     = new ScaffoldParam();
        scprm.setEntryClassName(projName);
        scprm.setEntryPackageName(projName.toLowerCase());
                
        // 全行読み込みながら、
        // テンプレートファイルからファイル出力
        // Verocity ラッパークラスを使用し、テンプレートに値を展開してファイル出力
        VelocityWrapper velocity    = new VelocityWrapper();
        velocity.put("scprm", scprm);

        Template template = null;
        try{
            template   = velocity.getTemplate("template/EntryPoint.java.vm");
        } catch(ResourceNotFoundException ex) {
            
        }
        velocity.setTemplate(template);
        String content      = velocity.merge();
        try {
            FileWriter writer    = new FileWriter(entry);
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("ERROR! cannot overwride entry point file");
            System.exit(ENDCODE_ERROR_EPTOWR);
        }
    }

    /**
     * project.propertiesファイルに必要なライブラリ参照の定義を追加
     * @param targetPath 
     */
    private void addLibRef(String targetPath){
        
        // 対象ファイルの存在チェック。プロジェクトディレクトリ配下の nbproject ディレクトリの配下にある
        File projDir    = new File(targetPath);
        File projProp   = new File(targetPath + SEP + "nbproject" + SEP + "project.properties");
        if(!projProp.exists()){
            System.out.println("ERROR! there is no project.properties file on the target project");
            System.exit(ENDCODE_ERROR_PRJPROP);
        }
        
        // 全行読み込みながら、必要な行を挿入
        StringBuilder  output    = new StringBuilder();
        try{
            FileReader fr       = new FileReader(projProp);
            BufferedReader br   = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                if(line.startsWith("excludes=")){
                    // "excludes=" の行の後に定義追加
                    for(int i = 0; i < EXCLUDES.length; i++){
                        output.append(EXCLUDES[i]).append("\n");
                    }
                    output.append("file.reference.").append(projDir.getName()).append("-prop=prop").append("\n");
                    output.append("##### ADDED BY creamy_tools #####").append("\n");
                }
                else if(line.startsWith("javac.classpath=")){
                    // "javac.classpath=" の行の後に定義追加
                    for(int i = 0; i < CLASSPATHES.length; i++){
                        output.append(CLASSPATHES[i]).append("\n");
                    }
                    output.append("    ${file.reference.").append(projDir.getName()).append("-prop}").append("\n");
                    output.append("##### ADDED BY creamy_tools #####").append("\n");
                }
                else{
                    // その他の行はそのまま
                    output.append(line).append("\n");
                }
            }
        }catch(Exception e){
            System.out.println("ERROR! cannot read project.properties file on the target project");
            System.exit(ENDCODE_ERROR_PRJPROP);
        }

        // 変更した内容にて project.properties ファイルを上書き
        try {
            FileWriter writer    = new FileWriter(projProp);
            writer.write(output.toString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("ERROR! cannot overwride project.properties file on the target project");
            System.exit(ENDCODE_ERROR_PRJPROP);
        }
        try {
            // 上書きした内容をプロジェクトに反映するため、意味なく project.propeties ファイルをコピーしてすぐ削除
            //　※なぜかこのような操作をするとプロジェクトに反映され、プロジェクトプロパティ上でライブラリにパスが通る
            File copiedFile   = new File(projProp.getAbsolutePath() + ".copied");
            FileUtils.copyFile(projProp, copiedFile);
            copiedFile.delete();
        } catch (IOException ex) {
            System.out.println("ERROR! project.properties.copy file remaind in nbproject folder so delete manually");
        }
    }
}
