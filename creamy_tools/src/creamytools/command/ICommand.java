/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creamytools.command;

/**
 * コマンドクラスの super interface
 * @author Ryusaburo Tanaka (Professor Akiguchi's PBL 2012, AIIT)
 */
public interface ICommand {
    
    /**
     * コマンド実行
     * @param args 入力パラメータ群 
     */
    public void exec(String[] args);
}
