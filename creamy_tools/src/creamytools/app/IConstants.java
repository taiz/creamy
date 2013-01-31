/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.app;

import java.io.File;

/**
 * 定数定義インターフェース
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public interface IConstants {

    /**
     * パス区切り文字、ファイルシステム上（Windows:\ Linuxs:/）
     */
    public static final String SEP                          = File.separator;
    
    /**
     * パス区切り文字、jarファイル内
     */
    public static final String SEPJ                         = "/";

    /**
     * 入力パラメータの最低の数１
     */
    public static final int NUMBER_PARAM_1                  = 1;

    /**
     * 入力パラメータの最低の数２
     */
    public static final int NUMBER_PARAM_2                  = 2;
    
    /**
     * 入力パラメータの最低の数３
     */
    public static final int NUMBER_PARAM_3                  = 3;

    /**
     * 入力パラメータの最低の数３
     */
    public static final int NUMBER_PARAM_4                  = 4;

    /**
     * 入力パラメータ中の実行バッチファイル・シェルスクリプトのパラメータの位置
     */
    public static final int IDX_EXEC_DIR                    = 0;

    /**
     * 入力パラメータ中の機能指定パラメータの位置(scaffold)
     */
    public static final int IDX_FUNCTION                    = 1;
    
    /**
     * 入力パラメータ中のテーブル指定パラメータの位置(scaffold)
     */
    public static final int IDX_TABLE                       = 2;
    
    /**
     * 入力パラメータ中のカラム指定パラメータの開始位置(scaffold)
     */
    public static final int IDX_COLUMN1                     = 3;

    /**
     * 対象プロジェクトのディレクトリ指定パラメータの位置(new)
     */
    public static final int IDX_TARGET_DIR                  = 2;

    /**
     * 入力パラメータ中のカラム指定に : が含まれないエラー
     */
    public static final int ERR_COLON                       = -1;

    /**
     * プログラムの終了コード（正常終了）
     */
    public static final int ENDCODE_NORMAL                  = 0;

    /**
     * プログラムの終了コード（入力パラメータエラー）
     */
    public static final int ENDCODE_ERROR_PARAM             = 1;

    /**
     * プログラムの終了コード（ファイル出力エラー）
     */
    public static final int ENDCODE_ERROR_OUTPUT            = 2;

    /**
     * プログラムの終了コード（ディレクトリ削除エラー）
     */
    public static final int ENDCODE_ERROR_DELDIR            = 3;

    /**
     * プログラムの終了コード（ディレクトリコピーエラー）
     */
    public static final int ENDCODE_ERROR_COPYDIR           = 4;

    /**
     * プログラムの終了コード（テンプレートファイル取得エラー）
     */
    public static final int ENDCODE_ERROR_GETTMPL           = 5;

    /**
     * プログラムの終了コード（project.propertiesファイル関連エラー）
     */
    public static final int ENDCODE_ERROR_PRJPROP           = 6;
    
    /**
     * プログラムの終了コード（jar コマンド実行エラー）
     */
    public static final int ENDCODE_ERROR_EXECJAR           = 7;
    
    /**
     * プログラムの終了コード（エントリーポイントファイル上書きエラー）
     */
    public static final int ENDCODE_ERROR_EPTOWR            = 8;

    /**
     * 文字列の先頭（大文字変換対象）
     */
    public static final int FIRST_CHARA                     = 0;

    /**
     * 文字列の２文字目
     */
    public static final int SECOND_CHARA                    = 1;
    
    /**
     * 生成先フォルダ名称群
     */
    public final String[] ELEMENTS                          = {"controllers", "models", "views"};

    /**
     * 生成先フォルダ（controller）
     */
    public final int CONTROLLER                             = 0;

    /**
     * 生成先フォルダ（model）
     */
    public final int MODEL                                  = 1;

    /**
     * 生成先フォルダ（VIEW）
     */
    public final int VIEW                                   = 2;

    /**
     * 指定の文字列が見つからない場合
     */
    public final int STRING_NOT_FOUND                       = -1;

    /**
     * ファイル数ゼロ
     */
    public final int FILE_NUMBER_0                          = 0;

}
