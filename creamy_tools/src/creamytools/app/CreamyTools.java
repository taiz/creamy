/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.app;

import creamytools.command.DestroyCommnad;
import creamytools.command.NewCommand;
import creamytools.command.ScaffoldCommand;

/**
 * この CreamyTools ツールのメインクラス
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public class CreamyTools implements IConstants {

    /**
     * メイン関数
     * @param args 実行時に渡されるパラメータ群
     */
    public static void main(String[] args) {
   
        // 入力パラメータのチェック　パラメータは最低２つ（例： c:\creamy new）
        if(!ParamParser.checkNumberParam(args, NUMBER_PARAM_2)) {
            System.exit(ENDCODE_ERROR_PARAM);
        }

        // コマンドごとにメソッドを呼び出す
        if(args[IDX_FUNCTION].equals("new")){
            NewCommand newCmd       = new NewCommand();
            newCmd.exec(args);
        }
        else if(args[IDX_FUNCTION].equals("destroy")){
            DestroyCommnad destCmd       = new DestroyCommnad();
            destCmd.exec(args);
        } else if(args[IDX_FUNCTION].equals("scaffold")){
            ScaffoldCommand scfCmd   = new ScaffoldCommand();
            scfCmd.exec(args);
        }
        else{
            System.out.println("ERROR! First parameter must be \"new\" or \"scaffold\" or \"destroy\". \""
                    + args[IDX_FUNCTION] + "\" is invalid");
            System.exit(ENDCODE_ERROR_PARAM);
        }
        
        // 終了メッセージ
        System.out.println(args[IDX_FUNCTION] + " command done!");
    }
}
